package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IGreatDao;
import com.qawhcb.shadow.dao.IPostCommentDao;
import com.qawhcb.shadow.dao.IPostDao;
import com.qawhcb.shadow.dao.IUserDao;
import com.qawhcb.shadow.entity.Great;
import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.entity.PostComment;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.entity.dataModel.PostVo;
import com.qawhcb.shadow.service.IPostService;
import com.qawhcb.shadow.utils.GetLocalDateTime;
import com.qawhcb.shadow.utils.UploadFileUtils;
import com.qawhcb.shadow.utils.easemobUtils.ImUtils;
import com.sun.org.apache.bcel.internal.generic.IMUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class PostServiceImpl implements IPostService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iPostDao.delete(id);
    }

    @Override
    public Post save(Integer userId, String text, MultipartFile[] files, String publishAddress) {

        Post post = new Post();
        post.setUserId(userId);
        post.setText(text);
        post.setPublishTime(GetLocalDateTime.getLocalDataTime());
        post.setPublishAddress(publishAddress);

        Post p = iPostDao.save(post);

        // 上传文件
        String names = UploadFileUtils.cardImgUpload(files, String.valueOf(p.getId()));

        p.setImg(names);

        return iPostDao.save(p);
    }

    @Override
    public List<PostVo> find(Integer userId, String type, String requirement, int page) {

        List<PostVo> postVos = new ArrayList<>(16);

        List<Post> posts = null;

        // 如果是根据关注查询，则使用此方法。否则使用 getPostsByRequirement()
        if (FOLLOW.equals(type)) {

            // 如果查询参数为null 或者 “” 直接返回
            if (requirement == null || "".equals(requirement)){
                return null;
            }

            // 如果查询类型是‘关注’

            // 查找当前用户的所有关注好友
            User user = iUserDao.findOne(Integer.parseInt(requirement));
            List<String> contacts = ImUtils.contacts(user.getPhone());

            // 提取出关注用户id
            List<String> userIds = new ArrayList<>(16);
            for (String hailFellow :
                    contacts) {

                // 遍历好友信息，查询发帖. 环信用户id与系统用户电话相同。
                List<User> users = iUserDao.findAll(new Specification<User>() {
                    @Override
                    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        List<Predicate> re = new ArrayList<>(8);

                        re.add(cb.equal(root.get("phone"), hailFellow));
                        // 删除用户，也查询发帖
//                        re.add(cb.equal(root.get("ifDel"), "false"));

                        Predicate[] p = new Predicate[re.size()];

                        return cb.and(re.toArray(p));
                    }
                });

                if (users.size() > 0) {
                    userIds.add(String.valueOf(users.get(0).getId()));
                }
            }

            // 如果有关注用户，才执行业务
            if (userIds.size() > 0) {


                // 查询id在关注用户id内的用户发布的帖子，按照时间排序

                Sort sort = new Sort(Sort.Direction.DESC, "publishTime");

                Pageable pageable = new PageRequest(page, 10, sort);

                Page<Post> postPage = iPostDao.findAll(new Specification<Post>() {
                    @Override
                    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        List<Predicate> re = new ArrayList<>(8);

                        // 遍历关注用户id
                        for (String userId :
                                userIds) {
                            re.add(cb.or(cb.equal(root.get("userId"), userId)));
                        }

//                    Predicate[] p = new Predicate[re.size()];

//                    CriteriaQuery<?> where = query.where(cb.or(re.toArray(p)));

//                    re.clear();
                        re.add(cb.equal(root.get("ifDel"), "false"));

                        Predicate[] p = new Predicate[re.size()];

                        return cb.and(re.toArray(p));
                    }
                }, pageable);
                posts = postPage.getContent();
            }else {
                posts = null;
            }

        } else {
            // 根据条件进行分页查询，获取所有帖子
            posts = getPostsByRequirement(type, requirement, page);
        }


        PostVo postVo = null;
        // 遍历
        for (Post p :
                posts) {

            postVo = new PostVo();


            // 查询当前用户是否点赞
            if (userId != null && userId != 0){
                Great praise = iGreatDao.praised(userId, p.getId());

                if (praise != null){
                    p.setLable1("true");
                }

            }

            postVo.setPost(p);

            // 查询发帖用户信息
            User user = iUserDao.findOne(p.getUserId());

            postVo.setUserName(user.getNickName());
            postVo.setUserImg(user.getPortrait());

            // 查询评论次数
            List<PostComment> postComments = iPostCommentDao.findAll(new Specification<PostComment>() {
                @Override
                public Predicate toPredicate(Root<PostComment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> re = new ArrayList<>(8);

                    re.add(cb.equal(root.get("postId"), p.getId()));
                    re.add(cb.equal(root.get("ifDel"), "false"));

                    Predicate[] p = new Predicate[re.size()];

                    return cb.and(re.toArray(p));
                }
            });

            // 所有评论数
            postVo.setCommentNum(String.valueOf(postComments.size()));

            postVos.add(postVo);

        }

        return postVos;
    }

    @Override
    public List<Post> nominate(Integer[] postIds) {
        List<Post> posts = new ArrayList<>(16);

        for (Integer id :
                postIds) {

            Post post = iPostDao.findOne(id);

            // 设置为精选
            post.setNominate("true");

            Post save = iPostDao.save(post);

            posts.add(save);

        }

        return posts;
    }

    @Override
    public List<Post> findNominate() {
        return iPostDao.findNominate();
    }

    private List<Post> getPostsByRequirement(String type, String requirement, int page) {

        // 排序对象
        Sort sort = null;
        // 分页对象
        Pageable pageable = null;

        // 判断查询类型
        if (TIME.equals(type)) {
            sort = new Sort(Sort.Direction.DESC, "publishTime");

            pageable = new PageRequest(page, 10, sort);
        } else if (HEAT.equals(type)) {
            // 查询内容为‘热门’，点赞人数最多的
            sort = new Sort(Sort.Direction.DESC, "laudCount");

            pageable = new PageRequest(page, 10, sort);
        }

        Page<Post> pagePost = iPostDao.findAll(new Specification<Post>() {
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(8);

                if (NOMINATE.equals(type)) {
                    re.add(cb.equal(root.get("nominate"), "true"));
                } else if (CITY_WIDE.equals(type)) {
                    re.add(cb.like(root.get("publishAddress"), "%requirement%"));
                }

                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        }, pageable);

        return pagePost.getContent();

    }

    private final String FOLLOW = "1";
    private final String NOMINATE = "2";
    private final String HEAT = "3";
    private final String TIME = "4";
    private final String CITY_WIDE = "5";

    @Autowired
    private IPostDao iPostDao;

    @Autowired
    private IPostCommentDao iPostCommentDao;

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private IGreatDao iGreatDao;
}

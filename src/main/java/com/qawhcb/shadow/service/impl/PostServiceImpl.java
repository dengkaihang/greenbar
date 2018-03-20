package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IPostCommentDao;
import com.qawhcb.shadow.dao.IPostDao;
import com.qawhcb.shadow.dao.IUserDao;
import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.entity.PostComment;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.entity.dataModel.PostVo;
import com.qawhcb.shadow.service.IPostService;
import com.qawhcb.shadow.utils.GetLocalDateTime;
import com.qawhcb.shadow.utils.UploadFileUtils;
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
    public Post save(String text, MultipartFile[] files, String publishAddress) {

        Post post = new Post();
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
    public List<PostVo> find(String type, String requirement, int page) {

        List<PostVo> postVos = new ArrayList<>(16);

        List<Post> posts = new ArrayList<>(16);


        // 判断查询类型
        switch (type) {
            // 如果查询类型是‘关注’
            case FOLLOW:

                break;
            case CHOICE:
                break;
            // 查询内容为‘热门’，点赞人数最多的
            case HEAT:

                posts = getPostsByRequirement(page);

                break;
            case TIME:

                posts = getPostsByRequirement(page);

                break;
            case CITY_WIDE:
                break;
            default:

                break;
        }

        PostVo postVo = new PostVo();
        // 遍历
        for (Post p :
                posts) {

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

    private List<Post> getPostsByRequirement(String type, String requirement, int page) {

        if (!CITY_WIDE.equals(type)) {

            Sort sort = new Sort(Sort.Direction.DESC, "laudCount");

            Pageable pageable = new PageRequest(page, 10, sort);

            Page<Post> pagePost = iPostDao.findAll(new Specification<Post>() {
                @Override
                public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> re = new ArrayList<>(8);

                    re.add(cb.equal(root.get("ifDel"), "false"));

                    Predicate[] p = new Predicate[re.size()];

                    return cb.and(re.toArray(p));
                }
            }, pageable);

            return pagePost.getContent();
        }

    }

    private final String FOLLOW = "1";
    private final String CHOICE = "2";
    private final String HEAT = "3";
    private final String TIME = "4";
    private final String CITY_WIDE = "5";

    @Autowired
    private IPostDao iPostDao;

    @Autowired
    private IPostCommentDao iPostCommentDao;

    @Autowired
    private IUserDao iUserDao;
}

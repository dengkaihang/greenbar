package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.ICommentDao;
import com.qawhcb.shadow.dao.IOrderDao;
import com.qawhcb.shadow.dao.IUserDao;
import com.qawhcb.shadow.entity.Comment;
import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.entity.dataModel.CommentVo;
import com.qawhcb.shadow.service.ICommentService;
import com.qawhcb.shadow.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CommentServiceImpl implements ICommentService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iCommentDao.delete(id);
    }

    @Override
    public List<CommentVo> findAll(int goodsId) {

        // 通过商品查出所有订单
        List<Order> orders = iOrderService.getAll(goodsId);

        List<CommentVo> commentVos = new ArrayList<>(16);
        for (Order o :
                orders) {
            List<Comment> comments = iCommentDao.findAll(new Specification<Comment>() {
                @Override
                public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> re = new ArrayList<>(8);

                    re.add(cb.equal(root.get("orderId"), o.getId()));
                    re.add(cb.equal(root.get("ifDel"), "false"));

                    Predicate[] p = new Predicate[re.size()];

                    return cb.and(re.toArray(p));
                }
            });

            // 遍历订单，关联评论用户
            CommentVo commentVo = null;
            for (Comment comment :
                    comments) {
                commentVo = new CommentVo();

                commentVo.setComment(comment);

                // 添加评论用户
                User userSrc = iUserDao.findOne(comment.getUserId());

                User userTarget = new User();

                userTarget.setNickName(userSrc.getNickName());
                userTarget.setPortrait(userSrc.getPortrait());

                commentVo.setUser(userTarget);

                commentVos.add(commentVo);
            }
        }

        return commentVos;
    }

    @Autowired
    private ICommentDao iCommentDao;

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IUserDao iUserDao;
}

package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IUserDao;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.service.IUserService;
import com.qawhcb.shadow.utils.easemobUtils.ImUtils;
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
 * 用户ｓｅｒｖｉｃｅ实现类
 * Created by kane on 18-3-5
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User codeLogin(String phone) throws Exception {
        if(phone == null || phone.trim().isEmpty()){
            throw new Exception("电话号码不能为空！");
        }
        User user = userDao.findOne(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.equal(root.get("phone"), phone));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });

        return user;
    }

    @Override
    public User regist(String phone) throws Exception {
        User user = new User();
        user.setPhone(phone);
        user.setNickName("小萌");
        user.setAutograph("P出另一个你。");
        user.setSex("男");
        user = userDao.save(user);
        ImUtils.register(user.getId().toString());
        return user;
    }

    @Override
    public User modify(User user) {
        return userDao.save(user);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }
}

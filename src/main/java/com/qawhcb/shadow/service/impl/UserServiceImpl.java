package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IUserDao;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.service.IUserService;
import com.qawhcb.shadow.utils.MD5Util;
import com.qawhcb.shadow.utils.UpdateUtils;
import com.qawhcb.shadow.utils.UploadFileUtils;
import com.qawhcb.shadow.utils.easemobUtils.ImUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 用户ｓｅｒｖｉｃｅ实现类
 * Created by kane on 18-3-5
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User codeLogin(String phone) throws Exception {
        if (phone == null || phone.trim().isEmpty()) {
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

        // 注册环信即时通讯用户 使用用户手机号注册
        ImUtils.register(user.getPhone());

        return user;
    }

    @Override
    public User modify(User user) {
        return userDao.save(user);

//        // 注册环信即时通讯用户
//        if (save != null){
//            ImUtils.register(save.getPhone());
//        }

//        return save;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public User update(User user) {
        User target = userDao.findOne(user.getId());

        UpdateUtils.copyNonNullProperties(user, target);

        // 清空密码
        target.setPassword(null);

        return userDao.save(target);
    }

    @Override
    public User updateVar(Integer userId, MultipartFile[] files) {
        User user = userDao.findOne(userId);

        String names = UploadFileUtils.userImgUpload(files, String.valueOf(userId));

        user.setPortrait(names);

        return userDao.save(user);
    }

    @Override
    public User isRegisterBySide(String token, String mode) {
        List<User> users = userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();

                list.add(cb.equal(root.get("mode"), token));

                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });

        if (users.size() > 0) {

            // 如果有此对象，说明此次登录正常。更改其token
            User src = users.get(0);
            src.setToken(MD5Util.createId());

            User user = userDao.save(src);

            user.setPassword(null);
            user.setWechat(null);
            user.setQq(null);
            user.setToken(null);

            return user;
        } else {
            return null;
        }
    }

    @Override
    public User loginBySide(String token, String mode, String phone) {

        User user = new User();

        if ("qq".equals(mode)) {
            user.setQq(token);
        } else if ("wechat".equals(mode)) {
            user.setWechat(token);
        }

        user.setPhone(phone);

        user.setToken(MD5Util.createId());

        User save = userDao.save(user);

        if (save != null) {
            // 注册环信
            ImUtils.register(phone);
        }
        return user;
    }

    @Override
    public void follow(Integer userId, Integer friend) {
        // 根据id查询出环信的id（即用户手机号）
        String ownerId = userDao.findOne(userId).getPhone();

        String friendId = userDao.findOne(friend).getPhone();

        ImUtils.follow(ownerId, friendId);
    }

    @Override
    public List<User> findByPhones(String phones) {

        List<User> users = new ArrayList<>(16);

        String[] phone = phones.split(",");

        for (String p :
                phone) {
            User src = userDao.findByPhone(p);

            users.add(src);
        }
        return users;
    }
}

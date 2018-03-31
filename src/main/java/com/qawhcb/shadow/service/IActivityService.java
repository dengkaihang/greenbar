package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Activity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IActivityService {

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    public void delete(Integer id);


    /**
     * 新建活动
     *
     * @param account 创建活动员工帐号
     * @param title   活动标题
     * @param text    活动简介
     * @param files   活动图片
     * @param covers  活动封面图片
     * @return 创建好的活动对象
     */
    Activity save(String account, String text, String title, MultipartFile[] files, MultipartFile[] covers);

    /**
     * 查询所有活动
     * @return 活动集合
     */
    List<Activity> find();
}

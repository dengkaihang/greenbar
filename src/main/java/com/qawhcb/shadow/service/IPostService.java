package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.entity.dataModel.PostVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IPostService {

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 帖子发布
     *
     * @param text           发布内容
     * @param files          发布图片
     * @param publishAddress 发布地址
     * @return 发布结果
     */
    public Post save(String text, MultipartFile[] files, String publishAddress);

    /**
     * 查询所有帖子
     *
     * @param type        查询类型
     * @param requirement 查询条件
     * @param page        当前页
     * @return 查询结果
     */
    public List<PostVo> find(String type, String requirement, int page);
}

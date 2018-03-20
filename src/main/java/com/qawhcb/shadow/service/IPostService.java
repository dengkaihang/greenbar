package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Post;
import org.springframework.web.multipart.MultipartFile; /**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IPostService {

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 帖子发布
     * @param text 发布内容
     * @param files 发布图片
     * @param publishAddress 发布地址
     * @return 发布结果
     */
    public Post save(String text, MultipartFile[] files, String publishAddress);
}

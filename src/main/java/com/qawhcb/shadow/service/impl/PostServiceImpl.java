package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IPostDao;
import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.service.IPostCommentService;
import com.qawhcb.shadow.service.IPostService;
import com.qawhcb.shadow.utils.GetLocalDateTime;
import com.qawhcb.shadow.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private IPostDao iPostDao;
}

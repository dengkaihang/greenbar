package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IActivityDao;
import com.qawhcb.shadow.entity.Activity;
import com.qawhcb.shadow.service.IActivityService;
import com.qawhcb.shadow.utils.GetLocalDateTime;
import com.qawhcb.shadow.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class ActivityServiceImpl implements IActivityService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iActivityDao.delete(id);
    }

    @Override
    public Activity save(String account, String text, String title, MultipartFile[] files, MultipartFile[] covers) {

        Activity activity = new Activity();

        activity.setEmployeeAccount(account);
        activity.setTitle(title);
        activity.setText(text);
        activity.setTime(GetLocalDateTime.getLocalDataTime());

        Activity save = iActivityDao.save(activity);

        // 上传活动图片
        String img = UploadFileUtils.activityImgUpload(files, String.valueOf(save.getId()));
        String cover = UploadFileUtils.activityImgUpload(covers, save.getId() + "/cover");

        save.setImg(img);
        save.setCoverImg(cover);

        return iActivityDao.save(save);
    }

    @Override
    public List<Activity> find() {

        iActivityDao.findAll();

        return null;
    }


    @Autowired
    private IActivityDao iActivityDao;
}

package com.qawhcb.shadow.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Taoism <br/>
 * Created on 2018/1/28 <br/>
 */
public class ImgUploadUtils {

    /**
     * 用户文件上传
     *
     * @param files 文件数组类
     * @param parentPath 文件保存路径
     * @return 上传全部文件路径
     */
    public static String userImgUpload(MultipartFile[] files, String parentPath) {


        String uploadPath = ImgUploadUtils.getProperty("img.upload.user");

        String download = ImgUploadUtils.getProperty("img.download.user");

        // 可以从页面传参数过来
        // 这里可以支持多文件上传
        String names = upload(files, parentPath, uploadPath, download);

        return names;
    }

    /**
     * 店铺文件上传
     *
     * @param files 文件数组类
     * @param parentPath 文件保存路径
     * @return 上传全部文件路径
     */
    public static String storeImgUpload(MultipartFile[] files, String parentPath) {

        String uploadPath = ImgUploadUtils.getProperty("img.upload.store");

        String download = ImgUploadUtils.getProperty("img.download.store");

        // 可以从页面传参数过来
        // 这里可以支持多文件上传
        String names = upload(files, parentPath, uploadPath, download);
        return names;
    }

    private static String upload(MultipartFile[] files, String parentPath, String uploadPath, String download) {

        StringBuffer names = new StringBuffer();

        if (files != null && files.length >= 1) {
            BufferedOutputStream bw = null;
            try {

                for (int i = 0; i < files.length; i++) {
                    String fileName = files[i].getOriginalFilename();
                    // 判断是否有文件且是否为图片文件
                    if (fileName != null && !"".equalsIgnoreCase(fileName.trim()) && isImageFile(fileName)) {
                        // 创建输出文件对象
                        File outFile = new File(uploadPath + parentPath + "/" + UUID.randomUUID().toString() + getFileType(fileName));
                        // 拷贝文件到输出文件对象
                        FileUtils.copyInputStreamToFile(files[i].getInputStream(), outFile);

                        names.append(download + parentPath + "/" + outFile.getName() + ",");
                    }
                }
            } catch (Exception e) {
                LoggerUtil.getLogger().error("文件上传失败" + e.getMessage());
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    LoggerUtil.getLogger().error("文件流关闭失败" + e.getMessage());
                }
            }
        }

        return names.toString();
    }

    public static String getProperty(String s) {
        InputStream is = ImgUploadUtils.class.getResourceAsStream("/properties/path.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            LoggerUtil.getLogger().error("配置文件加载出错！");
        }
        return properties.getProperty(s);
    }

    /**
     * 判断文件是否为图片文件
     *
     * @param fileName
     * @return
     */
    private static Boolean isImageFile(String fileName) {
        String[] img_type = new String[]{".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        if (fileName == null) {
            return false;
        }
        fileName = fileName.toLowerCase();
        for (String type : img_type) {
            if (fileName.endsWith(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件后缀名
     *
     * @param fileName
     * @return
     */
    private static String getFileType(String fileName) {
        if (fileName != null && fileName.indexOf(".") >= 0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
}


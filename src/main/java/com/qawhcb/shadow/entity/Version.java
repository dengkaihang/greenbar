package com.qawhcb.shadow.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * app版本
 * Created by kane on 18-2-1.
 */
@Entity
@Table(name = "t_version")
public class Version {

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;             //id
    @Column(name = "version_num", length = 5)
    private Integer versionNum;    //版本号
    @Column(name = "version_name", length = 30)
    private String versionName;    //版本名字
    @Column(name = "update_point", length = 512)
    private String updatePoint;    //更新提示
    @Column(name = "download_links", length = 128)
    private String downloadLinks;  //下载链接
    @Column(name = "force_update", length = 12)
    private String forceUpdate;    //强制更新

    public Version() {
    }

    public Version(Integer versionNum, String versionName, String updatePoint, String downloadLinks, String forceUpdate) {
        this.versionNum = versionNum;
        this.versionName = versionName;
        this.updatePoint = updatePoint;
        this.downloadLinks = downloadLinks;
        this.forceUpdate = forceUpdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getUpdatePoint() {
        return updatePoint;
    }

    public void setUpdatePoint(String updatePoint) {
        this.updatePoint = updatePoint;
    }

    public String getDownloadLinks() {
        return downloadLinks;
    }

    public void setDownloadLinks(String downloadLinks) {
        this.downloadLinks = downloadLinks;
    }

    public String getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(String forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id='" + id + '\'' +
                ", versionNum=" + versionNum +
                ", versionName='" + versionName + '\'' +
                ", updatePoint='" + updatePoint + '\'' +
                ", downloadLinks='" + downloadLinks + '\'' +
                ", forceUpdate='" + forceUpdate + '\'' +
                '}';
    }
}

package com.lemon.entity;

import javax.persistence.*;

/**
 * 订单
 * Created by kane on 18-6-29
 */
@Entity
@Table(name = "t_order")
public class Order {

    private Integer id;
    private Integer userId;     //用户id
    private Integer houseId;    //房源id
    private String price;       //订单价格
    private String liveNum;     //居住人数
    private String liveTime;    //入住时间
    private String leaveTime;   //离开时间
    private String linkman;     //联系人
    private String phone;       //联系电话
    private String trueName;    //姓名
    private String idcard;      //身份证号码
    private String img;         //默认图片
    private String createTime;  //创建时间
    private String status;      //状态

    public Order() {
    }

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    public Integer getId() {
        return id;
    }
    @Column(name = "user_id", length = 11)
    public Integer getUserId() {
        return userId;
    }
    @Column(name = "house_id", length = 11)
    public Integer getHouseId() {
        return houseId;
    }
    @Column(name = "price", length = 6)
    public String getPrice() {
        return price;
    }
    @Column(name = "live_num", length = 2)
    public String getLiveNum() {
        return liveNum;
    }
    @Column(name = "live_time", length = 32)
    public String getLiveTime() {
        return liveTime;
    }
    @Column(name = "leave_time", length = 32)
    public String getLeaveTime() {
        return leaveTime;
    }
    @Column(name = "linkman", length = 12)
    public String getLinkman() {
        return linkman;
    }
    @Column(name = "phone", length = 11)
    public String getPhone() {
        return phone;
    }
    @Column(name = "true_name", length = 12)
    public String getTrueName() {
        return trueName;
    }
    @Column(name = "idcard", length = 18)
    public String getIdcard() {
        return idcard;
    }
    @Column(name = "img", length = 128)
    public String getImg() {
        return img;
    }
    @Column(name = "create_time", length = 32)
    public String getCreateTime() {
        return createTime;
    }
    @Column(name = "status", length = 6)
    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLiveNum(String liveNum) {
        this.liveNum = liveNum;
    }

    public void setLiveTime(String liveTime) {
        this.liveTime = liveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", houseId='" + houseId + '\'' +
                ", price='" + price + '\'' +
                ", liveNum='" + liveNum + '\'' +
                ", liveTime='" + liveTime + '\'' +
                ", leaveTime='" + leaveTime + '\'' +
                ", linkman='" + linkman + '\'' +
                ", phone='" + phone + '\'' +
                ", trueName='" + trueName + '\'' +
                ", idcard='" + idcard + '\'' +
                ", img='" + img + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

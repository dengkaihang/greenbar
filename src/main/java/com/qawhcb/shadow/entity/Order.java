package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 订单类
 * Created by kane on 18-3-2
 */
@Entity
@Table(name = "t_order")
public class Order {

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;              //id
    @Column(name = "status", length = 8)
    private String status;          //状态
    @Column(name = "user_id", length = 12)
    private Integer userId;          //用户id
    @Column(name = "coupon_id", length = 12)
    private Integer couponId;        //代金券id
    @Column(name = "num", length = 4)
    private String num;             //商品数量
    @Column(name = "pay_way", length = 8)
    private String payWay;          //支付方式
    @Column(name = "pay_water", length = 64)
    private String payWater;        //支付流水、
    @Column(name = "original_file", length = 256)
    private String originalFile;    //源文件链接
    @Column(name = "new_file", length = 256)
    private String newFile;         //修改后文件链接
    @Column(name = "pack_id", length = 12)
    private Integer packId;          //套餐id
    @Column(name = "modify_desc", length = 256)
    private String modifyDesc;      //修改套餐描述
    @Column(name = "price", length = 8)
    private String price;           //价格
    @Column(name = "plan_time", length = 32)
    private String planTime;        //预计完成时间
    @Column(name = "reminder_time", length = 2)
    private String reminderTime;    //催单次数
    @Column(name = "original_load_time", length = 32)
    private String originalLoadTime;//源文件下载时间
    @Column(name = "new_load_time", length = 32)
    private String newLoadTime;     //修改后文件下载时间
    @Column(name = "original_allow", length = 32)
    private String originalAllow;   //源文件允许下载时间
    @Column(name = "new_allow", length = 32)
    private String newAllow;        //修改后允许下载时间
    @Column(name = "if_download", columnDefinition = "varchar(8) default 'true'")
    private String ifDownload;      //是否允许下载
    @Column(name = "new_file_time", length = 32)
    private String newFileTime;     //修改后文件下载时间
    @Column(name = "dispute_time", length = 32)
    private String disputeTime;     //允许纠纷时间
    @Column(name = "if_fozen", columnDefinition = "varchar(8) default 'false'")
    private String ifFozen;         //是否冻结
    @Column(name = "employee_id", length = 12)
    private Integer employeeId;      //体现操作员工ｉｄ
    @Column(name = "play_money_time", length = 32)
    private String playMoneyTime;   //打款时间
    @Column(name = "post_depict", length = 128)
    private String postDepict;    //快递描述
    @Column(name = "reflect_water", length = 64)
    private String reflectWater;    //体现流水
    @Column(name = "arrival_time", length = 32)
    private String arrivalTime;     //到账时间
    @Column(name = "arrival_money", length = 8)
    private String arrivalMoney;    //到账金额
    @Column(name = "address_id", length = 12)
    private Integer addressId;       //地址id
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel;           //是否删除
    @Column(name = "label1", length = 32)
    private String label1;          //标签１
    @Column(name = "label2", length = 32)
    private String label2;          //
    @Column(name = "label3", length = 32)
    private String label3;          //

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayWater() {
        return payWater;
    }

    public void setPayWater(String payWater) {
        this.payWater = payWater;
    }

    public String getOriginalFile() {
        return originalFile;
    }

    public void setOriginalFile(String originalFile) {
        this.originalFile = originalFile;
    }

    public String getNewFile() {
        return newFile;
    }

    public void setNewFile(String newFile) {
        this.newFile = newFile;
    }

    public Integer getPackId() {
        return packId;
    }

    public void setPackId(Integer packId) {
        this.packId = packId;
    }

    public String getModifyDesc() {
        return modifyDesc;
    }

    public void setModifyDesc(String modifyDesc) {
        this.modifyDesc = modifyDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getOriginalLoadTime() {
        return originalLoadTime;
    }

    public void setOriginalLoadTime(String originalLoadTime) {
        this.originalLoadTime = originalLoadTime;
    }

    public String getNewLoadTime() {
        return newLoadTime;
    }

    public void setNewLoadTime(String newLoadTime) {
        this.newLoadTime = newLoadTime;
    }

    public String getOriginalAllow() {
        return originalAllow;
    }

    public void setOriginalAllow(String originalAllow) {
        this.originalAllow = originalAllow;
    }

    public String getNewAllow() {
        return newAllow;
    }

    public void setNewAllow(String newAllow) {
        this.newAllow = newAllow;
    }

    public String getIfDownload() {
        return ifDownload;
    }

    public void setIfDownload(String ifDownload) {
        this.ifDownload = ifDownload;
    }

    public String getNewFileTime() {
        return newFileTime;
    }

    public void setNewFileTime(String newFileTime) {
        this.newFileTime = newFileTime;
    }

    public String getDisputeTime() {
        return disputeTime;
    }

    public void setDisputeTime(String disputeTime) {
        this.disputeTime = disputeTime;
    }

    public String getIfFozen() {
        return ifFozen;
    }

    public void setIfFozen(String ifFozen) {
        this.ifFozen = ifFozen;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getPlayMoneyTime() {
        return playMoneyTime;
    }

    public void setPlayMoneyTime(String playMoneyTime) {
        this.playMoneyTime = playMoneyTime;
    }

    public String getPostDepict() {
        return postDepict;
    }

    public void setPostDepict(String postDepict) {
        this.postDepict = postDepict;
    }

    public String getReflectWater() {
        return reflectWater;
    }

    public void setReflectWater(String reflectWater) {
        this.reflectWater = reflectWater;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalMoney() {
        return arrivalMoney;
    }

    public void setArrivalMoney(String arrivalMoney) {
        this.arrivalMoney = arrivalMoney;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getIfDel() {
        return ifDel;
    }

    public void setIfDel(String ifDel) {
        this.ifDel = ifDel;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                ", couponId='" + couponId + '\'' +
                ", num='" + num + '\'' +
                ", payWay='" + payWay + '\'' +
                ", payWater='" + payWater + '\'' +
                ", originalFile='" + originalFile + '\'' +
                ", newFile='" + newFile + '\'' +
                ", packId='" + packId + '\'' +
                ", modifyDesc='" + modifyDesc + '\'' +
                ", price='" + price + '\'' +
                ", planTime='" + planTime + '\'' +
                ", reminderTime='" + reminderTime + '\'' +
                ", originalLoadTime='" + originalLoadTime + '\'' +
                ", newLoadTime='" + newLoadTime + '\'' +
                ", originalAllow='" + originalAllow + '\'' +
                ", newAllow='" + newAllow + '\'' +
                ", ifDownload='" + ifDownload + '\'' +
                ", newFileTime='" + newFileTime + '\'' +
                ", disputeTime='" + disputeTime + '\'' +
                ", ifFozen='" + ifFozen + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", playMoneyTime='" + playMoneyTime + '\'' +
                ", postDepict='" + postDepict + '\'' +
                ", reflectWater='" + reflectWater + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", arrivalMoney='" + arrivalMoney + '\'' +
                ", addressId='" + addressId + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", label1='" + label1 + '\'' +
                ", label2='" + label2 + '\'' +
                ", label3='" + label3 + '\'' +
                '}';
    }
}

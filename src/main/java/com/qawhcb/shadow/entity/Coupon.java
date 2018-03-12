package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 代金券
 * Created by kane on 18-3-4
 */
@Entity
@Table(name = "t_coupon")
public class Coupon {
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;              //id
    @Column(name = "employee_id", length = 11)
    private Integer employeeId;      //员工id
    @Column(name = "money", length = 3)
    private String money;           //金额
    @Column(name = "title", length = 32)
    private String title;           //代金券标题
    @Column(name = "avail_price", length = 3)
    private String availPrice;      //最低可用金额
    @Column(name = "total", length = 6)
    private String total;           //总数
    @Column(name = "activity_time", length = 32)
    private String activityTime;    //代金券活动时间
    @Column(name = "effective_time", length = 32)
    private String effectiveTime;   //领取后有效时间
    @Column(name = "num", length = 6)
    private String num;             //已发数量
    @Column(name = "instructions", length = 128)
    private String instructions;    //发放及使用说明
    @Column(name = "status", length = 2)
    private String status;          //状态
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel = "false";           //是否删除
    @Column(name = "lable1", length = 32)
    private String lable1;          //标签
    @Column(name = "lable2", length = 32)
    private String lable2;
    @Column(name = "lable3", length = 32)
    private String lable3;

    public Coupon() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvailPrice() {
        return availPrice;
    }

    public void setAvailPrice(String availPrice) {
        this.availPrice = availPrice;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIfDel() {
        return ifDel;
    }

    public void setIfDel(String ifDel) {
        this.ifDel = ifDel;
    }

    public String getLable1() {
        return lable1;
    }

    public void setLable1(String lable1) {
        this.lable1 = lable1;
    }

    public String getLable2() {
        return lable2;
    }

    public void setLable2(String lable2) {
        this.lable2 = lable2;
    }

    public String getLable3() {
        return lable3;
    }

    public void setLable3(String lable3) {
        this.lable3 = lable3;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", money='" + money + '\'' +
                ", title='" + title + '\'' +
                ", availPrice='" + availPrice + '\'' +
                ", total='" + total + '\'' +
                ", activityTime='" + activityTime + '\'' +
                ", effectiveTime='" + effectiveTime + '\'' +
                ", num='" + num + '\'' +
                ", instructions='" + instructions + '\'' +
                ", status='" + status + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", lable1='" + lable1 + '\'' +
                ", lable2='" + lable2 + '\'' +
                ", lable3='" + lable3 + '\'' +
                '}';
    }
}

package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 代金券使用类
 * Created by kane on 18-3-4
 */
@Entity
@Table(name = "t_use_coupon")
public class UseCoupon {
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private String id;              //id
    @Column(name = "coupon_id", length = 11)
    private String couponId;        //代金券id
    @Column(name = "user_id", length = 11)
    private String userId;          //用户id
    @Column(name = "if_use", columnDefinition = "varchar(8) default 'false'")
    private String ifUse;           //是否使用
    @Column(name = "use_time", length = 32)
    private String useTime;         //使用时间
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel;           //是否删除
    @Column(name = "lable1", length = 32)
    private String lable1;          //标签
    @Column(name = "lable2", length = 32)
    private String lable2;
    @Column(name = "lable3", length = 32)
    private String lable3;

    public UseCoupon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIfUse() {
        return ifUse;
    }

    public void setIfUse(String ifUse) {
        this.ifUse = ifUse;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
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
        return "UseCoupon{" +
                "id='" + id + '\'' +
                ", couponId='" + couponId + '\'' +
                ", userId='" + userId + '\'' +
                ", ifUse='" + ifUse + '\'' +
                ", useTime='" + useTime + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", lable1='" + lable1 + '\'' +
                ", lable2='" + lable2 + '\'' +
                ", lable3='" + lable3 + '\'' +
                '}';
    }
}

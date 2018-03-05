package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 商品类
 * Created by kane on 18-3-2
 */
@Entity
@Table(name = "t_goods")
public class Goods {
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;              //id
    @Column(name = "store_id", length = 11)
    private Integer storeId;        //店铺id
    @Column(name = "type", length = 8)
    private String type;            //类型
    @Column(name = "describe", length = 128)
    private String describe;        //商品描述
    @Column(name = "share_describe", length = 128)
    private String shareDescribe;  //商品分享描述
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel;           //是否删除
    @Column(name = "label1", length = 32)
    private String label1;          //标签
    @Column(name = "label2", length = 32)
    private String label2;          //
    @Column(name = "label3", length = 32)
    private String label3;          //

    public Goods() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getShareDescribe() {
        return shareDescribe;
    }

    public void setShareDescribe(String shareDescribe) {
        this.shareDescribe = shareDescribe;
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
        return "Goods{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", type='" + type + '\'' +
                ", describe='" + describe + '\'' +
                ", shareDescribe='" + shareDescribe + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", label1='" + label1 + '\'' +
                ", label2='" + label2 + '\'' +
                ", label3='" + label3 + '\'' +
                '}';
    }
}

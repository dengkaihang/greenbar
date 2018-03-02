package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 套餐
 * Created by kane on 18-3-2
 */
@Entity
@Table(name = "t_pack")
public class Pack {
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;         //id
    @Column(name = "goods_id", length = 11)
    private Integer goodsId;    //商品id
    @Column(name = "name", length = 8)
    private String name;        //套餐名字
    @Column(name = "describe", length = 128)
    private String describe;    //描述
    @Column(name = "price", length = 8)
    private String price;       //价格
    @Column(name = "if_print", columnDefinition = "varchar(8) default 'true'")
    private String ifPrint;     //是否支持打印
    @Column(name = "if_post", columnDefinition = "varchar(8) default 'true'")
    private String ifPost;      //是否支持邮寄
    @Column(name = "ifDel", columnDefinition = "varchar(8) default 'false'")
    private String ifDel;       //是否删除
    @Column(name = "label1", length = 32)
    private String label1;      //标签
    @Column(name = "label2", length = 32)
    private String label2;
    @Column(name = "label3", length = 32)
    private String label3;

    public Pack() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIfPrint() {
        return ifPrint;
    }

    public void setIfPrint(String ifPrint) {
        this.ifPrint = ifPrint;
    }

    public String getIfPost() {
        return ifPost;
    }

    public void setIfPost(String ifPost) {
        this.ifPost = ifPost;
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
        return "Pack{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", price='" + price + '\'' +
                ", ifPrint='" + ifPrint + '\'' +
                ", ifPost='" + ifPost + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", label1='" + label1 + '\'' +
                ", label2='" + label2 + '\'' +
                ", label3='" + label3 + '\'' +
                '}';
    }
}

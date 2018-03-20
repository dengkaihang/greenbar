package com.qawhcb.shadow.entity.dataModel;

import com.qawhcb.shadow.entity.Goods;
import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.entity.Pack;

/**
 * @author Taoism <br/>
 * Created on 2018/3/15 <br/>
 * 评论数据封装
 */
public class OrderVo {

    private Order order;
    private String orderId;
    private String goodsDepict;
    private String packPrice;
    private String num;
    private String packDepict;
    private String goodsImg;
    private String phone;
    private String goodsName;
    private String userPhone;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(String packPrice) {
        this.packPrice = packPrice;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsDepict() {
        return goodsDepict;
    }

    public void setGoodsDepict(String goodsDepict) {
        this.goodsDepict = goodsDepict;
    }

    public String getPackDepict() {
        return packDepict;
    }

    public void setPackDepict(String packDepict) {
        this.packDepict = packDepict;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}

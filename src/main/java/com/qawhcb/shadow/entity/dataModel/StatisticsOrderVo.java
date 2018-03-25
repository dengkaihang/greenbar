package com.qawhcb.shadow.entity.dataModel;

/**
 * @author Taoism <br/>
 * Created on 2018/3/23 <br/>
 */
public class StatisticsOrderVo {

    // 总金额
    private double money;
    // 总评论数
    private int comment;
    // 待处理订单
    private int pending;
    // 订单总数
    private int orderNum;
    // 待付款
    private int  obligation;
    // 待退款
    private int refundsPending;
    // 进行中
    private int underWay ;
    // 已完成
    private int fulfil;
    // 待评价
    private int evaluated;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getPending() {
        return pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getObligation() {
        return obligation;
    }

    public void setObligation(int obligation) {
        this.obligation = obligation;
    }

    public int getRefundsPending() {
        return refundsPending;
    }

    public void setRefundsPending(int refundsPending) {
        this.refundsPending = refundsPending;
    }

    public int getUnderWay() {
        return underWay;
    }

    public void setUnderWay(int underWay) {
        this.underWay = underWay;
    }

    public int getFulfil() {
        return fulfil;
    }

    public void setFulfil(int fulfil) {
        this.fulfil = fulfil;
    }

    public int getEvaluated() {
        return evaluated;
    }

    public void setEvaluated(int evaluated) {
        this.evaluated = evaluated;
    }
}

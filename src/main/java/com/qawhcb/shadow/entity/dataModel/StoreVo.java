package com.qawhcb.shadow.entity.dataModel;

import com.qawhcb.shadow.entity.Store;

/**
 * @author Taoism <br/>
 * Created on 2018/3/18 <br/>
 */
public class StoreVo {

    private Store store;
    private boolean isCollect;

    private double praiseRate;
    private double depictNum;
    private double servicNum;
    private double speedNum;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean collect) {
        isCollect = collect;
    }

    public double getPraiseRate() {
        return praiseRate;
    }

    public void setPraiseRate(double praiseRate) {
        this.praiseRate = praiseRate;
    }

    public double getDepictNum() {
        return depictNum;
    }

    public void setDepictNum(double depictNum) {
        this.depictNum = depictNum;
    }

    public double getServicNum() {
        return servicNum;
    }

    public void setServicNum(double servicNum) {
        this.servicNum = servicNum;
    }

    public double getSpeedNum() {
        return speedNum;
    }

    public void setSpeedNum(double speedNum) {
        this.speedNum = speedNum;
    }
}

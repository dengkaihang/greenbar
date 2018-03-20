package com.qawhcb.shadow.entity.dataModel;

import com.qawhcb.shadow.entity.CollectStore;

/**
 * @author Taoism <br/>
 * Created on 2018/3/19 <br/>
 */
public class CollectStoreVo {

    private CollectStore collectStore;
    private String defaultImg;
    private String depict;
    private String storeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public CollectStore getCollectStore() {
        return collectStore;
    }

    public void setCollectStore(CollectStore collectStore) {
        this.collectStore = collectStore;
    }

    public String getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }
}

package com.qawhcb.shadow.entity.dataModel;

import com.qawhcb.shadow.entity.*;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/13 <br/>
 * 商品数据封装
 */
public class GoodsVo {

    private Goods goods;

    private List<Pack> packs;

    private Store store;

    private List<CommentVo> commentVos;

    private CommentVo commentVo;

    /**
     * 评论用户信息
     */
    private User user;

    public boolean isIfCollectStore() {
        return ifCollectStore;
    }

    public void setIfCollectStore(boolean ifCollectStore) {
        this.ifCollectStore = ifCollectStore;
    }

    private boolean ifCollectStore;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CommentVo getCommentVo() {
        return commentVo;
    }

    public void setCommentVo(CommentVo commentVo) {
        this.commentVo = commentVo;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<CommentVo> getCommentVos() {
        return commentVos;
    }

    public void setCommentVos(List<CommentVo> commentVos) {
        this.commentVos = commentVos;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }
}

package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.CollectPost;
import com.qawhcb.shadow.entity.CollectStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface ICollectStoreDao extends JpaRepository<CollectStore, Integer>, JpaSpecificationExecutor<CollectStore> {

    /**
     * 按指定id逻辑删除数据
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update CollectStore as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

    /**
     * 通过店铺id查询总收藏
     * @param storeId 店铺id
     * @return 收藏列表
     */
    @Query(value = "select c from CollectStore as c where c.storeId = ?1 and c.ifDel = 'false'")
    List<CollectStore> findByStore(Integer storeId);
}

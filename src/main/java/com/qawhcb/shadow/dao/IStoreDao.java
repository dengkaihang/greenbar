package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by kane on 18-3-6
 */
public interface IStoreDao extends JpaRepository<Store, String>, JpaSpecificationExecutor<Store> {

}

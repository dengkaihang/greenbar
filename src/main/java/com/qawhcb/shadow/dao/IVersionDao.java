package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by kane on 18-2-1.
 */
public interface IVersionDao extends JpaRepository<Version, Integer>, JpaSpecificationExecutor<Version> {
}

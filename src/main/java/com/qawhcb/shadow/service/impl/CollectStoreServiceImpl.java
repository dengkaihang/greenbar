package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.ICollectStoreDao;
import com.qawhcb.shadow.dao.IStoreDao;
import com.qawhcb.shadow.entity.CollectStore;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.entity.dataModel.CollectStoreVo;
import com.qawhcb.shadow.service.ICollectStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class CollectStoreServiceImpl implements ICollectStoreService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iCollectStoreDao.delete(id);
    }

    @Override
    public CollectStore save(Integer userId, Integer storeId) {

        if (userId != null && storeId != null) {

            // 先查询该用户是否收藏该店铺
            List<CollectStore> collectStores = iCollectStoreDao.findAll(new Specification<CollectStore>() {
                @Override
                public Predicate toPredicate(Root<CollectStore> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> re = new ArrayList<>(8);

                    re.add(cb.equal(root.get("userId"), userId));
                    re.add(cb.equal(root.get("storeId"), storeId));
//                    re.add(cb.equal(root.get("ifDel"), "false"));

                    Predicate[] p = new Predicate[re.size()];

                    return cb.and(re.toArray(p));
                }
            });

            // 如果查询记录不为0，则有收藏记录。没有收藏记录则添加
            if (collectStores.size() != 0) {

                // 判断收藏记录是否删除
                for (CollectStore cs :
                        collectStores) {

                    //如果没有删除，则删除。删除则恢复
                    if ("false".equals(cs.getIfDel())) {
                        cs.setIfDel("true");
                    } else {
                        cs.setIfDel("false");
                    }
                    return iCollectStoreDao.save(cs);
                }
            } else {

                CollectStore collectStore = new CollectStore();

                collectStore.setUserId(userId);
                collectStore.setStoreId(storeId);

                return iCollectStoreDao.save(collectStore);
            }
        }
        return null;
    }


    @Override
    public List<CollectStoreVo> findAll(Integer userId) {

        List<CollectStoreVo> collectStoreVos = new ArrayList<>(8);

        List<CollectStore> collectStores = iCollectStoreDao.findAll(new Specification<CollectStore>() {
            @Override
            public Predicate toPredicate(Root<CollectStore> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(8);

                re.add(cb.equal(root.get("userId"), userId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        });

        CollectStoreVo collectStoreVo = null;
        for (CollectStore collectStore :
                collectStores) {
            collectStoreVo = new CollectStoreVo();

            Store store = iStoreDao.findOne(collectStore.getStoreId());

            collectStoreVo.setCollectStore(collectStore);
            collectStoreVo.setDefaultImg(store.getDefaultImg());
            collectStoreVo.setDepict(store.getDepict());
            collectStoreVo.setStoreName(store.getStoreName());

            collectStoreVos.add(collectStoreVo);
        }

        return collectStoreVos;
    }

    @Autowired
    private ICollectStoreDao iCollectStoreDao;

    @Autowired
    private IStoreDao iStoreDao;
}

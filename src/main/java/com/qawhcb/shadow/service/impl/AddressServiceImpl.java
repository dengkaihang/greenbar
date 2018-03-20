package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IAddressDao;
import com.qawhcb.shadow.entity.Address;
import com.qawhcb.shadow.service.IAddressService;
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
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class AddressServiceImpl implements IAddressService {


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iAddressDao.delete(id);
    }

    @Override
    public Address save(Address address) {
        return iAddressDao.save(address);
    }

    @Override
    public List<Address> findAll(Integer userId) {
        List<Address> addresses = iAddressDao.findAll(new Specification<Address>() {
            @Override
            public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                // 条件集合
                List<Predicate> re = new ArrayList<>(8);

                re.add(cb.equal(root.get("userId"), userId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        });

        return addresses;
    }

    @Autowired
    private IAddressDao iAddressDao;
}

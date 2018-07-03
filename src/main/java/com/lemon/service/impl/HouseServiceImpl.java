package com.lemon.service.impl;

import com.lemon.dao.IHouseDao;
import com.lemon.dao.IHouseImgDao;
import com.lemon.entity.House;
import com.lemon.entity.HouseImg;
import com.lemon.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kane on 18-7-2
 */
@Service
public class HouseServiceImpl implements IHouseService {

    @Autowired
    private IHouseDao iHouseDao;

    @Autowired
    private IHouseImgDao iHouseImgDao;

    @Override
    public List<House> findAll() {
        List<House> houses = iHouseDao.findAll();
        return houses;
    }

    @Override
    public House findOne(Integer id) {
        House house = iHouseDao.findOne(new Specification<House>(){

            @Override
            public Predicate toPredicate(Root<House> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.equal(root.get("id"), id));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });
        List<HouseImg> houseImgs = iHouseImgDao.findAll(new Specification<HouseImg>(){

            @Override
            public Predicate toPredicate(Root<HouseImg> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.equal(root.get("houseId"), id));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });
        if(houseImgs.size()>0){
            house.setImgs(houseImgs);
        }

        return house;
    }
}

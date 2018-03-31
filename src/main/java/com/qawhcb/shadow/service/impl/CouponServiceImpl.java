package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.ICouponDao;
import com.qawhcb.shadow.dao.IUseCouponDao;
import com.qawhcb.shadow.entity.Coupon;
import com.qawhcb.shadow.entity.UseCoupon;
import com.qawhcb.shadow.service.ICouponService;
import com.qawhcb.shadow.utils.GetLocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class CouponServiceImpl implements ICouponService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iCouponDao.delete(id);
    }

    @Override
    public Coupon publish(Coupon coupon) {

//        coupon.set

        return iCouponDao.save(coupon);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Coupon receive(Integer userId, Integer couponId) {

        // 查看创建的代金券数量，判断是否能够继续领取
        Coupon coupon = iCouponDao.findOne(couponId);

        // 判断优惠券是否被删除
        if ("true".equals(coupon.getIfDel())){
            return null;
        }

        Integer total = Integer.parseInt(coupon.getTotal());
        Integer num = Integer.parseInt(coupon.getNum());

        // 如果总数依然大于零，将用户及代金券构建保存
        if (total > 0) {
            // 构建代金券使用类
            UseCoupon useCoupon = new UseCoupon();

            useCoupon.setUserId(userId);
            useCoupon.setCouponId(couponId);

            // 构建优惠券领取时间，设置使用优惠券过期时间
            Calendar calendar = Calendar.getInstance();

            // 查询过期时间
            String effectiveTime = coupon.getEffectiveTime();
            Integer eff = Integer.parseInt(effectiveTime);

            // 格式化时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            calendar.add(Calendar.DAY_OF_YEAR, eff);
            Date date = calendar.getTime();

            useCoupon.setPeriodOfValidity(sdf.format(date));

            UseCoupon save = iUseCouponDao.save(useCoupon);
        }

        // 完成后，将总数-1
        coupon.setTitle(String.valueOf(total - 1));
        coupon.setNum(String.valueOf(num + 1));
        iCouponDao.save(coupon);

        // 返回领取到的代金券
        return iCouponDao.findOne(couponId);
    }

    @Override
    public List<Coupon> find(Integer userId) {

        List<UseCoupon> byUser = iUseCouponDao.findByUser(userId, GetLocalDateTime.getLocalDataTime());

        List<Coupon> coupons = new ArrayList<>(16);

        Coupon target = null;
        for (UseCoupon u :
                byUser) {
            target = new Coupon();

            Coupon src = iCouponDao.findOne(u.getCouponId());

            target.setTitle(src.getTitle());
            target.setMoney(src.getMoney());
            target.setAvailPrice(src.getAvailPrice());

            // 设置优惠券有效期
            target.setLable1(u.getPeriodOfValidity());

            coupons.add(target);
        }

        return coupons;
    }

    @Override
    public List<Coupon> findNotDel() {
        return iCouponDao.findNotDel();
    }

    @Autowired
    private ICouponDao iCouponDao;

    @Autowired
    private IUseCouponDao iUseCouponDao;
}


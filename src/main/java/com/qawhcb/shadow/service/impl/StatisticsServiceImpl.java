package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IOrderDao;
import com.qawhcb.shadow.dao.IStoreDao;
import com.qawhcb.shadow.dao.IUserDao;
import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Taoism <br/>
 * Created on 2018/3/28 <br/>
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {
    @Override
    public Map<String, Object> clientData() {

        Map<String, Object> map = new HashMap<>(8);

        map.put("user", iUserDao.findAll().size());
        map.put("store", iStoreDao.findAll().size());

        map.put("order", iOrderDao.findAll().size());

        map.put("successful", iOrderDao.findBySuccess().size());
        map.put("refund", iOrderDao.findByRefund().size());


        return map;
    }

    @Override
    public Map<String, Object> week() {
        Map<String, Object> map = new HashMap<>(8);

        // 统计每周数据
        Calendar calendar = Calendar.getInstance();

        // 设置时间为当前周的星期天
        calendar.set(Calendar.DAY_OF_WEEK, 1);


        // 将时间前移一周
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.DAY_OF_WEEK, 1);

        // 将一周数据封装
//        WeekData user = new WeekData();
//        WeekData store = new WeekData();
//        WeekData successOrder = new WeekData();
//        WeekData refundOrder = new WeekData();

        // 缓存查询记录

        List<Integer> tempUser = new ArrayList<>(8);
        List<Integer> tempStore = new ArrayList<>(8);
        List<Integer> tempSuccessOrder = new ArrayList<>(8);
        List<Integer> tempRefundOrder = new ArrayList<>(8);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 7; i++){

            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String date = sdf.format(calendar.getTime());

            // 时间区间为一天
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            String endDate = sdf.format(calendar.getTime());

            // 将时间还原为查询初始时间
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            List<User> byWeek = iUserDao.findByWeek(date, endDate);
            tempUser.add(byWeek.size());

            List<Store> byWeek1 = iStoreDao.findByWeek(date, endDate);
            tempStore.add(byWeek1.size());

            List<Order> byWeekSuccess = iOrderDao.findByWeekSuccess(date, endDate);
            tempSuccessOrder.add(byWeekSuccess.size());

            List<Order> byWeekRefund = iOrderDao.findByWeekRefund(date, endDate);
            tempRefundOrder.add(byWeekRefund.size());

        }

       map.put("user", tempUser);
       map.put("store", tempStore);
       map.put("successOrder", tempSuccessOrder);
       map.put("refundOrder", tempRefundOrder);

        return map;

    }


    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private IStoreDao iStoreDao;

    @Autowired
    private IOrderDao iOrderDao;

}

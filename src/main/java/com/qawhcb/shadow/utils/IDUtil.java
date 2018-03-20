package com.qawhcb.shadow.utils;

import java.text.SimpleDateFormat;

/**
 * 根据时间生成流水号
 * Created by kane on 18-1-26.
 */
public class IDUtil {

    /**
     * 生成流水
     * @return
     */
    public static String createId(){
        long s = System.currentTimeMillis();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmssSSSS");
        String time = dateformat.format(s);
        return time;
    }

    /**
     * 根据用户ID后两位生成订单Id
     * @param userId 用户id
     * @return 订单I
     *
     */
    public static String orderId(int userId){

        String prefix = "";

        String id =  String.valueOf(userId);

        prefix = id.length() < 2 ? "0"+id: id.substring(id.length()-2);

        return prefix+createId();
    }


}

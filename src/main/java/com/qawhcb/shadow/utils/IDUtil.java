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


}

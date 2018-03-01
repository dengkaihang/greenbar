package com.qawhcb.shadow.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


public class MD5Util {
    /*
     * MD5加密:摘要算法
     * 特点:任意长度字节处理成等长结果;不可逆
     * Base64:a-z A-Z 0-9 = +
     */
    public static String md5(String src) {
        try {
            MessageDigest md
                    = MessageDigest.getInstance("MD5");
            byte[] output
                    = md.digest(src.getBytes());
            String ret = Base64.encodeBase64String(output);
            return ret;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return src;
    }

    //UUID生成ID
    public static String createId() {
        String id
                = UUID.randomUUID().toString();
        return id;
		/*UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2]+idd[3]+idd[4];*/
    }
}










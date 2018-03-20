package com.qawhcb.shadow.utils.easemobUtils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.easemob.Message;
import com.qawhcb.shadow.entity.easemob.User;
import com.qawhcb.shadow.utils.HttpUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Taoism <br/>
 * Created on 2018/2/7 <br/>
 */
public class ImUtils {

    private static String orgName = "";
    private static String appName = "";
    private static String clientId = "";
    private static String clientSecret = "";

    static {
        InputStream is = ImUtils.class.getResourceAsStream("/properties/im.properties");

        Properties properties = new Properties();

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        orgName = properties.getProperty("org_name");
        appName = properties.getProperty("app_name");
        clientId = properties.getProperty("client_id");
        clientSecret = properties.getProperty("client_secret");
    }


    /**
     * 发送信息给指定用户
     * @param users
     * @param message
     */
    public static void send(String[] users, String message) {

//        InputStream is = ImController.class.getResourceAsStream("/properties/im.properties");
//
//        Properties properties = new Properties();
//
//        try {
//            properties.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String orgName = properties.getProperty("org_name");
//        String appName = properties.getProperty("app_name");
//        String clientId = properties.getProperty("client_id");
//        String clientSecret = properties.getProperty("client_secret");

        String token = getToken(orgName, appName, clientId, clientSecret);

        //  String[] users = new java.lang.String[]{"15180047865"};

        Message msg = new Message();

        msg.setType("txt");
        msg.setMsg(message);

        Map<String, Object> parameter = new HashMap<>(16);
        parameter.put("target_type", "users");
        parameter.put("target", users);
        parameter.put("msg", msg);

        String url = "http://a1.easemob.com/" + orgName + "/" + appName + "/messages";

        HttpUtils.httpRequest(url, "POST", JSONArray.toJSONString(parameter), "Bearer " + token);

    }

    private static String getToken(String orgName, String appName, String clientId, String clientSecret) {
        Map<String, Object> parameter = new HashMap<>(16);

        String url = "http://a1.easemob.com/" + orgName + "/" + appName + "/token";
        // 环信必要参数
        parameter.put("grant_type", "client_credentials");

        parameter.put("client_id", clientId);
        parameter.put("client_secret", clientSecret);

        String post = HttpUtils.httpRequest(url, "POST", JSONArray.toJSONString(parameter));

        Map mapType = JSON.parseObject(post, Map.class);

        return (String) mapType.get("access_token");
    }

    /**
     * 发送给所有人
     *
     * @param limit   最多的人数
     * @param message 发送的信息
     * @return 发送结果
     */
    public static String[] sendToAllUser(String limit, String message) {

        String token = getToken(orgName, appName, clientId, clientSecret);


        String url = "http://a1.easemob.com/" + orgName + "/" + appName + "/users?limit=" + limit;

        String post = HttpUtils.httpRequest(url, "GET", null, "Bearer " + token);

        Map mapType = JSON.parseObject(post, Map.class);

//        JSONArray entities = (JSONArray) mapType.get("entities");


        List<Map<String, Object>> mapListJson = (List) mapType.get("entities");

        ArrayList<String> users = new ArrayList<>(16);
        String[] userNames = new String[20];
        for (Map<String, Object> map :
                mapListJson) {

            String username = (String) map.get("username");

            users.add(username);

            // 最多一次发送20人
            if (users.size() == 20) {

                send(users.toArray(userNames), message);

                System.out.println("发送一次：" + users.size());
                users.clear();

                System.out.println("清除后：" + users.size());
            }

        }
        if (users.size() != 0) {
            send(users.toArray(userNames), message);
            System.out.println("最后一次：" + users.size());
            users.clear();

            System.out.println("清除后：" + users.size());
        }


        return null;
    }


    /**
     * 环信用户注册
     * @param username 注册用户名
     * @return 请求结果
     */
    public static String register(String username) {

        String token = getToken(orgName, appName, clientId, clientSecret);

        String url = "http://a1.easemob.com/" + orgName + "/" + appName + "/users";

        User user = new User();
        user.setUsername(username);
        user.setPassword("12345678");


        String post = HttpUtils.httpRequest(url, "POST", JSONArray.toJSONString(user), "Bearer " + token);

        return post;
    }
}

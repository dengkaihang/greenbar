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
     *
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

        // 获取此环信的所有账户
        String post = HttpUtils.httpRequest(url, "GET", null, "Bearer " + token);

        // 将响应信息转换为map集合
        Map mapType = JSON.parseObject(post, Map.class);

        // 从map集合中获取用户id， 环信接口将用户信息封装到 entities 中
        List<Map<String, Object>> mapListJson = (List) mapType.get("entities");

        // 因为环信推荐一次最多发送给20个用户，所以将用户拆分
        ArrayList<String> users = new ArrayList<>(16);
        String[] userNames = new String[20];
        for (Map<String, Object> map :
                mapListJson) {

            // 环信用户id即用户名
            String username = (String) map.get("username");

            users.add(username);

            // 最多一次发送20人
            if (users.size() == 20) {

                // 每满20个用户发送一次
                send(users.toArray(userNames), message);

                System.out.println("发送一次：" + users.size());
                users.clear();

                // 发送或将用户清除
                System.out.println("清除后：" + users.size());
            }

        }

        // 最后一次可能没满20个，用户数组不为空，再发送一次
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
     *
     * @param username 注册用户名
     * @return 请求结果
     */
    public static String register(String username) {

        String token = getToken(orgName, appName, clientId, clientSecret);

        String url = "http://a1.easemob.com/" + orgName + "/" + appName + "/users";

        User user = new User();
        user.setUsername(username);
        user.setPassword("12345678");

        return HttpUtils.httpRequest(url, "POST", JSONArray.toJSONString(user), "Bearer " + token);
    }

    /**
     * 环信用户添加好友
     *
     * @param owner  添加好友的用户名
     * @param friend 被添加的用户名
     * @return 请求结果
     */
    public static String follow(String owner, String friend) {

        String token = getToken(orgName, appName, clientId, clientSecret);

        String url = "http://a1.easemob.com/" + orgName + "/" + appName + "/users/" + owner + "/contacts/users/" + friend;

        return HttpUtils.httpRequest(url, "POST", null, "Bearer " + token);
    }

    /**
     * 查询某个用户的好友
     *
     * @param userId 用户id
     * @return 所有好友信息
     */
    public static List<String> contacts(String userId) {
        String token = getToken(orgName, appName, clientId, clientSecret);

        String url = "http://a1.easemob.com/" + orgName + "/" + appName + "/users" + "/" + userId + "/contacts/users";

        String post = HttpUtils.httpRequest(url, "GET", null, "Bearer " + token);

        // 将响应信息转换为map集合
        Map mapType = JSON.parseObject(post, Map.class);

        /*
         * 环信响应体
         *{
         *    "action": "get",
         *    "uri": "http://a1.easemob.com/1165170823115932/lemeng1/users/15180047865/contacts/users",
         *    "entities": [],
         *    "data": [
         *      "15180047853",
         *      "15180047852"
         *    ],
         *    "timestamp": 1521698681516,
         *    "duration": 5,
         *    "count": 1
         * }
         */

        // 从集合中获取封装的数据 data
//        List<String> contacts = (List<String>) mapType.get("data");

        return (List<String>) mapType.get("data");
    }
}

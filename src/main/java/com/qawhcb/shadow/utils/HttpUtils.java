
package com.qawhcb.shadow.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Taoism <br/>
 * Created on 2018/2/7 <br/>
 */
public class HttpUtils {

    private static final String POST = "POST";

    /**
     * post请求并得到返回结果(以utf-8编码)
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式
     * @param output        请求参数
     * @return 请求响应
     */
    public static String httpRequest(String requestUrl, String requestMethod, String output) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        // 请求响应缓存
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建一个URL连接对象
            URL url = new URL(requestUrl);

            // 开启连接
            connection = (HttpURLConnection) url.openConnection();

            // 发送POST请求必须设置如下两行
            if (HttpUtils.POST.equalsIgnoreCase(requestMethod)) {
                connection.setDoOutput(true);
                connection.setDoInput(true);
            }

            // 设置忽略缓存
            connection.setUseCaches(false);

            // 设置请求方法
            connection.setRequestMethod(requestMethod);

            // 如果参数不为null 则使用指定编码格式进行编码
            if (null != output) {
                // 获取连接输出对象
                OutputStream outputStream = connection.getOutputStream();
                // 从向连接中输入请求参数，以指定格式编码
                outputStream.write(output.getBytes("UTF-8"));

                outputStream.close();
            }

            // 从连接对象中获取输入流，并读取返回内容
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            // 关闭所开各种流对象
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return buffer.toString();
    }

    /**
     * post请求并得到返回结果(以utf-8编码)
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式
     * @param output        请求参数
     * @param token         请求token (会将请求token放入请求头中)
     * @return 请求响应
     */
    public static String httpRequest(String requestUrl, String requestMethod, String output, String token) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        // 请求响应缓存
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建一个URL连接对象
            URL url = new URL(requestUrl);

            // 开启连接
            connection = (HttpURLConnection) url.openConnection();

            // 发送POST请求必须设置如下两行
            if (HttpUtils.POST.equalsIgnoreCase(requestMethod)) {
                connection.setDoOutput(true);
                connection.setDoInput(true);
            }

            // 设置忽略缓存
            connection.setUseCaches(false);

            // 设置请求方法
            connection.setRequestMethod(requestMethod);

            connection.setRequestProperty("Authorization", token);

            // 如果参数不为null 则使用指定编码格式进行编码
            if (null != output) {
                // 获取连接输出对象
                OutputStream outputStream = connection.getOutputStream();
                // 从向连接中输入请求参数，以指定格式编码
                outputStream.write(output.getBytes("UTF-8"));

                outputStream.close();
            }

            // 从连接对象中获取输入流，并读取返回内容
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            // 关闭所开各种流对象
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return buffer.toString();
    }
}

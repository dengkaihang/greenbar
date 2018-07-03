package com.lemon.utils;

import java.io.*;
import java.net.*;
import java.util.Properties;


public class SMSUtils {

    private static String NAME;
    private static String PWD;
    private static String SIGN;

    static {
        InputStream is = SMSUtils.class.getResourceAsStream("/properties/sms.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            LoggerUtil.getLogger().error("加载配置文件失败：" + e.getMessage());
        }

        NAME = properties.getProperty("NAME");
        PWD = properties.getProperty("PWD");
        SIGN = properties.getProperty("SIGN");
    }

    /*
     * 发送短信
     *
     * @param name 用户名
     *
     * @param pwd 密码
     *
     * @param mobileString 电话号码字符串，中间用英文逗号间隔
     *
     * @param contextString 内容字符串
     *
     * @param sign 签名
     *
     * @param stime 追加发送时间，可为空，为空为及时发送
     *
     * @param extno 扩展码，必须为数字 可为空
     *
     * @return
     */


    /**
     * 发送短信，按照stime时间发送
     *
     * @param mobileString  电话号码字符串，中间用英文逗号间隔
     * @param contextString 内容字符串
     * @param stime         追加发送时间
     * @param extno         扩展码，必须为数字 可为空
     * @return
     */
    public static String send(String mobileString, String contextString, String stime, String extno) throws Exception {
        StringBuffer param = new StringBuffer();
        param.append("name=" + NAME);
        param.append("&pwd=" + PWD);
        param.append("&mobile=").append(mobileString);
        param.append("&content=").append(URLEncoder.encode(contextString, "UTF-8"));
        param.append("&stime=" + stime);
        // param.append("&sign=").append(URLEncoder.encode(SIGN, "UTF-8"));
        param.append("&type=pt");
        param.append("&extno=").append(extno);

        String resultBuffer = sendCode(param);

        return resultBuffer;
    }

    /**
     * 发送验证码短，即时发送
     *
     * @param mobileString  电话号码字符串，中间用英文逗号间隔
     * @param contextString 内容字符串
     * @param extno         扩展码，必须为数字 可为空
     * @return
     */
    public static String send(String mobileString, String contextString, String extno) throws Exception {
        StringBuffer param = new StringBuffer();
        param.append("name=" + NAME);
        param.append("&pwd=" + PWD);
        param.append("&mobile=").append(mobileString);
        param.append("&content=").append(URLEncoder.encode(contextString, "UTF-8"));
        // param.append("&sign=").append(URLEncoder.encode(SIGN, "UTF-8"));
        param.append("&type=pt");
        param.append("&extno=").append(extno);

        String resultBuffer = sendCode(param);

        return resultBuffer;
    }

    /**
     * 发送验证码短，即时发送
     *
     * @param mobileString  电话号码字符串，中间用英文逗号间隔
     * @param contextString 内容字符串
     * @return 发送结果
     */
    public static String send(String mobileString, String contextString) throws Exception {
        StringBuffer param = new StringBuffer();
        param.append("name=" + NAME);
        param.append("&pwd=" + PWD);
        param.append("&mobile=").append(mobileString);
        param.append("&content=").append(URLEncoder.encode(contextString, "UTF-8"));
        // param.append("&sign=").append(URLEncoder.encode(SIGN, "UTF-8"));
        param.append("&type=pt");

        String resultBuffer = sendCode(param);

        return resultBuffer;
    }

    /**
     * 将封装好的参数发送到第三方平台
     *
     * @param param 参数集
     * @return 发送结果
     * @throws MalformedURLException
     * @throws IOException
     * @throws ProtocolException
     * @throws Exception
     */
    private static String sendCode(StringBuffer param)
            throws MalformedURLException, IOException, ProtocolException, Exception {
        URL localURL = new URL("http://web.1xinxi.cn/asmx/smsservice.aspx?");
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        String resultBuffer = "";

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            resultBuffer = convertStreamToString(inputStream);

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }
        return resultBuffer;
    }

    /**
     * 转换返回值类型为UTF-8格式.
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {

            LoggerUtil.getLogger().error("获取验证码失败！" + e.getMessage());

            throw new RuntimeException();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LoggerUtil.getLogger().error("输入流关闭失败！" + e.getMessage());
                throw new RuntimeException();
            }
        }
        return sb1.toString();
    }

}

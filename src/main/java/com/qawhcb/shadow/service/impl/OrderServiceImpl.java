package com.qawhcb.shadow.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.qawhcb.shadow.dao.*;
import com.qawhcb.shadow.entity.*;
import com.qawhcb.shadow.entity.dataModel.OrderVo;
import com.qawhcb.shadow.entity.weixin.Unifiedorder;
import com.qawhcb.shadow.service.IOrderService;
import com.qawhcb.shadow.utils.*;
import com.qawhcb.shadow.utils.alipayUtils.AlipayConfigUtils;
import com.qawhcb.shadow.utils.easemobUtils.ImUtils;
import com.qawhcb.shadow.utils.weixinUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) throws Exception {

        // 取消前判断其状态 必须为待付款

        Order order = iOrderDao.findOne(id);
        if (! "dfk".equals(order.getStatus()) || !order.getStatus().contains("wc")){
            throw new RuntimeException("订单已付款或未完成，不能取消！");
        }


        iOrderDao.delete(id);
    }

    @Override
    public List<Order> getAll(Integer goodsId) {
        return iOrderDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(16);

                re.add(cb.equal(root.get("goodsId"), goodsId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        });
    }

    @Override
    public Order save(Order order) {

        order.setId(IDUtil.orderId(order.getUserId()));
        order.setStatus("dfk");

        // 查询当前套餐售价
        Pack pack = iPackDao.findOne(order.getPackId());

        // 获取套餐数量和价格
        double num = Double.parseDouble(order.getNum());
        double price = Double.parseDouble(pack.getPrice());

        if (num > 0 && price > 0) {
            // 判断用户是否使用优惠券
            if (order.getCouponId() != null && !"".equals(order.getCouponId())) {
                Coupon coupon = iCouponDao.findOne(order.getCouponId());

                Integer availPrice = Integer.parseInt(coupon.getAvailPrice());

                double orderPrice = num * price;

                // 如果符合优惠券使用规则，测将优惠券设置
                if (orderPrice >= availPrice) {
                    // 设置初始价格为计算结果
                    order.setInitialPrice(String.valueOf(orderPrice));

                    // 设置使用优惠券后的实际交易金额
                    double oPrice = orderPrice - Integer.parseInt(coupon.getMoney());

                    order.setPrice(String.valueOf(oPrice));
                } else {
                    order.setPrice(String.valueOf(num * price));
                }

            } else {
                order.setPrice(String.valueOf(num * price));
            }
        }

        // 设置订单创建时间
        order.setCreateTime(GetLocalDateTime.getLocalDataTime());

        return iOrderDao.save(order);
    }

    @Override
    public List<Order> findAll(Integer userId) {
        return iOrderDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(16);

                re.add(cb.equal(root.get("userId"), userId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        });

    }

    @Override
    public Order uploadFile(String orderId, MultipartFile[] files) {

        Order order = iOrderDao.findOne(orderId);

        String fileNames = UploadFileUtils.orderFileUpload(files, String.valueOf(orderId));

        order.setOriginalFile(fileNames);

        return iOrderDao.save(order);
    }

    @Override
    public List<OrderVo> findAllByStore(Integer storeId, Integer page) {
        Page<Order> orderPage = iOrderDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(16);

                re.add(cb.equal(root.get("storeId"), storeId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        }, new PageRequest(page, 6));

        List<OrderVo> orderVos = new ArrayList<>(16);

        getOrderVos(orderPage, orderVos);

        return orderVos;
    }

    // 封装orderVos
    private void getOrderVos(Page<Order> orderPage, List<OrderVo> orderVos) {
        OrderVo orderVo;
        for (Order order :
                orderPage.getContent()) {

            orderVo = new OrderVo();

            orderVo.setOrder(order);

            // 封装套餐详情
            Pack pack = iPackDao.findOne(order.getPackId());
            orderVo.setPackDepict(pack.getDepict());

            // 设置商品名称
            Goods goods = iGoodsDao.findOne(order.getGoodsId());
            orderVo.setGoodsName(goods.getName());

            // 设置买家手机号
            User user = iUserDao.findOne(order.getUserId());
            orderVo.setUserPhone(user.getPhone());

            orderVos.add(orderVo);
        }
    }

    @Override
    public List<OrderVo> findByStatus(Integer userId, String status, Integer page, Integer size) {
        Page<Order> orderPage = iOrderDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(16);

                re.add(cb.equal(root.get("userId"), userId));

                if (status.contains("wx")) {
                    re.add(cb.like(root.get("status"), "%wc%"));
                } else {
                    re.add(cb.equal(root.get("status"), status));
                }

                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        }, new PageRequest(page, size));

        List<OrderVo> orderVos = new ArrayList<>(16);

        OrderVo orderVo = null;
        for (Order order :
                orderPage.getContent()) {

            orderVo = new OrderVo();

            orderVo.setOrderId(order.getId());
            orderVo.setNum(order.getNum());

            Goods goods = iGoodsDao.findOne(order.getGoodsId());
            orderVo.setGoodsDepict(goods.getDepict());

            String[] split = goods.getCover().split(",");
            orderVo.setGoodsImg(split.length > 0 ? split[0] : null);

            Pack pack = iPackDao.findOne(order.getPackId());
            orderVo.setPackDepict(pack.getDepict());
            orderVo.setPackPrice(pack.getPrice());

            orderVos.add(orderVo);
        }

        return orderVos;
    }

    @Override
    public OrderVo findOne(String orderId) {

        Order order = iOrderDao.findOne(orderId);

        Store store = iStoreDao.findOne(order.getStoreId());

        OrderVo orderVo = new OrderVo();

        orderVo.setOrder(order);
        orderVo.setPhone(store.getPhone());

        return orderVo;
    }

    @Override
    public Order findOneInStore(String orderId) {
        return iOrderDao.findOne(orderId);
    }

    @Override
    public String pay(Order order) {

        Order target = iOrderDao.findOne(order.getId());

        UpdateUtils.copyNonNullProperties(order, target);

        // 支付宝支付接入...

        if (WECHAT.equals(target.getPayWay())) {

            // 配置微信支付
            String appId = WeixinConfigUtils.app_id;
            String mchId = WeixinConfigUtils.mch_id;
            // 微信支付回调
            String notifyUrl = WeixinConfigUtils.notify_url;

            // 微信支付随机字符串
            String nonceStr = RandCharsUtils.getRandomString(16);

            String body = "爱影动萌-订单支付";
            String detail = "订单支付，支付" + target.getPrice() + "元";
            String attach = "备用参数";

            String outTradeNo = target.getId();

            int totalFee = (int) (Float.parseFloat(target.getPrice()) * 100);
            String spbillCreateIp = "127.0.0.1";

            // 配置订单开始时间
            String timeStart = RandCharsUtils.timeStart();
            // 配置订单结束时间
            String timeExpire = RandCharsUtils.timeExpire();


            String tradeType = "APP";

            // 参数：开始生成签名
            SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
            parameters.put("appid", appId);
            parameters.put("mch_id", mchId);
            parameters.put("nonce_str", nonceStr);
            parameters.put("body", body);
            // parameters.put("nonce_str", nonce_str);
            parameters.put("detail", detail);
            parameters.put("attach", attach);
            parameters.put("out_trade_no", outTradeNo);
            parameters.put("total_fee", totalFee);

            parameters.put("time_start", timeStart);
            parameters.put("time_expire", timeExpire);

            parameters.put("notify_url", notifyUrl);
            parameters.put("trade_type", tradeType);
            parameters.put("spbill_create_ip", spbillCreateIp);

            // 按照微信要求最后加上key----->

            String sign = WXSignUtils.createSign("UTF-8", parameters);
            System.out.println("签名是：" + sign);

            Unifiedorder unifiedorder = new Unifiedorder();
            unifiedorder.setAppid(appId);
            unifiedorder.setMch_id(mchId);
            unifiedorder.setNonce_str(nonceStr);
            unifiedorder.setSign(sign);
            unifiedorder.setBody(body);
            unifiedorder.setDetail(detail);
            unifiedorder.setAttach(attach);
            unifiedorder.setOut_trade_no(outTradeNo);
            unifiedorder.setTotal_fee(totalFee);
            unifiedorder.setSpbill_create_ip(spbillCreateIp);

            unifiedorder.setTime_start(timeStart);
            unifiedorder.setTime_expire(timeExpire);

            unifiedorder.setNotify_url(notifyUrl);
            unifiedorder.setTrade_type(tradeType);

            // 构造xml参数
            String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);

            System.out.println("WeixinPayTestController.testOrder()-->" + xmlInfo);

            String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

            String method = "POST";

            String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo);

            System.out.println("OrderService.orderPay()--->" + weixinPost);

            return weixinPost;
            // return
            // "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wxc2222fa595265736]]></appid><mch_id><![CDATA[1490420532]]></mch_id><nonce_str><![CDATA[D4uS7ixTqqS55wPl]]></nonce_str><sign><![CDATA[C04913EB85D6810F7BD1D7C65B2AD896]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx2017120421552589240977230601038643]]></prepay_id><trade_type><![CDATA[APP]]></trade_type></xml>";

        } else if (ALIPAY.equals(target.getPayWay())) {

            String notifyUrl = AlipayConfigUtils.notify_url;

            AlipayClient alipayClient = getAlipayClient();

            // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

            // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

            // 设置订单描述
            model.setBody("爱影动萌，订单支付");
            // 设置订单标题
            model.setSubject("爱影动萌");
            // 设置订单号
            model.setOutTradeNo(order.getId());
            // 设置超时时间（默认为30分钟）
            model.setTimeoutExpress("30m");
            // 设置订单金额
            model.setTotalAmount(order.getPrice());

            // 设置产品代码
            model.setProductCode("QUICK_MSECURITY_PAY");

            request.setBizModel(model);

            // 商户外网可以访问的异步地址
            request.setNotifyUrl(notifyUrl);

            try {
                // 这里和普通的接口调用不同，使用的是sdkExecute
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
                // 就是orderString 可以直接给客户端请求，无需再做处理。
                System.out.println(response.getBody());
                return response.getBody();
            } catch (AlipayApiException e) {
                LoggerUtil.getLogger().error(e.getErrMsg());
            }

        }

        // 如果支付成功
        if (true) {

            target.setStatus("jxz");

            iOrderDao.save(target);
            return "";
        } else {
            return "";
        }

//        return "";
    }

    // 获取支付宝客户端实例
    private AlipayClient getAlipayClient() {
        String charset = AlipayConfigUtils.charset;
        String aliWebGateway = AlipayConfigUtils.ali_web_gateway;
        String aliAppId = AlipayConfigUtils.ali_app_id;
        String aliAppPrivateKey = AlipayConfigUtils.ali_app_private_key;
        String aliAlipayPublicKey = AlipayConfigUtils.ali_alipay_public_key;

        // 实例化支付客户端
        return new DefaultAlipayClient(aliWebGateway, aliAppId, aliAppPrivateKey, "json", charset, aliAlipayPublicKey, "RSA2");
    }

    @Override
    public void fruitAli(HttpServletRequest request) {

        // 遍历请求参数，并封装为map
        Map<String, String> params = new HashMap<>(8);
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    params.put(paramName, paramValue);
                }
            }
        }

        // 使用ali给定的API判断支付结果信息     !!!!!!!!!! 实测有问题 !!!!!!!!
        boolean payStatus = false;
        try {
            payStatus = AlipaySignature.rsaCheckV1(params, AlipayConfigUtils.ali_alipay_public_key, AlipayConfigUtils.charset);
        } catch (AlipayApiException e1) {
            LoggerUtil.getLogger().error("支付失败-->错误代码:" + e1.getErrCode() + "--错误信息:" + e1.getMessage());

            // 判断异常信息是否包含trade_status=TRADE_SUCCESS，如果包含，默认为成功。 吉诗平
            if (e1.getMessage().contains(ALI_PAY_SUCCESS)) {
                payStatus = true;
            }
        }

        // 如果支付成功，修改订单付款状态
        if (payStatus) {

            Order order = iOrderDao.findOne(params.get("out_trade_no"));

            order.setPayTime(GetLocalDateTime.getLocalDataTime());
            order.setStatus("dpj");

            iOrderDao.save(order);
        }
    }

    @Override
    public String fruitWx(HttpServletRequest request) {
        try {
            InputStream inStream = request.getInputStream();
            int bufferSize = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[bufferSize];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, bufferSize)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();
                // 将流转换成字符串
                String result = new String(outStream.toByteArray(), "UTF-8");
                // 将字符串解析成XML
                // Document doc = DocumentHelper.parseText(result);
                // 将XML格式转化成MAP格式数据

                Map<String, String> jdomParseXml = ParseXMLUtils.jdomParseXml(result);

                // Map<String, Object> resultMap = XmlMapHandle.Dom2Map(doc);

                if (jdomParseXml != null) {
                    // 判断appid和mch_id
                    if (WeixinConfigUtils.app_id.equals(jdomParseXml.get("appid"))
                            && WeixinConfigUtils.mch_id.equals(jdomParseXml.get("mch_id"))) {

                        if (WX_PAY_SUCCESS.equalsIgnoreCase(jdomParseXml.get("result_code"))) {

                            Order order = iOrderDao.findOne(jdomParseXml.get("out_trade_no"));

                            order.setPayTime(GetLocalDateTime.getLocalDataTime());
                            order.setStatus("dpj");

                            iOrderDao.save(order);
                        }
                    }
                }


            }
            // 通知微信支付系统接收到信息
            return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LoggerUtil.getLogger().error("微信支付通知接收失败！");
        }

        // 如果失败返回错误，微信会再次发送支付信息
        return "fail";
    }

    @Override
    public String refund(String orderId) {

        Order order = iOrderDao.findOne(orderId);

        // 如果订单状态为待付款或者退款完成，直接取消退款操作
        if ("dfk".equals(order.getStatus()) || "tkwc".equals(order.getStatus())) {
            return "fail";
        }

        // 判断支付方式，调用不同的退款接口
        if (ALIPAY.equals(order.getPayWay())) {

            AlipayClient alipayClient = getAlipayClient();

            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

            Map<String, Object> params = new HashMap<>();

            params.put("out_trade_no", order.getId());
            params.put("refund_amount", Float.parseFloat(order.getPrice()));
            params.put("refund_reason", "取消订单");
            /*
             * params.put("out_trade_no", order.getOrderId()); params.put("out_trade_no",
             * order.getOrderId()); params.put("out_trade_no", order.getOrderId());
             */

            request.setBizContent(JSONArray.toJSONString(params));

            /*
             * request.setBizContent("{" + "\"out_trade_no\":\"20150320010101001\"," +
             * "\"trade_no\":\"2014112611001004680073956707\"," +
             * "\"refund_amount\":200.12," + "\"refund_reason\":\"正常退款\"," +
             * "\"out_request_no\":\"HZ01RF001\"," + "\"operator_id\":\"OP001\"," +
             * "\"store_id\":\"NJ_S_001\"," + "\"terminal_id\":\"NJ_T_001\"" + "  }");
             */

            AlipayTradeRefundResponse response = null;
            try {
                response = alipayClient.execute(request);
            } catch (AlipayApiException e) {
                LoggerUtil.getLogger().error("支付宝退款异常！" + e.getMessage());
                return "fail";
            }
            if (!response.isSuccess()) {
                LoggerUtil.getLogger().error("支付宝退款失败！订单Id" + order.getId());
                return "fail";
            }

        } else if (WECHAT.equals(order.getId())) {
            String appid = WeixinConfigUtils.app_id;
            // System.out.println("appid是：" + appid);

            String mch_id = WeixinConfigUtils.mch_id;
            // System.out.println("mch_id是：" + mch_id);

            String nonce_str = RandCharsUtils.getRandomString(16);
            // System.out.println("随机字符串是：" + nonce_str);

            String outTradeNo = order.getId();

            String outRefundNo = outTradeNo;

            int totalFee = (int) (Float.parseFloat(order.getPrice()) * 100);

            int refundFee = totalFee;

            /*
             * String spbill_create_ip = "127.0.0.1"; String time_start =
             * RandCharsUtils.timeStart(); System.out.println(time_start); String
             * time_expire = RandCharsUtils.timeExpire(); System.out.println(time_expire);
             *
             * String notify_url = WeixinConfigUtils.notify_url;
             * System.out.println("notify_url是：" + notify_url); String trade_type = "APP";
             */
            // 参数：开始生成签名
            SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
            parameters.put("appid", appid);
            parameters.put("mch_id", mch_id);
            parameters.put("nonce_str", nonce_str);

            parameters.put("out_trade_no", outTradeNo);
            parameters.put("out_refund_no", outRefundNo);
            parameters.put("total_fee", totalFee);
            parameters.put("refund_fee", refundFee);

            // 按照微信要求最后加上key--在生成工具中有添加--->

            String sign = WXSignUtils.createSign("UTF-8", parameters);
            // System.out.println("签名是：" + sign);

            parameters.put("sign", sign);

            // 构造xml参数
            String xmlInfo = HttpXmlUtils.xmlInfo(parameters);

            // System.out.println("WeixinPayTestController.testOrder()-->" + xmlInfo);

            String wxUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";

            String method = "POST";

            String weixinPost = null;
            try {
                weixinPost = ClientCustomSSL.doRefund(wxUrl, xmlInfo);
            } catch (Exception e) {
                LoggerUtil.getLogger().error("微信退款失败");
            }

            Map<String, String> jdomParseXml = ParseXMLUtils.jdomParseXml(weixinPost);

            if (jdomParseXml != null) {
                if ("fail".equalsIgnoreCase(jdomParseXml.get("return_code"))) {
                    return "fail";
                }
            }
        }

        order.setStatus("tkwc");
        order.setRefundTime(GetLocalDateTime.getLocalDataTime());

        iOrderDao.save(order);

        return "success";
    }

    @Override
    public String askRefund(String orderId, String cause) {

        // 发送系统消息给商户，商户处理

        Order order = iOrderDao.findOne(orderId);

        order.setRefundReason(cause);

        order.setStatus("dtk");

        iOrderDao.save(order);

        return "已提交退款申请";
    }

    @Override
    public Order finish(String orderId) {

        Order order = iOrderDao.findOne(orderId);

        order.setStatus("dpj");

        order.setFulfilTime(GetLocalDateTime.getLocalDataTime());

        // 订单完成，店铺得分
        Store store = iStoreDao.findOne(order.getStoreId());

        int score = store.getScore();
        store.setScore(score + 1);

        iStoreDao.save(store);

        return iOrderDao.save(order);

    }

    @Override
    public Order update(Order src) {

        Order targer = iOrderDao.findOne(src.getId());

        UpdateUtils.copyNonNullProperties(src, targer);

        return iOrderDao.save(targer);
    }

    @Override
    public Order refuse(String orderId, String cause) {

        Order order = iOrderDao.findOne(orderId);

        order.setRefuseReason(cause);

        order.setIsSubmit("true");

        // 构建退款消息发送人员数组
        User user = iUserDao.findOne(order.getUserId());
        String[] users = new String[]{user.getPhone()};

        String msg = "您的退款要求被回绝，回绝的原因是" + cause + "。如不满意处理，后续请联系客服！";

        // 发送系统消息到客户
        ImUtils.send(users, msg);

        return iOrderDao.save(order);
    }

    @Override
    public Order result(String orderId, MultipartFile[] files) {

        String names = UploadFileUtils.orderFileUpload(files, orderId + "/result");

        Order order = iOrderDao.findOne(orderId);

        order.setNewFile(names);

        return iOrderDao.save(order);
    }

    @Override
    public List<OrderVo> sotreFindByStatus(Integer storeId, String status, Integer page, Integer size) {
        Page<Order> orderPage = iOrderDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(16);

                re.add(cb.equal(root.get("storeId"), storeId));

                if (status.contains("wx")) {
                    re.add(cb.like(root.get("status"), "%wc%"));
                } else {
                    re.add(cb.equal(root.get("status"), status));
                }

                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        }, new PageRequest(page, size));

        List<OrderVo> orderVos = new ArrayList<>(16);

        getOrderVos(orderPage, orderVos);

        return orderVos;
    }

    @Override
    public List<Order> findBySubmit(Integer page) {

        Page<Order> all = iOrderDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(8);

                re.add(cb.equal(root.get("isSubmit"), "true"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        }, new PageRequest(page, 5));

        if (all.getContent().size() > 0) {
            all.getContent().get(0).setLabel1(String.valueOf(all.getTotalPages()));
        }

        return all.getContent();

    }

    @Override
    public String oaRefund(String account, String orderId) {
        String refund = refund(orderId);

        if ("success".equals(refund)) {
            Order order = iOrderDao.findOne(orderId);

            order.setEmployeeAccount(account);

            iOrderDao.save(order);
        }

        return refund;
    }

    @Override
    public Order oaRefuse(String account, String orderId, String cause) {
        Order order = iOrderDao.findOne(orderId);

        String refuseReason = order.getRefuseReason();

        order.setRefuseReason(refuseReason + ", 后台处理为拒绝订单。理由为：" + cause);

        Order save = iOrderDao.save(order);

        // 构建退款消息发送人员数组
        User user = iUserDao.findOne(order.getUserId());
        String[] users = new String[]{user.getPhone()};

        String msg = "您的退款要求被平台回绝，回绝的原因是" + cause + "。如不满意处理，请联系平台！";

        // 发送系统消息到客户
        ImUtils.send(users, msg);

        return save;
    }


    @Autowired
    private IOrderDao iOrderDao;

    @Autowired
    private IPackDao iPackDao;

    @Autowired
    private IGoodsDao iGoodsDao;

    @Autowired
    private IStoreDao iStoreDao;

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private ICouponDao iCouponDao;

    private final String ALI_PAY_SUCCESS = "trade_status=TRADE_SUCCESS";
    private final String WX_PAY_SUCCESS = "success";

    private final String ALIPAY = "alipay";
    private final String WECHAT = "wechat";
    private final String QQ = "qq";
}

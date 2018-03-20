package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.entity.dataModel.OrderVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IOrderService {

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    public void delete(String id);

    /**
     * 通过商品查询所有订单
     *
     * @param goodsId 商品id
     * @return 所有相关订单
     */
    public List<Order> getAll(Integer goodsId);

    /**
     * 新建订单
     *
     * @param order 订单对象
     * @return 添加成功的订单
     */
    public Order save(Order order);

    /**
     * 查询用户所有订单
     *
     * @param userId 当前用户id
     * @return 所有订单
     */
    public List<Order> findAll(Integer userId);

    /**
     * 订单文件上传
     *
     * @param orderId 文件上传到的订单
     * @param files   要上传的文件
     * @return 合成后的订单
     */
    public Order uploadFile(String orderId, MultipartFile[] files);

    /**
     * 查询店铺所有订单
     *
     * @param storeId 当前用户id
     * @param page    查询页数
     * @return 所有订单
     */
    public List<OrderVo> findAllByStore(Integer storeId, Integer page);

    /**
     * 按订单状态查询用户订单
     *
     * @param userId 当前用户id
     * @param status 订单状态
     * @param page   当前页
     * @param size   分页大小
     * @return 查询订单
     */
    public List<OrderVo> findByStatus(Integer userId, String status, Integer page, Integer size);

    /**
     * 查询单个订单详情
     *
     * @param orderId 订单Id
     * @return 查询到的订单
     */
    public OrderVo findOne(String orderId);

    /**
     * 查询商铺单个订单详情
     *
     * @param orderId 订单Id
     * @return 查询到的订单
     */
    public Order findOneInStore(String orderId);

    /**
     * 订单支付
     *
     * @param order order
     * @return 支付结果
     */
    public String pay(Order order);

    /**
     * 支付宝支付接口回调
     *
     * @param request 请求参数
     */
    public void fruitAli(HttpServletRequest request);

    /**
     * 微信支付接口回调
     *
     * @param request 请求参数
     */
    public String fruitWx(HttpServletRequest request);

    /**
     * 订单退款
     *
     * @param orderId 要退款的订单id
     * @return 退款结果
     */
    public String refund(String orderId);

    /**
     * 用户发起退款
     *
     * @param orderId 要申请退款的订单id
     * @param cause   退款原因
     * @return 处理结果
     */
    public String askRefund(String orderId, String cause);

    /**
     * 用户确认收货
     *
     * @param orderId 订单id
     * @return 确认后的订单
     */
    public Order finish(String orderId);

    /**
     * 商户修改订单
     *
     * @param src 要修改的订单内容
     * @return 修改结果
     */
    public Order update(Order src);

    /**
     * 拒绝退款原因
     *
     * @param orderId 处理订单id
     * @param cause   拒绝原因
     * @return 处理结果
     */
    public Order refuse(String orderId, String cause);

    /**
     * 订单完成文件上传
     *
     * @param orderId 订单id
     * @param files   压缩文件
     * @return 完成后的订单
     */
    public Order result(String orderId, MultipartFile[] files);

    /**
     * 商户按照状态分页查询
     * @param storeId 店铺id
     * @param status 状态
     * @param page 当前页
     * @param size 分页大小
     * @return 查询结果
     */
    public List<OrderVo> sotreFindByStatus(Integer storeId, String status, Integer page, Integer size);
}

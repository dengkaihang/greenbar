package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Employee;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IEmployeeService {

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 验证用户是否具有相关权限
     *
     * @param account   帐号
     * @param authority 要验证使用的权限（权限 已经实现定义）
     * @return 是否具有此项权限
     */
    public boolean confirmation(String account, String authority);

    /**
     * 公司员工登录
     *
     * @param account  员工id
     * @param password 密码
     * @return 登录结果
     */
    Employee login(String account, String password);

    /**
     * 注册公司员工
     *
     * @param employee 员工
     */
    Employee register(Employee employee);
}

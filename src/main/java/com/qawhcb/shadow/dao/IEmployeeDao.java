package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Coupon;
import com.qawhcb.shadow.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface IEmployeeDao extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    /**
     * 按指定id逻辑删除数据
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update Employee as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);


    /**
     * 根据帐号和密码登录
     * @param account 用户帐号
     * @param password 用户密码
     * @return 登录结果
     */
    @Query(value = "select e from Employee as e where e.account = ?1 and e.password = ?2 and e.ifDel = 'false'")
    Employee findByAccountAndPass(String account, String password);

    /**
     * 根据帐号查找员工
     * @param account 帐号
     * @return 员工信息
     */
    @Query(value = "select e from Employee as e where e.account = ?1 and e.ifDel = 'false'")
    Employee findByAccount(String account);
}

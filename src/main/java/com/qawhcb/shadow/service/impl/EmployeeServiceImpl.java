package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IEmployeeDao;
import com.qawhcb.shadow.dao.IRoleDao;
import com.qawhcb.shadow.entity.Employee;
import com.qawhcb.shadow.entity.Role;
import com.qawhcb.shadow.service.IEmployeeService;
import com.qawhcb.shadow.service.IRoleService;
import com.qawhcb.shadow.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iEmployeeDao.delete(id);
    }

    @Override
    public boolean confirmation(String account, String authority) {

        // 查询该帐号所持有的角色
        Employee employee = iEmployeeDao.findByAccount(account);

        // 从角色表中查询此账户拥有的权限
        Role role = iRoleDao.findOne(employee.getRoleId());

        if (role != null) {
            String jurisdiction = role.getJurisdiction();

            // 分割权限遍历，查找当前操作权限是否合法

            if (jurisdiction == null || "".equals(jurisdiction)) {
                return false;
            }

            List<String> jurisdictions = Arrays.asList(jurisdiction.split(","));

            // 是否包含请求权限
            return jurisdictions.contains(authority);

        }

        return false;
    }

    @Override
    public Employee login(String account, String password) {

        Employee byAccountAndPass = iEmployeeDao.findByAccountAndPass(account, password);

        if (byAccountAndPass != null){
            byAccountAndPass.setToken(MD5Util.createId());

            Employee save = iEmployeeDao.save(byAccountAndPass);

            save.setPassword(null);
            save.setIfDel(null);

            return save;
        }

        return null;
    }

    @Override
    public Employee register(Employee employee) {

        // 注册时，保证帐号唯一
        Employee byAccount = iEmployeeDao.findByAccount(employee.getAccount());

        if (byAccount != null){
            return null;
        }

        return iEmployeeDao.save(employee);
    }

    @Autowired
    private IEmployeeDao iEmployeeDao;

    @Autowired
    private IRoleDao iRoleDao;
}

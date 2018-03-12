package com.qawhcb.shadow.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Taoism <br/>
 * Created on 2018/3/12 <br/>
 * jpa 保存对象复制实体非空属性
 */
public class UpdateUtils {

    /**
     * 将空值的属性从目标实体类中复制到源实体类中
     * @param src    : 要将属性中的空值覆盖的对象(源实体类)
     * @param target :从数据库根据id查询出来的目标对象
     */
    public static void copyNonNullProperties(Object src, Object target) {

        // 复制源对象值到目标对象，忽略指定属性
        BeanUtils.copyProperties(src, target, getNullProperties(src));
    }

    /**
     * 将为空的properties给找出来,然后返回出来
     * @param src 要查找非空属性的实体
     * @return 非空属性
     */
    private static String[] getNullProperties(Object src) {

        // 获取指定对象的属性描述器
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        Set<String> emptyName = new HashSet<>();

        // 遍历所有属性
        for (PropertyDescriptor p : pds) {
            Object srcValue = srcBean.getPropertyValue(p.getName());

            // 将属性为null的遍历出来
            if (srcValue == null) {
                emptyName.add(p.getName());
            }
        }
        String[] result = new String[emptyName.size()];
        return emptyName.toArray(result);
    }

}

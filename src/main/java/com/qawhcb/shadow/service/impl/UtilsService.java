package com.qawhcb.shadow.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.dao.IStoreDao;
import com.qawhcb.shadow.dao.IUserDao;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.utils.LoggerUtil;
import com.qawhcb.shadow.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * token验证。
 * Created by kane on 18-3-6
 */
@Service
public class UtilsService {

    @Autowired
    private IUserDao iUserDao;

    @Autowired
    private IStoreDao iStoreDao;

    /**
     * 验证用户token
     * @param token 待验证的token
     * @param userId　待验证的用户ｉｄ
     * @return 验证结果
     */
    public boolean userTokenVerify(String token, Integer userId){
        if(StringUtils.isNull(token)){
            return false;
        }

        User user = iUserDao.findOne(userId);

        if(user == null){
            return false;
        }else {
            return token.equalsIgnoreCase(user.getToken());
        }
    }

    /**
     * 验证店铺token
     * @param storeId
     * @param token
     * @return
     */
    public boolean storeTokenVerify(Integer storeId, String token){
        if(StringUtils.isNull(token)){
            return false;
        }

        Store store = iStoreDao.findOne(storeId);

        if(store == null){
            return false;
        }else{
            return token.equalsIgnoreCase(store.getToken());
        }
    }

    /**
     * 用户验证token，并返回map信息
     * @param userId
     * @param token
     * @return
     */
    public String userVerifyAndReturn(Integer userId, String token){
        try {
            Map<String, Object> map = new HashMap<>();
            if (!this.userTokenVerify(token, userId)){
                map.put("msg", "token error");
                map.put("code", -33);

                return JSONArray.toJSONString(map);
            }else{
                return null;
            }
        } catch (Exception e) {
            LoggerUtil.getLogger().error("" + e.getMessage());
            return  null;
        }
    }

    /**
     * 店铺验证token，并返回map信息
     * @param token
     * @param storeId
     * @return
     */
    public String storeVerifyAndReturn(String token, Integer storeId){
        try {
            Map<String, Object> map = new HashMap<>();
            if (!this.storeTokenVerify(storeId, token)){
                map.put("msg", "token error");
                map.put("code", -33);

                return JSONArray.toJSONString(map);
            }else{
                return null;
            }
        } catch (Exception e) {
            LoggerUtil.getLogger().error("" + e.getMessage());
            return  null;
        }
    }
}

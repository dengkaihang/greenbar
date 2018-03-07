package com.qawhcb.shadow.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.dao.IUserDao;
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
}

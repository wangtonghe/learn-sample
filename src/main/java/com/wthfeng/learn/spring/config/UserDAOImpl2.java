package com.wthfeng.learn.spring.config;

import com.wthfeng.learn.spring.autowire.UserDAO;
import com.wthfeng.learn.spring.entity.UserInfo;

/**
 * @author wangtonghe
 * @since 2019/4/3 16:29
 */
public class UserDAOImpl2 implements UserDAO {

    @Override
    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser("xxx");
        userInfo.setPassword("vvv");
        return userInfo;
    }
}

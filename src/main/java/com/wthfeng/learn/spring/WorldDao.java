package com.wthfeng.learn.spring;

import com.wthfeng.learn.spring.entity.UserInfo;

/**
 * @author wangtonghe
 * @since 2019/4/3 14:14
 */
public class WorldDao {

    public UserInfo getUser2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser("fff");
        return userInfo;
    }

    public void init() {
        System.out.println("执行初始化方法");
    }

    public void destroy() {
        System.out.println("执行销毁方法");
    }
}

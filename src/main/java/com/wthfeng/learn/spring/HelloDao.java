package com.wthfeng.learn.spring;

import com.wthfeng.learn.spring.entity.UserInfo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author wangtonghe
 * @since 2019/4/3 11:36
 */
public class HelloDao {

    public UserInfo getUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser("ddd");
        userInfo.setPassword("ddddss");
        return userInfo;
    }

    @PostConstruct
    public void init() {
        System.out.println("init dao hello");

    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("destroy dao ");
    }
}

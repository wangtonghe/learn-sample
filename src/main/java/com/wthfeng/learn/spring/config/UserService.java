package com.wthfeng.learn.spring.config;

import com.wthfeng.learn.spring.autowire.UserDAO;
import com.wthfeng.learn.spring.entity.UserInfo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author wangtonghe
 * @since 2019/4/3 16:05
 */
public class UserService {

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private UserDAO userDAO;


    public UserInfo getUser() {
        return userDAO.getUserInfo();
    }


    @PostConstruct
    public void init2() {
        System.out.println("user service init...xxxxxxxxxxxxx");
    }


    @PreDestroy
    public void destroy2() {
        System.out.println("user service destroy over xxxxxxx");
    }
}

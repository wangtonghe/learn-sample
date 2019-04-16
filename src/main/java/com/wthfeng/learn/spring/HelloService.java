package com.wthfeng.learn.spring;

import com.wthfeng.learn.spring.entity.UserInfo;

/**
 * @author wangtonghe
 * @since 2019/4/3 11:35
 */
public class HelloService {


    public HelloService(HelloDao helloDao, WorldDao worldDao) {
        this.helloDao = helloDao;
        this.worldDao = worldDao;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private HelloDao helloDao;

    private WorldDao worldDao;


    public UserInfo getUser() {
        UserInfo userInfo = helloDao.getUser();
        userInfo.setUser(message);
        return userInfo;
    }


    public void init() {
        System.out.println("hello service init...");
    }

    public void destroy() {

        System.out.println("hello service destroy ...");

    }
}

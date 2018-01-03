package com.wthfeng.learn.guice;

/**
 * @author wangtonghe
 * @date 2017/10/20 13:07
 */
public class UserServiceImpl implements UserService {
    @Override
    public void login(String user, String password) {
        System.out.println(UserServiceImpl.class.getSimpleName()+"login");

    }
}

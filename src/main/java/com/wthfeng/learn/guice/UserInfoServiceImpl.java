package com.wthfeng.learn.guice;

/**
 * @author wangtonghe
 * @date 2017/10/20 13:08
 */
public class UserInfoServiceImpl implements UserService {
    @Override
    public void login(String user, String password) {
        System.out.println( UserInfoServiceImpl.class.getSimpleName()+" login");
    }
}

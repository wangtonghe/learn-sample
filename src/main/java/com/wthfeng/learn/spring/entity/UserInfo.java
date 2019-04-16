package com.wthfeng.learn.spring.entity;

import java.util.StringJoiner;

/**
 * @author wangtonghe
 * @since 2019/4/3 11:36
 */
public class UserInfo {

    private String user;

    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserInfo.class.getSimpleName() + "[", "]")
                .add("user='" + user + "'")
                .add("password='" + password + "'")
                .toString();
    }
}

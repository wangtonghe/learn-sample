package com.wthfeng.learn.reflect;

/**
 * @author wangtonghe
 * @date 2017/10/25 19:31
 */
public class User {
    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void plan(){
        System.out.println("hello world");
    }
}

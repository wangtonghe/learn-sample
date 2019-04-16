package com.wthfeng.learn.reflect;

/**
 * @author wangtonghe
 * @since 2019/3/17 16:36
 */
public class TestReflect {

    private String message = "test user";

    public String getMessage() {
        return message;
    }


    private void privateMethod() {
        System.out.println(message);
    }

}

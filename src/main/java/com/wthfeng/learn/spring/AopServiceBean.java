package com.wthfeng.learn.spring;

/**
 * @author wangtonghe
 * @since 2019/4/3 18:21
 */
public class AopServiceBean {


    public void before() {
        System.out.println("执行方法 say hello  start");
    }

    public void after() {
        System.out.println("执行方法 after");
    }

    public void around() {
        System.out.println("环切。。。。");
    }

    public void throwEx() {
        System.out.println("throw ex");
    }
}

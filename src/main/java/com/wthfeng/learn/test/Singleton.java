package com.wthfeng.learn.test;

/**
 * @author wangtonghe
 * @since 2019/3/28 17:56
 */
public enum Singleton {

    INSTANCE;

    public void init() {
        System.out.println("init method");
    }


}

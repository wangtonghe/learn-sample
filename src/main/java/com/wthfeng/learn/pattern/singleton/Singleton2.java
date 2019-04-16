package com.wthfeng.learn.pattern.singleton;

/**
 * 饿汉模式
 *
 * @author wangtonghe
 * @since 2018/9/18 10:40
 */
public class Singleton2 {

    private static Singleton2 singleton2 = new Singleton2();

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        return singleton2;
    }
}


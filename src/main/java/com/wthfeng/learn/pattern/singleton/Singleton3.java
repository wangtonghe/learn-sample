package com.wthfeng.learn.pattern.singleton;

/**
 * 静态内部类方式
 */
public class Singleton3 {
    private Singleton3() {
        if (null != InnerSingleton.singleton3) {
            throw new RuntimeException();
        }
    }

    public static Singleton3 getInstance() {
        return InnerSingleton.singleton3;
    }

    private static class InnerSingleton {
        private static final Singleton3 singleton3 = new Singleton3();

    }
}

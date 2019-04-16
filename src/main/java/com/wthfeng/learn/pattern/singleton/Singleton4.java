package com.wthfeng.learn.pattern.singleton;

/**
 * @author wangtonghe
 * @since 2019/3/5 20:52
 */
public class Singleton4 {

    private Singleton4 instance;

    private Singleton4() {

    }

    public Singleton4 getInstance() {

        return Inner.INSTANCE.getInstance();


    }

    private enum Inner {

        INSTANCE;

        private Singleton4 instance;

        Inner() {
            instance = new Singleton4();
        }

        private Singleton4 getInstance() {
            return instance;
        }


    }
}

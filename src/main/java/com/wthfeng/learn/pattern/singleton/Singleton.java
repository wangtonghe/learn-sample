package com.wthfeng.learn.pattern.singleton;

import java.io.Serializable;

/**
 * 双重检锁模式
 *
 * @author wangtonghe
 * @since 2018/9/18 09:57
 */
public class Singleton implements Serializable {

    /**
     * 实例变量一定要有volatile修饰
     */
    private volatile static Singleton singleton;

    private Singleton() {
        if (null != singleton) {
            throw new RuntimeException();
        }
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public void dispaly() {
        System.out.println("hello,this is singleton.");
    }

    private Object readResolve() {
        return singleton;
    }


}

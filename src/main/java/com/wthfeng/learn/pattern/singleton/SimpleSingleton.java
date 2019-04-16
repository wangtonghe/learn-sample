package com.wthfeng.learn.pattern.singleton;

/**
 * 饿汉模式变种
 * 都是在类被加载时创建的对象，由jvm保证其线程安全
 * 缺点：如果这个类被多次加载的话也会造成多次实例化，即该方法类不能被加载多次
 */
public class SimpleSingleton {

    private static SimpleSingleton simpleSingleton;

    private SimpleSingleton() {
    }

    static {
        simpleSingleton = new SimpleSingleton();
    }

    public SimpleSingleton getInstance() {
        return simpleSingleton;
    }

}

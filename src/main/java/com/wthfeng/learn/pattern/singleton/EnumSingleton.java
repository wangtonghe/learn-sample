package com.wthfeng.learn.pattern.singleton;

/**
 * 枚举模式
 *
 * @author wangtonghe
 * @since 2018/9/18 10:11
 */
public class EnumSingleton {

    private EnumSingleton() {
    }

    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getEnumSingleton();
    }

    private enum Singleton {

        INSTANCE;

        private EnumSingleton enumSingleton;

        Singleton() {
            enumSingleton = new EnumSingleton();
        }

        private EnumSingleton getEnumSingleton() {
            return enumSingleton;
        }


    }
}

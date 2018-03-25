package com.wthfeng.learn.reflect;

/**
 * @author wangtonghe
 * @date 2017/10/22 20:15
 */
public class TestInitClass {
    public static void main(String[] args) {
        //只触发了父类的初始化，而不会触发子类初始化
        //因为这个字段是父类的
        System.out.println(Common.value);
    }

}

class Father {
    static {
        System.out.println("father init");

    }
}

class Common extends Father {
    static String value = "wth2";
    static {
        System.out.println("common init");
    }

}

package com.wthfeng.learn.lang;

import org.junit.Test;

/**
 * @author wangtonghe
 * @date 2017/10/23 18:51
 */
public class InitTest {
    static {
        a=3;  //出现在定义之前，只能赋值不能访问
//        System.out.println(a);  //非法向前引用
    }
    static int a = 1;

    public static void main(String[] args) {
        System.out.println(InitTest.a);  //1,
    }

}

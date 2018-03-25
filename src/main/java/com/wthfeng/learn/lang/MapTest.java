package com.wthfeng.learn.lang;

/**
 * @author wangtonghe
 * @date 2018/3/9 21:01
 */
public class MapTest {

    public static void main(String[] args) {
        String hello = "hello";
        int code = hello.hashCode();
        System.out.println(code);
        int hash = code^code>>>16;
        System.out.println(hash);
        System.out.println(1024>>>3);
    }
}

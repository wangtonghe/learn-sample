package com.wthfeng.learn.lang;


import org.junit.Test;

/**
 * @author wangtonghe
 * @date 2017/12/23 22:49
 */
public class StringTest {


    @Test
    public void test1() {
        String str = "";
        int index = str.indexOf(" ");
        String first = "";
        String second = "";
        if (index > -1) {
            first = str.substring(0, index);
            second = str.substring(index + 1);
        } else {
            first = str;
        }
        System.out.println(first);
        System.out.println(second);
        String[] arr = new String[1];
        arr[0] = second;
        test2("wthfeng", arr);
    }


    public void test2(String name, String... email) {
        System.out.println(name);
        if (email.length > 0) {
            System.out.println(email[0]);
        }
    }
}

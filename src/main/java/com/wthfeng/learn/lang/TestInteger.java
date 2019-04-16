package com.wthfeng.learn.lang;

import org.junit.Test;

/**
 * @author wangtonghe
 * @since 2018/10/13 18:29
 */
public class TestInteger {

    @Test
    public void testInt() {
        Integer a = null;
        int b = 5;
        int c = a + b;
        System.out.println(c);

    }

    @Test
    public void IntCache() {

        Integer a = 127;
        Integer b = 127;
        System.out.println(a==b);

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);

        Integer e = 1000;
        Integer f = 1000;
        System.out.println(e == f);

        Integer g = 1001;
        Integer h = 1001;
        System.out.println(g == h);

        Integer i = 20000;
        Integer j = 20000;
        System.out.println(i == j);


    }

    @Test
    public void testMoveBit() {
        int count = Integer.SIZE - 3;
        int a = 0 << count;
        int b = -1 << count;
        int c = 1 << count;
        int d = 2 << count;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
        System.out.println(Integer.toBinaryString(c));
        System.out.println(Integer.toBinaryString(d));

    }
}

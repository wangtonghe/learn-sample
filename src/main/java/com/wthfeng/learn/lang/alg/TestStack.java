package com.wthfeng.learn.lang.alg;

import org.junit.Test;

/**
 * @author wangtonghe
 * @since 2018/9/23 20:35
 */
public class TestStack {

    @Test
    public void testStack() {
        MyStack stack = new MyStack(10);
        stack.push(23);
        stack.push(12);
        stack.push(90);
        stack.push(9);
        stack.push(13);
        System.out.println("e=" + stack.pop());
        System.out.println("min=" + stack.min());
        System.out.println("e=" + stack.pop());
        System.out.println("min=" + stack.min());
        System.out.println("e=" + stack.pop());
        System.out.println("min=" + stack.min());
        System.out.println("e=" + stack.pop());
        System.out.println("min=" + stack.min());
        System.out.println("e=" + stack.pop());
        System.out.println("min=" + stack.min());

    }
}

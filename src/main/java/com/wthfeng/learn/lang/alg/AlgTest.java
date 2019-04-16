package com.wthfeng.learn.lang.alg;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Stack;

/**
 * @author wangtonghe
 * @since 2018/9/22 18:12
 */
public class AlgTest {

    @Test
    public void testFibon() {
        int n = 50;
        System.out.println(fib(n));

    }


    public int fibona(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibona(n - 1) + fibona(n - 2);
    }

    public long fib(long n) {

        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long fn1 = 0;
        long fn2 = 1;
        long fn = 0;
        for (int i = 2; i < n; i++) {
            fn = fn1 + fn2;
            fn1 = fn2;
            fn2 = fn;
        }
        return fn;
    }

    public long factorial(long n) {
        if (n <= 0) {
            throw new RuntimeException("输入非法");
        } else if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public long facNotR(long n) {

        if (n <= 0) {
            throw new RuntimeException("输入非法");
        } else if (n == 1) {
            return 1;
        }
        long fn1 = 1;
        long fn = 1;
        for (int i = 1; i <= n; i++) {
            fn = i * fn1;
            fn1 = fn;
        }
        return fn;
    }


    @Test
    public void testFib() {
        long start = System.currentTimeMillis();
        fibona(37);
        System.out.println(System.currentTimeMillis() - start);
        long start1 = System.currentTimeMillis();
        facNotR(37);
        System.out.println(System.currentTimeMillis() - start1);
    }


    @Test
    public void testFac() {

        int n = 100;
        BigInteger result = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }
        System.out.println(result);

    }


    @Test
    public void testMatch() {

        String testStr = "[](]{}";
        Stack<Character> stack = new Stack<>();
        boolean result = true;

        for (int i = 0; i < testStr.length(); i++) {
            char c = testStr.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                char pre = stack.pop();
                if (c == ')' && pre != '(') {
                    result = false;
                    break;
                } else if (c == ']' && pre != '[') {
                    result = false;
                    break;

                } else if (c == '}' && pre != '{') {
                    result = false;
                    break;

                }

            }
        }
        result = result && stack.size() == 0;

        System.out.println(result);

    }


}

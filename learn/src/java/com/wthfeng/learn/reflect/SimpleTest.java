package com.wthfeng.learn.reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wangtonghe
 * @date 2017/10/12 12:45
 */
public class SimpleTest {


    @Test
    public void test() {
        Class<Apple> appleClass = Apple.class;
        String clzName = appleClass.getName();
//        Apple apple = new Apple();
        System.out.println(clzName);
        try {
            Field field = appleClass.getDeclaredField("name");

            Method m_hello = appleClass.getMethod("hello");
            m_hello.invoke(appleClass.getDeclaredConstructor().newInstance());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

class Apple {
    private String name;

    private int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void hello() {
        System.out.println("hello apple");
    }
}

package com.wthfeng.learn.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangtonghe
 * @date 2017/10/25 19:23
 */
public class ClassTest {


    @Test
    public void test2() {

        Class<TestReflect> clazz = TestReflect.class;
        try {
            Method method = clazz.getDeclaredMethod("privateMethod");
            method.setAccessible(true);

            method.invoke(clazz.newInstance());


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {

        try {
            Class<TestReflect> clazz = TestReflect.class;
            Field field = clazz.getDeclaredField("message");
            field.setAccessible(true);
            TestReflect reflect = clazz.getDeclaredConstructor().newInstance();


            field.set(reflect, "123");
            System.out.println(reflect.getMessage());
            System.out.println(field.get(reflect));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test() throws Exception {

        Class<User> userClass = User.class;
        System.out.println(userClass.toGenericString());
        Constructor<User> userConstructor = userClass.getConstructor(null);
        Object obj = userConstructor.newInstance();
        Method method = userClass.getMethod("plan");
        method.invoke(obj);


    }

    @Test
    public void testReflect() {


        String cName = "com.wthfeng.learn.reflect.User";
        try {
            Class<?> clazz = Class.forName(cName);
            Class<?> clazz2 = Class.forName(cName);
            System.out.println(clazz == clazz2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReflect2() {


        String clazzName = "com.wthfeng.learn.reflect.User";
        try {

            Class<?> clazz = Class.forName(clazzName);
            User user = (User) clazz.newInstance();
            user.plan();
            Field[] fields = clazz.getFields();

            Method[] methods = clazz.getMethods();

            for (Method method : methods) {

                System.out.println("----");
                System.out.println(method.getName());
                System.out.println("----");

            }

            Method[] methods1 = clazz.getDeclaredMethods();
            for (Method method : methods1) {
                System.out.println("----**********");
                System.out.println(method.getName());
                System.out.println("----");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}



package com.wthfeng.learn.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author wangtonghe
 * @date 2017/10/25 19:23
 */
public class ClassTest {

    @Test
    public void test() throws Exception {

        Class<User> userClass = User.class;
        System.out.println(userClass.toGenericString());
        Constructor<User> userConstructor = userClass.getConstructor(null);
        Object obj = userConstructor.newInstance();
        Method method = userClass.getMethod("plan");
        method.invoke(obj);


    }
}



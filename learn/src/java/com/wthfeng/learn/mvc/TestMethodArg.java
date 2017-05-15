package com.wthfeng.learn.mvc;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @author wangtonghe
 * @date 2017/5/14 17:33
 */
public class TestMethodArg {

    public void  method1( String name,String email){
        System.out.println(name+":"+email);

    }

    @Test
    public void test(){
        Class<TestMethodArg> clazz = TestMethodArg.class;
        try {
            Method method = clazz.getMethod("method1", String.class, String.class);
            Parameter[] parameters = method.getParameters();
            Arrays.stream(parameters).forEach(p->{
                System.out.println(p.getName()+" : "+p.getType());
                System.out.println(p.getAnnotatedType());
            });
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

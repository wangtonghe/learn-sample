package com.wthfeng.learn.reflect.aop.cglib;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wangtonghe
 * @since 2019/4/6 20:11
 */
public class MyIntercept implements MethodInterceptor {


    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("前置拦截");
        Object result = methodProxy.invokeSuper(obj, objects);
        System.out.println("后置拦截");
        return result;
    }
}

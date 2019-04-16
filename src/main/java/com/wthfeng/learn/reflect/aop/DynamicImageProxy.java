package com.wthfeng.learn.reflect.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangtonghe
 * @since 2019/4/6 18:03
 */
public class DynamicImageProxy implements InvocationHandler {

    private Object obj;

    public DynamicImageProxy(Object obj) {
        this.obj = obj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置工作");
        Object result = method.invoke(obj, args);
        System.out.println("后置工作");
        return result;
    }
}

package com.wthfeng.learn.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangtonghe
 * @date 2017/5/21 13:25
 */
public class TestProxy {

    @Test
    public void testStatic(){
        ImageHandler handler = new ImageHandlerImpl();
        ImageHandlerProxy proxy = new ImageHandlerProxy(handler);

        proxy.loadImage();

    }

    @Test
    public void testDynamic(){

        //真实对象
        ImageHandler realObject = new ImageHandlerImpl();

        //代理对象的处理器
        InvocationHandler  handler = new MyDynamicProxy(realObject);

        //生成代理对象
        ImageHandler imageProxy = (ImageHandler) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                handler.getClass().getInterfaces(),handler);

        imageProxy.loadImage();

    }


}



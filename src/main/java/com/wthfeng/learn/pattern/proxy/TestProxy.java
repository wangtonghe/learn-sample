package com.wthfeng.learn.pattern.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author wangtonghe
 * @date 2017/5/21 13:25
 */
public class TestProxy {

    @Test
    public void testStatic() {
        ImageHandler handler = new ImageHandlerImpl();
        ImageHandlerProxy proxy = new ImageHandlerProxy(handler);

        proxy.loadImage();

    }

    @Test
    public void testDynamic() {

        //真实对象
        ImageHandler realObject = new ImageHandlerImpl();

        //代理对象的处理器
        InvocationHandler handler = new MyDynamicProxy(realObject);

        //生成代理对象
        ImageHandler imageProxy = (ImageHandler) Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                new Class[]{ImageHandler.class}, handler);

        imageProxy.loadImage();

    }

    @Test
    public void testInterface() {
        //真实对象
        ImageHandler realObject = new ImageHandlerImpl();

        //代理对象的处理器
        InvocationHandler handler = new MyDynamicProxy(realObject);

        Arrays.stream(ImageHandler.class.getInterfaces()).forEach(System.out::println);

        Arrays.stream(ImageHandlerImpl.class.getInterfaces()).forEach(System.out::println);

        Arrays.stream(handler.getClass().getInterfaces()).forEach(System.out::println);


    }

    @Test
    public void testDynamic2() {

        ImageHandler imageHandler = new ImageHandlerImpl();

        ImageHandler imageProxy = (ImageHandler) Proxy.newProxyInstance(imageHandler.getClass().getClassLoader(),
                new Class[]{ImageHandler.class}, (proxy, method, args) -> {

                    System.out.println("开始执行");

                    return method.invoke(imageHandler, args);


                });

        imageProxy.loadImage();


    }


}



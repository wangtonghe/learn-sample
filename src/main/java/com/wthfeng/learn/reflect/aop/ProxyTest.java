package com.wthfeng.learn.reflect.aop;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author wangtonghe
 * @since 2019/4/6 18:06
 */
public class ProxyTest {

    @Test
    public void testDynamicProxy() {

        ImageHandler imageHandler = new ImageHandlerImpl();

        ImageHandler imageProxy = (ImageHandler) Proxy.newProxyInstance(imageHandler.getClass().getClassLoader(), new Class[]{ImageHandler.class},
                new DynamicImageProxy(imageHandler));

        imageProxy.loadImage();


    }
}

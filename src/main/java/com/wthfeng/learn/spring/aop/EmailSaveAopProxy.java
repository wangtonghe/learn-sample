package com.wthfeng.learn.spring.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangtonghe
 * @since 2019/4/6 18:16
 */
public class EmailSaveAopProxy implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        IEmailService result = null;
        if (bean instanceof EmailService) {
            result = (IEmailService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), EmailService.class.getInterfaces(),
                    new EmailServiceProxy(new EmailService()));

        }
        return result;
    }
}

class EmailServiceProxy implements InvocationHandler {

    private Object obj;

    public EmailServiceProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String className = "com.wthfeng.learn.spring.aop.AspectModel";
        Class<?> aopClazz = Class.forName(className);
        Object aopObj = aopClazz.newInstance();
        Method beforeM = aopClazz.getMethod("before");
        Method afterM = aopClazz.getMethod("after");
        beforeM.invoke(aopObj);

        Object result = method.invoke(obj, args);
        afterM.invoke(aopObj);

        return result;
    }
}

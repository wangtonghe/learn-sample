package com.wthfeng.learn.reflect;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangtonghe
 * @date 2017/10/12 15:37
 */
public class DynamicProxySubject implements InvocationHandler {

    private Object subject;

    public DynamicProxySubject(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始。。。");
        Object obj = method.invoke(subject, args);
        System.out.println("结束。。。");
        return obj;

    }

    public static void main(String[] args) {

        //真实对象
        Subject subject = new SubjectImpl();

        //代理对象的处理器
        InvocationHandler handler = new DynamicProxySubject(subject);

        Subject proxy = (Subject) Proxy.newProxyInstance(
                Subject.class.getClassLoader(), new Class[] {Subject.class},
               handler );

        proxy.load();
    }
}

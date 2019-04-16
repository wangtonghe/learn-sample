package com.wthfeng.learn.reflect.aop.cglib;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author wangtonghe
 * @since 2019/4/6 20:16
 */
public class CglibTest {


    @Test
    public void testSimple() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Subject.class);
        enhancer.setCallback(new MyIntercept());
        Subject subject = (Subject) enhancer.create();
        subject.doSubject();
    }
}

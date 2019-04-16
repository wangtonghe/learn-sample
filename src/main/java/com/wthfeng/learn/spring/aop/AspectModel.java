package com.wthfeng.learn.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wangtonghe
 * @since 2019/4/3 19:28
 */
@Aspect
@Component
public class AspectModel {

    @Pointcut(value = "execution(* com.wthfeng.learn.spring.aop.*Service.save(..))")
    public void selectSave() {

    }

    @Before(value = "selectSave()")
    public void before() {

        System.out.println("before method...");

    }

    @After(value = "selectSave()")
    public void after() {
        System.out.println("after method...");

    }

}

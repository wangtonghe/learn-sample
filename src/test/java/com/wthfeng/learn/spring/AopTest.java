package com.wthfeng.learn.spring;

import com.wthfeng.learn.spring.aop.Email2Service;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangtonghe
 * @since 2019/4/6 19:08
 */
public class AopTest {

    private ClassPathXmlApplicationContext context;


    @Test
    public void testAop() {

        context = new ClassPathXmlApplicationContext("spring/aop.xml");

        Email2Service email2Service = (Email2Service) context.getBean("emailService2");
        email2Service.save();


    }
}

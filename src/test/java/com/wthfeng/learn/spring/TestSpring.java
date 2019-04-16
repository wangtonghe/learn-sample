package com.wthfeng.learn.spring;

import com.wthfeng.learn.spring.aop.IEmailService;
import com.wthfeng.learn.spring.autowire.UserService;
import com.wthfeng.learn.spring.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author wangtonghe
 * @since 2019/4/3 11:48
 */
public class TestSpring {


    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {

//        context.start();

    }

    @Test
    public void testContext() {
        context = new ClassPathXmlApplicationContext("classpath:spring/beans.xml");

//        HelloService helloService = (HelloService) context.getBean("helloService");
//
//        HelloService helloService2 = (HelloService) context.getBean("helloService");
//
//        System.out.println(helloService == helloService2);
//
//        MessageBean messageBean = (MessageBean) context.getBean("messageBean");
//        System.out.println(messageBean.getMsg1());
//        System.out.println(messageBean.getMsg3());

//        CollectionBean collectionBean = (CollectionBean) context.getBean("collectionBean");
//        collectionBean.getList().forEach(System.out::print);
//        collectionBean.getMap().forEach((k, v) -> {
//            System.out.println("k=" + k + ":v=" + v);
//        });

        UserService userService = (UserService) context.getBean("userService");
        context.start();
        System.out.println(userService.getUser());


        context.destroy();

    }

    @Test
    public void testBeanFactory() {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("spring/beans.xml"));

        HelloService helloService = (HelloService) xmlBeanFactory.getBean("helloService");
        UserInfo userInfo = helloService.getUser();
        System.out.println(userInfo);

    }

    @Test
    public void testAop() {

        context = new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
        IEmailService emailService = (IEmailService) context.getBean("emailService");
        emailService.save();

    }

    @Test
    public void testPost() {
        context = new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
        WorldDao worldDao = (WorldDao) context.getBean("worldDao");
        System.out.println(worldDao.getUser2());
        context.close();

    }


}

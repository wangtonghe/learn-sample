package com.wthfeng.learn.spring.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangtonghe
 * @since 2019/4/3 17:26
 */
public class SpringApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        System.out.println(userService.getUser());

    }
}

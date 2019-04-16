package com.wthfeng.learn.spring.aop;

/**
 * @author wangtonghe
 * @since 2019/4/3 18:41
 */
public class EmailService implements IEmailService {

    public void printLog() {
        System.out.println("email log");
    }

    @Override
    public void save() {
        System.out.println("email2");
    }
}

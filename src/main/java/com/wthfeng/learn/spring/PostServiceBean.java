package com.wthfeng.learn.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * bean后置处理器
 * 实现了BeanPostProcessor接口的类，会调用每一个bean,并对bean进行初始化前和初始化后的工作
 *
 * @author wangtonghe
 * @since 2019/4/3 13:55
 */
public class PostServiceBean implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        // 做bean做一些修改
        return bean;
    }
}

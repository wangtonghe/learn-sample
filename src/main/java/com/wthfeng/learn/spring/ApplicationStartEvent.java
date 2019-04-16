package com.wthfeng.learn.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @author wangtonghe
 * @since 2019/4/3 17:58
 */
public class ApplicationStartEvent implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {

        System.out.println("spring上下文启动。。。");

    }
}

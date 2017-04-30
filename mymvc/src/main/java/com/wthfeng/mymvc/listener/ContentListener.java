package com.wthfeng.mymvc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author wangtonghe
 * @date 2017/3/8 13:18
 */
@WebListener
public class ContentListener implements ServletContextListener {

    /**
     *
     * @param sce
     */
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener 容器初始化开始");


    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("listener 容器销毁");

    }
}

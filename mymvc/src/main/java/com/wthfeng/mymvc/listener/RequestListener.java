package com.wthfeng.mymvc.listener;


import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @author wangtonghe
 * @date 2017/3/8 14:57
 */
@WebListener
public class RequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("listener request销毁");

    }

    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("listener request初始化:" );

    }
}

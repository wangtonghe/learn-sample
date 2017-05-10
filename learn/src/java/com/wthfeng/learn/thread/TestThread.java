package com.wthfeng.learn.thread;

import org.junit.Test;

/**
 * @author wangtonghe
 * @date 2017/5/5 12:47
 */
public class TestThread {
    private final NotifyObject notifyObject = new NotifyObject();

    public void doWait() {
        synchronized (notifyObject) {
            try {
                notifyObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void doNotify() {
        synchronized (notifyObject) {
            notifyObject.notify();
        }
    }


}


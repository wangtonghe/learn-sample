package com.wthfeng.learn.lang.thread;

/**
 * @author wangtonghe
 * @since 2018/10/18 16:37
 */
public class TestThread {


    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("线程运行中");
        }).start();
    }

}

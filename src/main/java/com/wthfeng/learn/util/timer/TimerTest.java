package com.wthfeng.learn.util.timer;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author wangtonghe
 * @date 2017/5/24 19:35
 */
public class TimerTest {

    @Test
    public void test(){
        Timer timer = new Timer();
        timer.schedule(new MyTimer(),1000);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束任务");



    }

    public class MyTimer extends TimerTask{

        @Override
        public void run() {
            System.out.println("定时任务开始");

        }
    }
}

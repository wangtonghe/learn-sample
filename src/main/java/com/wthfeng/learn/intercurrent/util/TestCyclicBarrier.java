package com.wthfeng.learn.intercurrent.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wangtonghe
 * @since 2019/3/8 15:48
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {
        int n = 5;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(n, () -> {
            System.out.println("所有准备工作就绪，开始！");

        });

        for (int i = 0; i < n; i++) {
            Thread t = new Thread(new RunTask(cyclicBarrier, i));
            t.start();
        }

    }

    public static class RunTask implements Runnable {

        private int i;
        private CyclicBarrier cb;

        RunTask(CyclicBarrier cb, int i) {
            this.i = i;
            this.cb = cb;
        }


        @Override
        public void run() {

            try {
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + ": 准备完成");
                cb.await();
                System.out.println(i + "已准备好！");
            } catch (InterruptedException e) {
                System.out.println("被中断");
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                System.out.println("放弃任务");
                e.printStackTrace();
            }

        }
    }

}

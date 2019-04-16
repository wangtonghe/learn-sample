package com.wthfeng.learn.intercurrent.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtonghe
 * @since 2019/3/17 19:53
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> {
        return 0;
    });

    public static void main(String[] args) {

        List<Thread> list = new ArrayList<>();
        Thread thread = null;

        for (int i = 0; i < 10; i++) {

            thread = new Thread(new Task(i));
            thread.start();
            list.add(thread);

        }


        list.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }


    static class Task implements Runnable {

        private int num;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {

            try {
                threadLocal.set(num);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get:" + threadLocal.get());

        }
    }


}

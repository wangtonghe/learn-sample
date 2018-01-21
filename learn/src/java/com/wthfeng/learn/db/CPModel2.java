package com.wthfeng.learn.db;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangtonghe
 * @date 2018/1/5 10:49
 */
public class CPModel2 {


    static private int cap = 200;
    static final AtomicInteger number = new AtomicInteger(0);


    private static MyLinkedBlockingQueue<Task> queue = new MyLinkedBlockingQueue<>(cap);

    private AtomicInteger taskNo = new AtomicInteger(0);

    static int threadNum = 20;


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            CPModel.Task task = new CPModel.Task(i);
//            queue.add(task);
            number.getAndIncrement();
        }

        for (int i = 0; i < threadNum; i++) {
            new Thread(new CPModel.Produce(), "produce-" + i).start();
            new Thread(new CPModel.Consume(), "consume-" + i).start();
        }
        Thread.sleep(60 * 1000);
//        shundown();
//        System.out.println("共生产消费任务数："+total);
    }

    //消费者线程
    static class Consume implements Runnable {

        @Override
        public void run() {
            try {
                Task task = queue.take();
                System.out.println(task.no);  //模拟消费
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Produce implements Runnable {

        @Override
        public void run() {
            Task task = new Task(1);
            try {
                queue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    static class Task {
        int no;

        Task(int no) {
            this.no = no;
        }
    }
}

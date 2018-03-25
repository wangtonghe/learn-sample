package com.wthfeng.learn.intercurrent.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 共享变量测试类
 *
 * @author wangtonghe
 * @date 2017/5/11 10:26
 */
public class TestShared {

    private   volatile    boolean flag =false;
    private CountDownLatch countDownLatch = new CountDownLatch(2);

    private   void test() {

        new Thread(() -> {
            for (int i=0;;i++) {
                if (flag) {
                    break;
                }
                System.out.println(i+"-------->"+Thread.currentThread().getName());
            }
            countDownLatch.countDown();
        },"thread-one").start();

    }
    private void test2(){

        new Thread(() -> {
            try {
                Thread.sleep(3000);
                flag=true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();

        },"thread-two").start();

    }

    public static void main(String[] args) {
        TestShared testShared = new TestShared();
        testShared.test();
        testShared.test2();
        try {
            testShared.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

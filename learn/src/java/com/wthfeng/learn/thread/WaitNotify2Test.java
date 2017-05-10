package com.wthfeng.learn.thread;

/**
 * @author wangtonghe
 * @date 2017/5/10 09:50
 */
public class WaitNotify2Test {
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

    public static void main(String[] args) {
       WaitNotify2Test waitNotify2Test = new WaitNotify2Test();

        new Thread(() -> {
            for(int i=0;i<100;i++){
                if(i==50){
                    waitNotify2Test.doWait();
                }
                System.out.println( Thread.currentThread().getName()+":"+i);
            }

        }).start();
        new Thread(()->{
            for(int i=0;i<500;i++){
                System.out.println( Thread.currentThread().getName()+":"+i);
            }
            waitNotify2Test.doNotify();


        }).start();

    }
}

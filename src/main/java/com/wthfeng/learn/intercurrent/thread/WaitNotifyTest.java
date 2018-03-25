package com.wthfeng.learn.intercurrent.thread;

/**
 * @author wangtonghe
 * @date 2017/5/10 09:27
 */
public class WaitNotifyTest {


    /**
     * wait、notify用法
     * 当执行一个对象的wait()、notify()方法时，必须持有这个对象的锁，即在同步块中调用方法
     * 在JVM实现中，当执行一个对象的wait()方法时，会首先检查它是否在同步块中，否则抛出异常
     * @param args args
     */
    public static void main(String[] args) {
        NotifyObject notifyObject = new NotifyObject();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始");
            for(int i=0;i<100;i++){
                if(i==50){
                    //需获得这个对象的锁才能执行wait()方法
                    synchronized (notifyObject){
                        try {
                            notifyObject.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println( Thread.currentThread().getName()+":"+i);
            }

        }).start();
        new Thread(()->{
            for(int i=0;i<500;i++){
                System.out.println( Thread.currentThread().getName()+":"+i);
            }
            synchronized (notifyObject){
                notifyObject.notify();
            }


        }).start();
    }
}

class NotifyObject {


}

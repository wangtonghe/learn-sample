package com.wthfeng.learn.intercurrent.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangtonghe
 * @date 2017/5/17 17:56
 */
public class CASTest {

    private AtomicInteger value = new AtomicInteger(0);

    private int unValue =0;

    public AtomicInteger getValue() {
        return value;
    }


    public int getUnValue() {
        return unValue;
    }

    private void  safeAdd(){
       while (true){
           int i = value.get();
           if(value.compareAndSet(i,i+1)){
               break;
           }
       }

    }

    private void add(){
        unValue++;
    }

    public static void main(String[] args) {
        CASTest casTest = new CASTest();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                for(int j=0;j<10000;j++) {
                    casTest.add();
                    casTest.safeAdd();
                }
                countDownLatch.countDown();

            }).start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i:"+casTest.getUnValue());
        System.out.println("i:"+casTest.getValue());
    }


}

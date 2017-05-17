package com.wthfeng.learn.thread;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author wangtonghe
 * @date 2017/5/17 00:36
 */
public class TestCaller {

    private ExecutorService executorService;

    private CompletionService<Integer> cs;

    private volatile Integer origin;

    private List<Future<Integer>> list;


    @Before
    public void before() {
        executorService = Executors.newFixedThreadPool(10);
        cs =  new ExecutorCompletionService<>(executorService);
        list = new ArrayList<>();
        origin =new Random().nextInt();



    }


    @Test
    public void test() {
        long start = System.currentTimeMillis();

        for (int i = 0; i <10 ; i++) {
            Future<Integer> result = executorService.submit(()->{
               long s0 = System.currentTimeMillis();
                Thread.sleep(5000);
                int res = new Random().nextInt(50);
                System.out.println(System.currentTimeMillis()-s0);
                return res;

            });
            list.add(result);
        }
//        executorService.shutdown();
        System.out.println("获取结果：");
        executorService.shutdown();


        list.forEach(e-> {
            try {
                e.get();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
        });
        System.out.println("共耗时："+(System.currentTimeMillis()-start)/1000+"秒");

    }

    @Test
    public void test2(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10 ; i++) {
            cs.submit(()->{
                long s1 = System.currentTimeMillis();
                Thread.sleep(5000);
                int value =  new Random().nextInt(100);
                System.out.println(System.currentTimeMillis()-s1);
                return value;
            });
        }
        for (int i = 0; i <10 ; i++) {
            try {
                cs.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        executorService.shutdown();
        System.out.println("共耗时："+(System.currentTimeMillis()-start)+"毫秒");
    }


}

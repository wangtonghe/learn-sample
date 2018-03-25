package com.wthfeng.learn.lang;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author wangtonghe
 * @date 2017/9/27 16:03
 */
public class ForkJoinTest {

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(new CountTask(1, 100_000));
        int ret = result.get();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(ret);
        forkJoinPool.shutdown();

    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();

        int total = 0;
        for (int i = 0; i <= 100_000; i++) {
            total += i;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(total);
    }
}

class CountTask extends RecursiveTask<Integer> {

    private int from;

    private int end;


    CountTask(int from, int end) {
        this.from = from;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int range = end - from;
        if (range <= 10) {
            int total = 0;
            for (int i = from; i <= end; i++) {
                total += i;
            }
            return total;
        } else {
            int middle = (from + end) / 2;
            CountTask leftTask = new CountTask(from, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();
        }
    }
}

package com.wthfeng.learn.intercurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author wangtonghe
 * @date 2017/9/26 09:03
 */
public class ForkJoinTest {


    @Test
    public void test() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer[] arr = {2, 3, 1, 5, 6, 4, 78, 21, 34};
        MergeSortTask task = new MergeSortTask(arr);
        Future<Integer[]> result = forkJoinPool.submit(task);
        try {
            Integer[] resArr = result.get();
            System.out.println(resArr);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test2() {

        int a=100;
        int result = a-10>100?a+10:a-10==90?a+10==110?a-20:100:1000;
        System.out.println(result);

        int res = a-10>90?100:a-80==20?200:300;
        System.out.println(res);

    }

}

class MergeSortTask extends RecursiveTask<Integer[]> {

    private int limit = 2;


    private Integer[] arr;

    public MergeSortTask(Integer[] arr) {
        this.arr = arr;
    }

    @Override
    protected Integer[] compute() {
        int length = arr.length;

        //分割到指定大小，开始计算
        if (length <= limit) {
            int first = arr[0];
            int second = arr[1];
            if (second < first) {
                arr[0] = second;
                arr[1] = first;
            }
            return arr;

        } else {
            int middle = length / 2;
            Integer[] left = Arrays.copyOfRange(arr, 0, middle);
            Integer[] right = Arrays.copyOfRange(arr, middle, length);
            MergeSortTask leftTask = new MergeSortTask(left);
            MergeSortTask rightTask = new MergeSortTask(right);
            leftTask.fork();
            rightTask.fork();
            Integer[] leftArr = leftTask.join();
            Integer[] rightArr = rightTask.join();
            Integer[] arr2 = new Integer[arr.length];
            System.arraycopy(leftArr, 0, arr2, 0, leftArr.length);
            System.arraycopy(rightArr, 0, arr2, 0, rightArr.length);
            Arrays.sort(arr2);
            return arr2;
        }
    }
}

class CountTask extends RecursiveTask<Integer> {

    @Override
    protected Integer compute() {
        return null;
    }
}

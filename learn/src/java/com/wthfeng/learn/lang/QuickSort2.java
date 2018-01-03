package com.wthfeng.learn.lang;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * @author wangtonghe
 * @date 2017/9/29 09:14
 */
public class QuickSort2 {



    @Test
    public  void test()throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] arr = {4, 1, 8, 6, 7, 9, 3, 2};
        SortTask sortTask = new SortTask(arr,0,arr.length-1);
        ForkJoinTask<Void> task =  forkJoinPool.submit(sortTask);
        task.get();
        Arrays.stream(arr).forEach(e->System.out.print(e+" "));
    }
}


class SortTask extends RecursiveAction{

    private int[] arr;

    private int low;

    private int high;

    public SortTask(int[] arr,int low,int high) {
        this.arr = arr;
        this.high = high;
        this.low = low;
    }

    @Override
    protected void compute() {
        if(low<high){
            int i=low+1,j=high,base = arr[low];
            while (i<j){
                while (arr[j]>=base&&i<j){
                    j--;
                }
                while (arr[i] < base && i < j) {
                    i++;
                }
                swap(arr,i,j);
            }
            swap(arr,low,j);
            SortTask leftTask =new  SortTask(arr,low,j-1);
            SortTask rightTask =new  SortTask(arr,j+1,high);
            leftTask.fork();
            rightTask.fork();
            leftTask.join();
            leftTask.join();
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}

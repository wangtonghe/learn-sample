package com.wthfeng.learn.lang;

import java.util.concurrent.RecursiveAction;

/**
 * @author wangtonghe
 * @date 2017/9/29 09:14
 */
public class QuickSort2 {


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
            int i=low,j=high,base = arr[low];
            while (i<j){
                while (arr[j]>=base&&i<j){
                    j--;
                }


            }

        }


    }
}

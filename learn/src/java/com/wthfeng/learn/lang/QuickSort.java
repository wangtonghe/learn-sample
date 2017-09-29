package com.wthfeng.learn.lang;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wangtonghe
 * @date 2017/9/26 22:15
 */
public class QuickSort {

    public void sort(int[] arr, int low, int high) {
        if (low < high) {
            int i = low, j = high;

            int base = arr[i];
            int tmp;
            while (i < j) {
                while (arr[j] >= base && i < j) {
                    j--;
                }
                tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
                while (arr[i] <= base && i < j) {
                    i++;
                }
                tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
            arr[i] = base;
            sort(arr, low, i - 1);
            sort(arr, i + 1, high);

        }

    }


    public void sort2(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low+1, j = high, base = arr[low];
        while (i < j) {
            while (arr[j] > base && i < j) {
                j--;
            }
            while (arr[i] < base && i < j) {
                i++;
            }
            swap(arr,i,j);
        }
        swap(arr,low,j);
        sort2(arr,low,j-1);
        sort2(arr,j+1,high);

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 快速排序算法
     */
    @Test
    public void test() {
        int[] arr = {4, 1, 8, 6, 7, 9, 3, 2};
        sort2(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }


}

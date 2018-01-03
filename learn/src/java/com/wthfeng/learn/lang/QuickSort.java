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


    public void quickSort(int[] arr, int low, int high) {
        // low,high 为每次处理数组时的首尾元素索引

        //当low==high是表示该序列只有一个元素，不必排序了
        if (low >= high) {
            return;
        }
        // 选出哨兵元素和基准元素。这里左边的哨兵元素为第2个元素（第一个为基准元素）
        int i = low+1, j = high, base = arr[low];
        while (i < j) {
            //右边哨兵从后向前找
            while (arr[j] > base && i < j) {
                j--;
            }
            //左边哨兵从前向后找
            while (arr[i] < base && i < j) {
                i++;
            }
            swap(arr,i,j);  //交换元素
        }
        swap(arr,low,j);  //基准元素与右哨兵交换

        //递归调用，排序左子集合和右子集合
        quickSort(arr,low,j-1);
        quickSort(arr,j+1,high);

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
        quickSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }


}

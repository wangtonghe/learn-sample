package com.wthfeng.learn.lang.alg;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wangtonghe
 * @since 2018/9/23 15:00
 */
public class MergeSort {

    public void mergeSort(int[] arr, int low, int high, int[] temp) {
        if (low < high) {
            int mid = (low + high) >> 1;
            mergeSort(arr, low, mid, temp);
            mergeSort(arr, mid + 1, high, temp);
            merge(arr, low, high, mid, temp);

        }


    }

    public void merge(int[] arr, int start, int end, int mid, int[] temp) {
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];

            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= end) {
            temp[k++] = arr[j++];
        }
        for (int l = 0; l < k; l++) {
            arr[start + l] = temp[l];
        }
    }

    @Test
    public void testMerge() {
        int[] arr = {4, 9, 3, 2, 1, 5, 8};


        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        printArray(arr);


    }

    public void printArray(int[] arr) {
        Arrays.stream(arr).forEach(e -> System.out.print(e + " "));

    }

    @Test
    public void testIndexAdd() {
        int k = 0;
        int[] arr = {1, 2, 3, 4};
        System.out.println(arr[k++]);
        System.out.println(arr[k++]);

    }
}

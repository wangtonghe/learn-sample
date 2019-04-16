package com.wthfeng.learn.lang.alg;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wangtonghe
 * @since 2018/9/24 16:35
 */
public class TestSort2 {

    public int topK(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int j = partSort(arr, left, right);
        int num = j - left + 1;
        if (k < num) {
            return topK(arr, left, j - 1, k);
        } else if (k > num) {
            return topK(arr, j + 1, right, k);
        } else {
            return j;
        }

    }


    public void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int j = partSort(arr, low, high);
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }

    public int partSort(int[] arr, int low, int high) {
        int base = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (arr[j] >= base && i < j) {
                j--;
            }
            while (arr[i] <= base && i < j) {
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, low, j);
        return j;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void printArr(int[] arr) {
        Arrays.stream(arr).forEach(e -> System.out.print(e + " "));
        System.out.println();
    }

    @Test
    public void testTopK() {
        int[] arr = {5, 7, 6, 9, 11, 19, 8, 2, 3, 12};
        int j = topK(arr, 0, arr.length - 1, 4);
        System.out.println(j);
        printArr(arr);

    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
            printArr(arr);
        }
    }

    public void selectSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    @Test
    public void testBubble() {
        int[] arr = {5, 7, 6, 9, 11, 19, 8, 2, 3, 12};
        bubbleSort(arr);
        printArr(arr);

    }

    @Test
    public void testSelect() {
        int[] arr = {5, 7, 6, 9, 11, 19, 8, 2, 3, 12};
        selectSort(arr);
        printArr(arr);

    }

}

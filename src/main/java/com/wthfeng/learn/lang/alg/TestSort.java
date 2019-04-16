package com.wthfeng.learn.lang.alg;

import org.junit.Test;

import java.util.Stack;

/**
 * @author wangtonghe
 * @since 2018/9/22 10:02
 */
public class TestSort {

    public void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int j = partSort(arr, low, high);
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);

    }

    public void quickSortNotR(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        while (!stack.empty()) {
            int j = stack.pop();
            int i = stack.pop();
            int k = partSort(arr, i, j);
            if (i < k - 1) {
                stack.push(i);
                stack.push(k - 1);
            }
            if (j > k + 1) {
                stack.push(k + 1);
                stack.push(high);
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void tesQuickSort() {
        int[] arr = {4, 1, 6, 3, 7, 9, 2, 10, 23, 100, 11};
        quickSort(arr, 0, arr.length - 1);
        printArray(arr);

    }

    @Test
    public void testSortNotR() {
        int[] arr = {4, 1, 6, 3, 7, 9, 2, 10, 23, 100, 11};
        quickSortNotR(arr);
        printArray(arr);


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

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }


}

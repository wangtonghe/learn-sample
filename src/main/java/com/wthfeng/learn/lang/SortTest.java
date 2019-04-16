package com.wthfeng.learn.lang;

import java.util.Arrays;

/**
 * 排序；练习
 *
 * @author wangtonghe
 * @date 2017/8/16 09:22
 */
public class SortTest {


    public int[] sort(int[] array) {


        quickSort(array, 0, array.length - 1);
        return array;

    }

    private void quickSort(int[] arr, int low, int high) {

        if (low > high) {
            return;
        }

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
//            if (i < j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
//            }
        }
        int tmp = arr[j];
        arr[j] = arr[low];
        arr[low] = tmp;

        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }

    public static void main(String[] args) {

        SortTest sortTest = new SortTest();


        int[] arr = {6, 1, 2, 5, 4, 3, 9, 7, 10, 8};

        sortTest.sort(arr);

        Arrays.stream(arr).forEach(e -> System.out.print(e + " "));


    }


}

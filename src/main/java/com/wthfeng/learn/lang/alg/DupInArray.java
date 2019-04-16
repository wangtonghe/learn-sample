package com.wthfeng.learn.lang.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangtonghe
 * @since 2019/2/23 10:00
 */
public class DupInArray {

    /**
     * 找出数据中重复的数，这些数的范围在[0,length-1]间，也即数组索引范围。可利用与索引范围一致的特点，
     * 尽量将数组放到对应索引处，如果放的时候已经有值了，则说明重复了。
     * <p>
     * 遍历数组，对每个值m,查看是否与索引值相等，若一致，则处理下一个
     * 若不相等，则需要将该数值放到对应索引处,则
     * 1. 比较该数值与对应索引处的值，相等则说明有重复
     * 2. 若不相等，交换两值
     *
     * @param array
     * @return
     */
    public static int[] findDupInArray(int[] array) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; ) {
            int m = array[i];
            // 索引值等于该值
            if (m == i) {
                i++;
                continue;
            }
            //找到重复值
            if (m == array[m]) {
                list.add(m);
                i++;
            } else {
                int tmp = array[m];
                array[m] = m;
                array[i] = tmp;
            }
        }
        return list.stream().mapToInt(i -> i).toArray();

    }

    public static void main(String[] args) {

        int[] arr = {3, 2, 1, 1, 0, 3};
        Arrays.stream(findDupInArray(arr)).forEach(a -> System.out.print(a + "\t"));

    }
}

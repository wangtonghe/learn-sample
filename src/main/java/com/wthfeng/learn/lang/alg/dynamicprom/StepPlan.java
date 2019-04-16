package com.wthfeng.learn.lang.alg.dynamicprom;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划问题
 *
 * @author wangtonghe
 * @since 2019/4/7 11:24
 */
public class StepPlan {

    /**
     * 走台阶问题,10阶台阶，每次走1阶或2阶，有多少种走法
     */
    @Test
    public void testStep() {

        int num = 6;

        Map<Integer, Integer> tmpMap = new HashMap<>();

        System.out.println(getStepNum2(num, tmpMap));

        System.out.println(getStepByDynamic(num));


    }

    private int getStepNum(int num) {

        if (num == 1) {
            return 1;
        } else if (num == 2) {
            return 2;
        }
        return getStepNum(num - 1) + getStepNum(num - 2);
    }

    private int getStepNum2(int num, Map<Integer, Integer> tempMap) {
        if (num < 1) {
            return 0;
        }
        if (num == 1) {
            return 1;
        } else if (num == 2) {
            return 2;
        }
        if (tempMap.containsKey(num)) {
            return tempMap.get(num);
        }
        int result = getStepNum2(num - 1, tempMap) + getStepNum2(num - 2, tempMap);
        if (tempMap.get(result) == null) {
            tempMap.put(num, result);
        }
        return result;
    }

    /**
     * 动态规划求解
     *
     * @return
     */
    private int getStepByDynamic(int num) {
        if (num < 1) {
            return 0;
        } else if (num == 1) {
            return 1;
        } else if (num == 2) {
            return 2;
        }
        int ppre = 1;
        int pre = 2;
        int cur = 0;
        for (int i = 3; i <= num; i++) {
            cur = ppre + pre;
            // ! 注意两变量赋值顺序，先赋值前前变量，防止被覆盖
            ppre = pre;
            pre = cur;
        }
        return cur;
    }


}

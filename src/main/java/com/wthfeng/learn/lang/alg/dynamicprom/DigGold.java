package com.wthfeng.learn.lang.alg.dynamicprom;

import org.junit.Test;

import java.util.Arrays;

/**
 * 动态规划-挖金矿问题
 * <p>
 * 动态规划有3种方式，
 * 1. 简单递归
 * 2. 备忘录
 * 3. 动态规划（自底向上递推）
 * <p>
 * 5座金矿，每座金矿的黄金储量不同，需要参与挖掘的工人数也不同。参与挖矿工人的总数是10人
 * A 200金 3人
 * B 300金 4人
 * C 350金 3人
 * D 400金 5人
 * E 500金 5人
 *
 * @author wangtonghe
 * @since 2019/4/7 12:16
 */
public class DigGold {


    @Test
    public void getDigGold() {
        int num = 20;

//        int[] result = digGold(num);
//        Arrays.stream(result).forEach(e -> System.out.print(e + " "));

        // 黄金数
        int[] g = {200, 300, 350, 400, 500};
        // 用工数
        int[] p = {6, 4, 5, 12, 13};

        int money = digGold3(5, 20, g, p);
        System.out.println();
        System.out.println(money);

    }

    /**
     * @param n 金矿数
     * @param w 工人数
     * @param g 金矿金额数组
     * @param p 金矿工人数
     * @return
     */
    public int digGold3(int n, int w, int[] g, int[] p) {
        if (n == 0 || w == 0) {
            return 0;
        }
        // 如果只有一个矿
        if (n == 1) {
            return w > p[0] ? g[0] : 0;
        }
        int[] pre = new int[w + 1];
        int[] cur = new int[w + 1];

        // 计算第一行
        for (int i = 1; i <= w; i++) {
            if (i < p[0]) {
                pre[i] = 0;
            } else {
                pre[i] = g[0];
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < p[i]) {
                    cur[j] = pre[j];
                } else {
                    int m1 = pre[j];
                    int m2 = pre[j - p[i]] + g[i];
                    cur[j] = m1 > m2 ? m1 : m2;
                }
            }
            pre = cur;
            cur = new int[w + 1];
        }
        return pre[w];
    }

    /**
     * 使用递归解决挖矿问题
     *
     * @param wnum 工人数
     * @param gnum 金矿数
     * @param g    各金矿金子数
     * @param p    各金矿使用工人数
     * @return
     */
    private int digGold2(int wnum, int gnum, int[] g, int[] p) {


        if (gnum == 0 || wnum == 0) {
            return 0;
        }
        // 如果只有一个矿
        if (gnum == 1) {
            return wnum > p[0] ? g[0] : 0;
        }

        // 少挖一个矿
        int goldTotal1 = digGold2(wnum, gnum - 1, g, p);
        //
        int goldTotal2 = digGold2(wnum - p[gnum - 1], gnum - 1, g, p) +
                digGold2(p[gnum - 1], 1, new int[]{g[gnum - 1]}, new int[]{p[gnum - 1]});

        return goldTotal1 > goldTotal2 ? goldTotal1 : goldTotal2;
    }

    /**
     * 挖不挖用0，1表示
     *
     * @param num 人数
     * @return
     */
    private int[] digGold(int num) {
        int[] golds = new int[5];
        int total = 1 << 5;

        int[] result = new int[total];
        int money;
        int people;

        for (int i = 0; i < total; i++) {
            if (i == 0) {
                result[0] = 0;
                continue;
            }
            String binStr = Integer.toBinaryString(i);
            int len = binStr.length();
            money = 0;
            people = 0;
            for (int j = len - 1; j >= 0; j--) {
                if (binStr.charAt(j) == '1') {
                    int no = 5 - (len - j - 1);
                    if (no == 1) {
                        money += 200;
                        people += 3;
                    } else if (no == 2) {
                        money += 300;
                        people += 4;
                    } else if (no == 3) {
                        money += 350;
                        people += 3;
                    } else if (no == 4) {
                        money += 400;
                        people += 5;
                    } else if (no == 5) {
                        money += 500;
                        people += 5;
                    }
                }
            }
            if (people <= num) {
                result[i] = money;
            }
        }
        int max = 0;
        int k = 0;
        for (int i = 0; i < total; i++) {
            int mon = result[i];
            if (mon > max) {
                max = mon;
                k = i;
            }
        }
        String finBin = Integer.toBinaryString(k);
        int len = finBin.length();
        int fillNum = 5 - len;

        for (int i = 0; i < fillNum; i++) {
            finBin = "0" + finBin;
        }
        for (int i = 0; i < 5; i++) {
            golds[i] = finBin.charAt(i) == '0' ? 0 : 1;

        }
        return golds;
    }


    @Test
    public void printBinStr() {
        int i = 10;
        System.out.println(Integer.toBinaryString(i));
    }
}

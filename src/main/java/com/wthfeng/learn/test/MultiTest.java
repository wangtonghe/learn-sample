package com.wthfeng.learn.test;

/**
 * @author wangtonghe
 * @since 2019/3/29 22:38
 */
public class MultiTest {

    public static void main(String[] args) {
        MultiTest multiTest = new MultiTest();
        String num1 = "999";
        String num2 = "999";
        System.out.println(num1.length());
        System.out.println(num2.length());
        String result = multiTest.multiply(num1, num2);
        System.out.println(result);
    }

    public String multiply(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        String result = "";
        String minStr = num1.length() > num2.length() ? num2 : num1;
        String maxStr = num1.length() > num2.length() ? num1 : num2;


        for (int i = minStr.length() - 1; i >= 0; i--) {

            String num = multi(maxStr, minStr.charAt(i), minStr.length() - i - 1);
            result = add2Result(result, num);

        }
        int j = 0;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) != '0' && result.charAt(i) != 0) {
                j = i;
                break;
            }
        }

        return result.substring(j);


    }

    private String multi(String num, char c, int index) {

        int len = num.length();

        int cb = 0;

        char[] res = new char[240];

        for (int i = len - 1; i >= 0; i--) {
            int r = ((int) num.charAt(i) - 48) * ((int) c - 48);
            int n = r % 10 + cb;
            if (n > 9) {
                n = n - 10;
                cb = 1;
            } else {
                cb = 0;
            }
            int pos = (len - i - 1) + index;
            res[res.length - pos - 1] = (char) (n + 48);
            cb = r / 10 + cb;
        }
        if (cb > 0) {
            res[res.length - len - 1 - index] = (char) (cb + 48);

        }

        return new String(res);

    }

    private String add2Result(String result, String num) {

        int cb = 0;

        if (result == null || result.equals("")) {
            return num;
        }

        char[] res = new char[240];
        for (int i = num.length() - 1; i >= 0; i--) {


            int r1 = (num.charAt(i) == '\0' ? 0 : num.charAt(i) - 48);
            int r2 = (result.charAt(i) == '\0' ? 0 : result.charAt(i) - 48);
            int r = r1 + r2 + cb;
            if (r > 9) {
                cb = 1;
                r = r - 10;
            } else {
                cb = 0;
            }
            res[i] = (char) (r + 48);
        }

        return new String(res);

    }


}

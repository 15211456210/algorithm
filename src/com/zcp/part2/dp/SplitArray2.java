package com.zcp.part2.dp;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/26
 * @description：[[正数数组分割为个数跟累加和接近的两个集合]] 返回和较小的数组和
 * @version:
 */
public class SplitArray2 {

    public static int solution(int[] array) {
        if (array == null || array.length < 1) {
            return -1;
        }

        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        if (array.length % 2 == 0) {
            //如果是双数
            return process(array, 0, array.length / 2, sum / 2);
        } else {
            //如果是单数，有两种情况，取最大值
            return Math.max(process(array, 0, array.length / 2, sum / 2), process(array, 0, array.length / 2 + 1, sum / 2));
        }
    }

    /**
     * 在index 之后再选择restN个数字，和不超过restS的最大值
     *
     * @param array
     * @param index
     * @param restN
     * @param restS
     * @return
     */
    private static int process(int[] array, int index, int restN, int restS) {
        if (index == array.length) {
            //如果到末尾了  选择的数字总个数 不符合要求 返回-1表示无效解，否则返回0
            return restN == 0 ? 0 : -1;
        }
        if (restN == 0) {
            //如果数字 个数 用完了，直接返回0
            return 0;
        }
        int p1 = process(array, index + 1, restN, restS);
        int p2 = -1;
        if (restS - array[index] >= 0) {
            p2 = array[index] + process(array, index + 1, restN - 1, restS - array[index]);
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] array) {
        if (array == null || array.length < 1) {
            return -1;
        }

        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        // index,restN,restS
        int[][][] dp = new int[array.length + 1][array.length / 2 + 2][sum / 2 + 1];
        for (int restS = 0; restS <= sum / 2; restS++) {
            for (int restN = 1; restN <= array.length / 2 + 1; restN++) {
                dp[array.length][restN][restS] = -1;
            }
        }

        for (int index = array.length - 1; index >= 0; index--) {
            for (int restN = 1; restN <= array.length / 2 + 1; restN++) {
                for (int restS = 0; restS <= sum / 2; restS++) {
                    int p1 = dp[index + 1][restN][restS];
                    int p2 = -1;
                    if (restS - array[index] >= 0) {
                        p2 = array[index] + dp[index + 1][restN - 1][restS - array[index]];
                    }
                    dp[index][restN][restS] = Math.max(p1, p2);
                }
            }
        }

        if (array.length % 2 == 0) {
            //如果是双数
            return dp[0][array.length / 2][sum / 2];
        } else {
            //如果是单数，有两种情况，取最大值
            return Math.max(dp[0][array.length / 2][sum / 2], dp[0][array.length / 2 + 1][sum / 2]);
        }
    }


    public static void main(String[] args) {
        int[] array = {2,100,2};
        System.out.println(solution(array));
        System.out.println(dp(array));
    }
}

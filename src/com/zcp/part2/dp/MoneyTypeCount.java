package com.zcp.part2.dp;

import java.util.Arrays;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/24
 * @description：[[面值数组组成面值的最少货币数]] https://leetcode-cn.com/problems/gaM7Ch/
 * @version:
 */
public class MoneyTypeCount {


    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length < 1) {
            return -1;
        }
        int count = process(0, coins, amount);
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    /**
     * coins[index....]组成remain的最小硬币个数
     *
     * @param index
     * @param coins
     * @param remain
     * @return
     */
    public int process(int index, int[] coins, int remain) {
        if (remain == 0) {
            return 0;
        }
        if (index == coins.length) {
            return Integer.MAX_VALUE;
        }
        //remain>0 index < coins.length
        int min = Integer.MAX_VALUE;
        for (int count = 0; count * coins[index] <= remain; count++) {
            int next = process(index + 1, coins, remain - count * coins[index]);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(min, count + next);
            }
        }
        return min;
    }


    public int dp1(int[] coins, int amount) {
        if (coins == null || coins.length < 1) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        for (int j = 1; j <= amount; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int remain = 1; remain <= amount; remain++) {
                int min = Integer.MAX_VALUE;
                for (int count = 0; count * coins[index] <= remain; count++) {
                    int next = dp[index + 1][remain - count * coins[index]];
                    if (next != Integer.MAX_VALUE) {
                        min = Math.min(min, count + next);
                    }
                }
                dp[index][remain] = min;
            }
        }
        int count = dp[0][amount];
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    public int dp2(int[] coins, int amount) {
        if (coins == null || coins.length < 1) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        for (int j = 1; j <= amount; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int remain = 1; remain <= amount; remain++) {
                int min = Integer.MAX_VALUE;
                min = Math.min(min, dp[index + 1][remain]);
                if (remain - coins[index] >= 0) {
                    if (remain - coins[index] == 0) {
                        min = 1;
                    } else {
                        if (dp[index][remain - coins[index]] != Integer.MAX_VALUE) {
                            min = Math.min(min, 1 + dp[index][remain - coins[index]]);
                        }
                    }
                }
                dp[index][remain] = min;
            }
        }
        int count = dp[0][amount];
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    public static void main(String[] args) {
        MoneyTypeCount moneyTypeCount = new MoneyTypeCount();
        int testTime = 1000;
        int size = 3;
        int range = 5;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
//            int[] array = generateArray(size, range);
            int[] array = {195,265,404,396};
//            int aim = (int) (Math.random() * size) * (int) (Math.random() * range);
            int aim = 3239;
            if (moneyTypeCount.dp1(array, aim) != moneyTypeCount.dp2(array, aim)){
                System.out.println("出错了");
                System.out.println(Arrays.toString(array) + "aim:" +aim);
                break;
            }
        }
        System.out.println("测试结束");

    }


    public static int[] generateArray(int size, int range) {
        int sz = (int) (Math.random() * size);
        int[] ints = new int[sz];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * range);
        }
        return ints;
    }

}

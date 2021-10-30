package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/23
 * @description：货币面值系列问题 [[面值数组组成面值的方法数-张数不限]]
 * https://leetcode-cn.com/problems/coin-change-2/
 * @version:
 */
public class CoinsSumMethods2 {


    public int change(int amount, int[] coins) {
        if (amount < 0 || coins == null || coins.length < 1) {
            return 0;
        }
        return process(amount, 0, coins);
    }

    /**
     * 从index位置货币开始组成rest金额的方法数
     *
     * @param rest  剩余金额
     * @param index 当前货币下标
     * @param coins
     * @return
     */
    public int process(int rest, int index, int[] coins) {
        if (index == coins.length && rest == 0) {
            return 1;
        }
        if (index == coins.length || rest < 0) {
            return 0;
        }

        int ways = 0;
        for (int count = 0; coins[index] * count <= rest; count++) {
            ways += process(rest - coins[index] * count, index + 1, coins);
        }
        return ways;
    }


    /**
     * 动态规划1
     * @param amount
     * @param coins
     * @return
     */
    public int dp(int amount, int[] coins) {
        if (amount < 0 || coins == null || coins.length < 1) {
            return 0;
        }
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[coins.length][0] = 1;
        for (int i = coins.length - 1; i >= 0; i--) {
            for (int j = 0; j < amount + 1; j++) {
                int ways = 0;
                for (int count = 0; coins[i] * count <= j; count++) {
                    ways += dp[i + 1][j - coins[i] * count];
                }
                dp[i][j] = ways;
            }
        }
        return dp[0][amount];
    }

    /**
     * 动态规划2
     * 空间压缩  二维转一维
     *
     * @param amount
     * @param coins
     * @return
     */
    public static int dp2(int amount, int[] coins) {
        if (amount < 0 || coins == null || coins.length < 1) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = coins.length-1; i>=0 ; i--) {
            for (int j = amount; j>=0; j--) {
                int ways = 0;
                for (int count = 0; coins[i] * count <= j; count++) {
                    ways += dp[j - coins[i] * count];
                }
                dp[j] = ways;
            }
        }
        return dp[amount];
    }


    /**
     * 动态规划3
     * 空间压缩  二维转一维
     * 内部循环优化
     * @param amount
     * @param coins
     * @return
     */
    public static int dp3(int amount, int[] coins) {
        if (amount < 0 || coins == null || coins.length < 1) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = coins.length-1; i>=0 ; i--) {
            for (int j = 0; j < amount+1; j++) {
                int ways = dp[j] + ((j-coins[i]<0) ? 0: dp[j-coins[i]]);
                dp[j] = ways;
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int aim = 5;
        int[] coins = {1,2,5};
        System.out.println(dp2(aim,coins));
        System.out.println(dp3(aim,coins));
    }
}

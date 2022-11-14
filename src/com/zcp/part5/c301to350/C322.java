package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C322
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/coin-change/submissions/
 * @date 2022/10/15 16:51
 */
public class C322 {

    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -2;
            }
        }
        return f(coins, 0, amount, dp);
    }

    public void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {

            for (int j = i; j >= 1 && nums[j - 1] <= nums[j]; --j) {
                int temp = nums[j - 1];
                nums[j - 1] = nums[j];
                nums[j] = temp;
            }


        }


    }


    public int f(int[] coins, int i, int rem, int[][] dp) {
        if (dp[i][rem] != -2) {
            return dp[i][rem];
        }
        if (rem == 0) {
            dp[i][rem] = 0;
            return 0;
        }
        if (rem < 0 || i >= coins.length) {
            dp[i][rem] = -1;
            return -1;
        }

        int minCnt = Integer.MAX_VALUE;

        int maxPic = rem / coins[i];
        for (int k = 0; k <= maxPic; k++) {
            int nextCnt = f(coins, i + 1, rem - k * coins[i], dp);
            if (nextCnt != -1) {
                minCnt = Math.min(minCnt, k + nextCnt);
            }

        }
        dp[i][rem] = (minCnt == Integer.MAX_VALUE ? -1 : minCnt);
        return dp[i][rem];

    }
}

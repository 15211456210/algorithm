package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * @date 2022/9/29 14:48
 */
public class C309 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[len + 1][2];
        dp[len - 1][1] = prices[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            dp[i][0] = Math.max(dp[i + 1][0], -prices[i] + dp[i + 1][1]);
            dp[i][1] = Math.max(dp[i + 1][1], prices[i] + dp[i + 2][0]);
        }
        return dp[0][0];
    }
}

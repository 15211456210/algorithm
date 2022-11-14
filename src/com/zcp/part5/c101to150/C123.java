package com.zcp.part5.c101to150;

/**
 * @author ZCP
 * @title: C123
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/submissions/
 * @date 2022/9/2 10:27
 */
public class C123 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int k = 2;
        int[][][] dp = new int[len + 1][k + 1][2];
        for (int i = 0; i < len + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                dp[i][j][0] = Integer.MIN_VALUE;
                dp[i][j][1] = Integer.MIN_VALUE;
            }
        }
        return process(prices, 0, 0, k, dp);
    }

    /**
     * 返回当前i位置开始,能获得的最大收益
     *
     * @param prices
     * @param i      当前来到第几天
     * @param hold   当前是否持有股 1：持有，0：不持有
     * @param remain 剩余多少交易次数
     * @param dp
     * @return
     */
    public int process(int[] prices, int i, int hold, int remain, int[][][] dp) {
        if (dp[i][remain][hold] != Integer.MIN_VALUE) {
            return dp[i][remain][hold];
        }
        if (i == prices.length || remain == 0) {
            dp[i][remain][hold] = 0;
            return 0;
        }
        int maxBenefit = 0;
        if (hold == 0) {
            //说明未持有股，可以进行买入，也可以不买
            //p1: 不买当前股
            //p2: 买当前股
            maxBenefit = Math.max(maxBenefit, process(prices, i + 1, 0, remain, dp));
            maxBenefit = Math.max(maxBenefit, -prices[i] + process(prices, i + 1, 1, remain, dp));

        } else {
            //当前持有股
            //p1：不卖持有股
            int p1 = process(prices, i + 1, 1, remain, dp);

            //p2:卖出持有股
            //p21:不买当前股
            //p22:买当前股
            int p2 = prices[i] + Math.max(process(prices, i + 1, 0, remain - 1, dp), -prices[i] + process(prices, i + 1, 1, remain - 1, dp));
            maxBenefit = Math.max(p1, p2);

        }
        dp[i][remain][hold] = maxBenefit;
        return maxBenefit;
    }
}

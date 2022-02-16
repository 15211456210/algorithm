package com.zcp.part4.day15;

//leetcode 188
public class Code04_BestTimeToBuyAndSellStockIV {


    /**
     * 思路:
     * 动态规划 + 记忆化搜索
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit3(int k, int[] prices) {
        if (k <= 0 || prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;

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


    public static int maxProfit(int K, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int N = prices.length;
        if (K >= N / 2) {
            return allTrans(prices);
        }
        int[][] dp = new int[K + 1][N];
        int ans = 0;
        for (int tran = 1; tran <= K; tran++) {
            int pre = dp[tran][0];
            int best = pre - prices[0];
            for (int index = 1; index < N; index++) {
                pre = dp[tran - 1][index];
                dp[tran][index] = Math.max(dp[tran][index - 1], prices[index] + best);
                best = Math.max(best, pre - prices[index]);
                ans = Math.max(dp[tran][index], ans);
            }
        }
        return ans;
    }

    public static int allTrans(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

    // 课上写的版本，对了
    public static int maxProfit2(int K, int[] arr) {
        if (arr == null || arr.length == 0 || K < 1) {
            return 0;
        }
        int N = arr.length;
        if (K >= N / 2) {
            return allTrans(arr);
        }
        int[][] dp = new int[N][K + 1];
        // dp[...][0] = 0
        // dp[0][...] = arr[0.0] 0
        for (int j = 1; j <= K; j++) {
            // dp[1][j]
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j - 1] - arr[1], dp[0][j - 1] - arr[0]);
            dp[1][j] = Math.max(p1, best + arr[1]);
            // dp[1][j] 准备好一些枚举，接下来准备好的枚举
            for (int i = 2; i < N; i++) {
                p1 = dp[i - 1][j];
                int newP = dp[i][j - 1] - arr[i];
                best = Math.max(newP, best);
                dp[i][j] = Math.max(p1, best + arr[i]);
            }
        }
        return dp[N - 1][K];
    }

}

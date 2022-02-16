package com.zcp.part4.day15;

//leetcode 714
public class Code06_BestTimeToBuyAndSellStockWithTransactionFee {


    /**
     * 思路：
     * 动态规划
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int[][] dp = new int[len + 1][2];
        dp[len - 1][1] = Math.max(0, prices[len - 1] - fee);

        for (int i = len - 2; i >= 0; i--) {
            dp[i][0] = Math.max(dp[i + 1][0], -prices[i] + dp[i + 1][1]);
            int p1 = dp[i + 1][1];
            int p2 = prices[i] - fee + dp[i + 1][0];
            int p3 = -fee + dp[i + 1][1];
            dp[i][1] = Math.max(Math.max(p1, p2), p3);
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new Code06_BestTimeToBuyAndSellStockWithTransactionFee().maxProfit2(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }

    public static int maxProfit(int[] arr, int fee) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        // 0..0   0 -[0] - fee
        int bestbuy = -arr[0] - fee;
        // 0..0  卖  0
        int bestsell = 0;
        for (int i = 1; i < N; i++) {
            // 来到i位置了！
            // 如果在i必须买  收入 - 批发价 - fee
            int curbuy = bestsell - arr[i] - fee;
            // 如果在i必须卖  整体最优（收入 - 良好批发价 - fee）
            int cursell = bestbuy + arr[i];
            bestbuy = Math.max(bestbuy, curbuy);
            bestsell = Math.max(bestsell, cursell);
        }
        return bestsell;
    }

}

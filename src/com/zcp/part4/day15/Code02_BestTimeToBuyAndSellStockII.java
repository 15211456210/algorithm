package com.zcp.part4.day15;

//leetcode 122
public class Code02_BestTimeToBuyAndSellStockII {


    /**
     * 思路：
     * 如果将每天的股价画成衣服折线图，
     * 那么计算每次曲线上升的值 总和就是最大收益
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int maxBenefit = 0;
        for (int i = 1; i < len; i++) {
            maxBenefit += Math.max(0, prices[i] - prices[i - 1]);
        }
        return maxBenefit;
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(prices[i] - prices[i - 1], 0);
        }
        return ans;
    }

}

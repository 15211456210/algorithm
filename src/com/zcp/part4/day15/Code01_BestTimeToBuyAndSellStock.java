package com.zcp.part4.day15;

// leetcode 121
public class Code01_BestTimeToBuyAndSellStock {

    /**
     * 思路：
     * 遍历数组，记录最小值，当前位置值-之前的最小值就是当前位置的最大收益
     * 最后取最大收益值
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int min = prices[0];
        int maxBenefit = 0;
        for (int i = 1; i < len; i++) {
            min = Math.min(min, prices[i]);
            maxBenefit = Math.max(maxBenefit, prices[i] - min);
        }
        return maxBenefit;
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 必须在0时刻卖掉，[0] - [0]
        int ans = 0;
        // arr[0...0]
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

}

package com.zcp.part5.c101to150;

/**
 * @author ZCP
 * @title: C122
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/submissions/
 * @date 2022/9/2 10:26
 */
public class C122 {

    public int maxProfit(int[] prices) {
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
}

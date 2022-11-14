package com.zcp.part5.c101to150;

/**
 * @author ZCP
 * @title: C121
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * @date 2022/9/2 10:24
 */
public class C121 {

    public int maxProfit(int[] prices) {
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
}

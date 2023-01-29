package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C410
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/split-array-largest-sum/description/
 * @date 2022/11/21 15:54
 */
public class C410 {
    public int splitArray(int[] nums, int k) {
        int len = nums.length;
        int[][] dp = new int[len][k + 1];
        int[] preSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            dp[i][1] = preSum[i + 1];
        }
        for (int i = 0; i < len; i++) {
            for (int j = 2; j <= k; j++) {
                if (j <= i + 1) {
                    for (int s = i - 1; s >= j - 2; s--) {
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[s][j - 1], preSum[i + 1] - preSum[s + 1]));
                    }
                }
            }
        }
        return dp[len - 1][k];
    }

    public static void main(String[] args) {
        System.out.println(new C410().splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }
}

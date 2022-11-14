package com.zcp.part5.c351to400;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C376
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/wiggle-subsequence/description/
 * @date 2022/10/28 16:28
 */
public class C376 {

    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        int[] up = new int[len];
        int[] down = new int[len];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = 1 + down[i - 1];
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = 1 + up[i - 1];
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }

        }
        return Math.max(up[len - 1], down[len - 1]);
    }

    public int wiggleMaxLength2(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][3];
        for (int i = 0; i < len; i++) {
            dp[i][0] = 1;
        }
        int max = 1;
        for (int i = 0; i < len; i++) {
            for (int l = i - 1; l >= 0; l--) {
                if (nums[i] != nums[l]) {
                    if (nums[i] > nums[l] && (dp[l][0] == 1 || dp[l][1] == 1) && 1 + dp[l][0] >= dp[i][0]) {
                        dp[i][1] = 1 + dp[l][0] == dp[i][0] ? dp[i][1] : 0;
                        dp[i][2] = 1;
                        dp[i][0] = 1 + dp[l][0];
                    }
                    if (nums[i] < nums[l] && (dp[l][0] == 1 || dp[l][2] == 1) && 1 + dp[l][0] >= dp[i][0]) {
                        dp[i][2] = 1 + dp[l][0] == dp[i][0] ? dp[i][2] : 0;
                        dp[i][1] = 1;
                        dp[i][0] = 1 + dp[l][0];
                    }
                }
                max = Math.max(max, dp[i][0]);
            }
        }
        return max;
    }
}

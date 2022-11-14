package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c032
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-valid-parentheses/submissions/
 * @date 2022/8/26 16:29
 */
public class c032 {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] partten = s.toCharArray();
        int len = partten.length;
        int[] dp = new int[len];
        int maxLen = 0;
        for (int i = 1; i < len; i++) {
            if (partten[i] == '(') {
                dp[i] = 0;
            } else {
                // ')'
                int preIndex = i - dp[i - 1] - 1;
                if (preIndex < 0) {
                    dp[i] = 0;
                } else if (partten[preIndex] == '(') {
                    dp[i] = (preIndex - 1 >= 0 ? dp[i - 1] + 2 + dp[preIndex - 1] : dp[i - 1] + 2);
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }
        return maxLen;
    }
}

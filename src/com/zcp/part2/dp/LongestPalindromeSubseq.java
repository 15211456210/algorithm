package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/22
 * @description： 516.[[最长回文子序列]] [M]
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * @version:
 */
public class LongestPalindromeSubseq {


    /**
     * 暴力递归版本
     *
     * @param s
     * @return
     */
    public int solution(String s) {
        if (s == null || s.length() < 1) {
            return -1;
        }
        return process(s.toCharArray(), 0, s.length() - 1);
    }

    /**
     * char[L...R]上的最长回文子序列
     *
     * @param str
     * @param L
     * @param R
     * @return
     */
    public int process(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L + 1 == R && str[L] == str[R]) {
            return 2;
        }

        int max = 0;
        //p1 str[L] == str[R]
        if (str[L] == str[R]) {
            max = Math.max(max, 2 + process(str, L + 1, R - 1));
        }
        //p2 L+1 --- R
        max = Math.max(max, process(str, L + 1, R));
        //p3 L --- R+1
        max = Math.max(max, process(str, L, R - 1));
        return max;
    }

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() < 1) {
            return -1;
        }
        char[] str = s.toCharArray();
        int length = str.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
            if (i + 1 < length) {
                dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
            }
        }

        for (int i = length - 3; i >= 0; i--) {
            for (int j = i + 2; j < length; j++) {
                int max = 0;
                //p1 str[L] == str[R]
                if (str[i] == str[j]) {
                    max = Math.max(max, 2 + dp[i + 1][j - 1]);
                }
                //p2 L+1 --- R
                max = Math.max(max, dp[i + 1][j]);
                //p3 L --- R+1
                max = Math.max(max, dp[i][j - 1]);
                dp[i][j] = max;
            }
        }
        return dp[0][length - 1];
    }


}

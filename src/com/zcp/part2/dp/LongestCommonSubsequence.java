package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/20
 * @description：[两个字符串的最长公共子序列]]https://leetcode-cn.com/problems/qJnOS7/
 * @version:
 */
public class LongestCommonSubsequence {

    /**
     * 递归尝试
     *
     * @param text1
     * @param text2
     * @return
     */
    public int solution1(String text1, String text2) {
        if (text1 == null || text1.length() < 1 || text2 == null || text2.length() < 1) {
            return 0;
        }
        return process1(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    /**
     * text1 0-i
     * text2 0-j
     * 上的最大公共子序列
     *
     * @param text1
     * @param text2
     * @param i
     * @param j
     * @return
     */
    public int process1(String text1, String text2, int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        int max = 0;
        //p1  和i,j都有关
        if (text1.charAt(i) == text2.charAt(j)) {
            max = Math.max(1 + process1(text1, text2, i - 1, j - 1), max);
        } else {
            //p2  可能和i有关，但和j无关
            max = Math.max(process1(text1, text2, i, j - 1), max);
            //p3  可能和j有关，但和i无关
            max = Math.max(process1(text1, text2, i - 1, j), max);
        }
        return max;
    }

    /**
     * 动态规划版本
     *
     * @param text1
     * @param text2
     * @return
     */
    public int solution2(String text1, String text2) {
        if (text1 == null || text1.length() < 1 || text2 == null || text2.length() < 1) {
            return 0;
        }
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 0; i < l1 + 1; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < l2 + 1; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < l1 + 1; i++) {
            for (int j = 1; j < l2 + 1; j++) {
                int max = 0;
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    max = Math.max(1 + dp[i - 1][j - 1], max);
                } else {
                    //p2  可能和i有关，但和j无关
                    max = Math.max(dp[i][j - 1], max);
                    //p3  可能和j有关，但和i无关
                    max = Math.max(dp[i - 1][j], max);
                }
                dp[i][j] = max;
            }
        }
        return dp[l1][l2];
    }


    public static void main(String[] args) {
//        "mhunuzqrkzsnidwbun"
//        "szulspmhwpazoxijwbq"
        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        System.out.println(longestCommonSubsequence.solution1("bl", "yby"));
        System.out.println(longestCommonSubsequence.solution2("bl", "yby"));
    }
}

package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c087
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/scramble-string/submissions/
 * @date 2022/8/31 9:28
 */
public class c087 {

    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (!kindNumCheck(s1, s2)) {
            return false;
        }

        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        int len = char1.length;
        int[][][] dp = new int[len][len][len + 1];//0:未计算，1：true,-1:false
        return process4(char1, char2, 0, 0, len, dp);
    }


    /**
     * s1[l1,l1+1,l1+2,...,l1+len] 与 s2[l2,l2+1,l2+2,...,l2+len]是否是扰乱字符串
     *
     * @param s1
     * @param s2
     * @param l1
     * @param l2
     * @param dp
     * @return
     */
    public boolean process4(char[] s1, char[] s2, int l1, int l2, int len, int[][][] dp) {
        if (dp[l1][l2][len] != 0) {
            return dp[l1][l2][len] == 1;
        }
        if (len == 1) {
            dp[l1][l2][len] = (s1[l1] == s2[l2] ? 1 : -1);
            return s1[l1] == s2[l2];
        }
        for (int leftCnt = 1; leftCnt < len; leftCnt++) {
            if ((process4(s1, s2, l1, l2, leftCnt, dp) && process4(s1, s2, l1 + leftCnt, l2 + leftCnt, len - leftCnt, dp)) ||
                    (process4(s1, s2, l1, l2 + len - leftCnt, leftCnt, dp) && process4(s1, s2, l1 + leftCnt, l2, len - leftCnt, dp))) {
                dp[l1][l2][len] = 1;
                return true;
            }
        }
        dp[l1][l2][len] = -1;
        return false;
    }

    /**
     * 字符种类和数量校验
     *
     * @return
     */
    private boolean kindNumCheck(String s1, String s2) {
        int[] bucket = new int[26];
        char[] char1 = s1.toCharArray();
        for (int i = 0; i < char1.length; i++) {
            bucket[char1[i] - 'a']++;
        }

        char[] char2 = s2.toCharArray();
        for (int i = 0; i < char2.length; i++) {
            bucket[char1[i] - 'a']--;
        }

        for (int i : bucket) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}

package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c097
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/interleaving-string/submissions/
 * @date 2022/9/1 15:50
 */
public class c097 {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        int N1 = s1.length();
        int N2 = s2.length();
        int N3 = s3.length();
        if (N1 + N2 != N3) {
            return false;
        }
        int[][][] dp = new int[N1 + 1][N2 + 1][N3 + 1];
        return process(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0, dp);
    }

    private boolean process(char[] c1, char[] c2, char[] c3, int i1, int i2, int i3, int[][][] dp) {
        if (dp[i1][i2][i3] != 0) {
            return dp[i1][i2][i3] == 1;
        }
        if (i1 == c1.length && i2 == c2.length && i3 == c3.length) {
            dp[i1][i2][i3] = 1;
            return true;
        }
        boolean ans = false;
        if (i1 < c1.length && c1[i1] == c3[i3]) {
            ans = ans || process(c1, c2, c3, i1 + 1, i2, i3 + 1, dp);
        }
        if (i2 < c2.length && c2[i2] == c3[i3]) {
            ans = ans || process(c1, c2, c3, i1, i2 + 1, i3 + 1, dp);
        }
        dp[i1][i2][i3] = ans ? 1 : -1;
        return ans;
    }
}

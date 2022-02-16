package com.zcp.part4.day04;

// 本题测试链接 : https://leetcode.com/problems/interleaving-string/
public class Code07_InterleavingString {


    /**
     * 思路：
     * 动态规划
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        int N1 = s1.length();
        int N2 = s2.length();
        int N3 = s3.length();
        if (N1 + N2 != N3) {
            return false;
        }
        int[][][] dp = new int[N1 + 1][N2 + 1][N3 + 1];//生成三维dp表 0：表示之前没算过，-1：false 1：true
        return process(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0, dp);
    }

    /**
     * 从c1[i1] c2[i2] 开始 能否组成 c3[c3]往后的字符串
     * @param c1
     * @param c2
     * @param c3
     * @param i1
     * @param i2
     * @param i3
     * @param dp
     * @return
     */
    private boolean process(char[] c1, char[] c2, char[] c3, int i1, int i2, int i3, int[][][] dp) {
        if (dp[i1][i2][i3] != 0) {
            return dp[i1][i2][i3] == 1;
        }
        if (i1 == c1.length && i2 == c2.length && i3 == c3.length) {
            //如果 3下标都到了尾部，说明可以构成
            dp[i1][i2][i3] = 1;
            return true;
        }
        boolean ans = false;
        //两种情况
        if (i1 < c1.length && c1[i1] == c3[i3]) {
            ans = ans || process(c1, c2, c3, i1 + 1, i2, i3 + 1, dp);
        }
        if (i2 < c2.length && c2[i2] == c3[i3]) {
            ans = ans || process(c1, c2, c3, i1, i2 + 1, i3 + 1, dp);
        }
        dp[i1][i2][i3] = ans ? 1 : -1;
        return ans;
    }


    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str3.length != str1.length + str2.length) {
            return false;
        }
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j <= str2.length; j++) {
            if (str2[j - 1] != str3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (
                        (str1[i - 1] == str3[i + j - 1] && dp[i - 1][j])


                                ||


                                (str2[j - 1] == str3[i + j - 1] && dp[i][j - 1])


                ) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }

}

package com.zcp.part5.c101to150;

/**
 * @author ZCP
 * @title: C132
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/palindrome-partitioning-ii/submissions/
 * @date 2022/9/6 15:01
 */
public class C132 {

    public int minCut(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = s.length();
        //生成是否回文串dp表
        boolean[][] checkPalindrome = getCheckPalindorme(chars);

        //创建dp
        int[] dp = new int[len];
        dp[len - 2] = chars[len - 1] == chars[len - 2] ? 0 : 1;

        for (int start = len - 3; start >= 0; start--) {
            dp[start] = len - start - 1;
            for (int end = start; end < len; end++) {
                if (checkPalindrome[start][end]) {
                    if (end == len - 1) {
                        dp[start] = 0;
                        break;
                    } else {
                        dp[start] = Math.min(dp[start], dp[end + 1] + 1);
                    }
                }
            }
        }
        return dp[0];
    }

    /**
     * 获取校验是否回文串的 dp表
     *
     * @param chars
     * @return
     */
    private boolean[][] getCheckPalindorme(char[] chars) {
        int len = chars.length;
        boolean[][] checkPalindrome = new boolean[len][len];
        for (int i = 0; i < len - 1; i++) {
            checkPalindrome[i][i] = true;
            checkPalindrome[i][i + 1] = (chars[i] == chars[i + 1]);
        }
        checkPalindrome[len - 1][len - 1] = true;

        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                if (chars[i] == chars[j]) {
                    checkPalindrome[i][j] = checkPalindrome[i + 1][j - 1];
                }
            }
        }
        return checkPalindrome;
    }
}

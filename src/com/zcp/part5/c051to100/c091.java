package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c091
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/decode-ways/submissions/
 * @date 2022/8/31 16:33
 */
public class c091 {


    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();

        int[] dp = new int[chars.length + 1];
        dp[dp.length - 1] = 1;
        int num;
        for (int i = dp.length - 2; i >= 0; i--) {
            if (chars[i] - '0' == 0) {
                dp[i] = 0;
                continue;
            }
            num = chars[i] - '0';
            dp[i] += dp[i + 1];
            if (num == 1 && i + 1 < chars.length) {
                dp[i] += dp[i + 2];
            } else if (num == 2 && i + 1 < chars.length && chars[i + 1] - '0' <= 6) {
                dp[i] += dp[i + 2];
            }

        }
        return dp[0];
    }

//    public int numDecodings(String s) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//
//        char[] chars = s.toCharArray();
//        return process(chars, 0);
//    }

    public int process(char[] s, int i) {
        if (i >= s.length) {
            return 1;
        }

        int num = s[i] - '0';
        if (num == 0) {
            return 0;
        }

        int res = 0;
        res += process(s, i + 1);
        if (num == 1 && i + 1 < s.length) {
            res += process(s, i + 2);
        } else if (num == 2 && i + 1 < s.length && s[i + 1] - '0' <= 6) {
            res += process(s, i + 2);
        }
        return res;
    }

}

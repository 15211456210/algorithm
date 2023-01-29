package com.zcp.part5.c401to450;

import java.util.HashMap;

/**
 * @author ZCP
 * @title: C423
 * @projectName algorithm
 * @description: https://leetcode.com/problems/reconstruct-original-digits-from-english/
 * @date 2023/1/23 23:20
 */
public class C423 {
    //    ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].


    int[] charCount = new int[26];

    String[] digits = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};


    /**
     * e: 0,1,3,5,7,8,9
     * g: 8
     * f: 4,5
     * i: 5,6,8,9
     * h: 3,8
     * o: 0,1,2,4
     * n: 1,7,9
     * s: 6,7
     * r: 0,3,4
     * u: 4
     * t: 2,3,8
     * w: 2
     * v: 5,7
     * x: 6
     * z: 0
     *
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        int[] charCount = new int[26];
        int[] cnt = new int[10];
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
        }
        // 0,2,4,6,8
        cnt[0] = charCount['z' - 'a'];
        cnt[2] = charCount['w' - 'a'];
        cnt[4] = charCount['u' - 'a'];
        cnt[6] = charCount['x' - 'a'];
        cnt[8] = charCount['g' - 'a'];

        // 3,5,7
        cnt[3] = charCount['h' - 'a'] - cnt[8];
        cnt[5] = charCount['f' - 'a'] - cnt[4];
        cnt[7] = charCount['s' - 'a'] - cnt[6];

        // 1,9
        cnt[1] = charCount['o' - 'a'] - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = (charCount['n' - 'a'] - cnt[1] - cnt[7]) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt.length; i++) {
            for (int n = 0; n < cnt[i]; n++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }


//    public String originalDigits(String s) {
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            charCount[c - 'a']++;
//        }
//        return dfs(charCount, 0);
//    }
//
//    public String dfs(int[] charCount, int numIdx) {
//        if ("x".equals(illegal(charCount))) {
//            // 失败的尝试
//            return "x";
//        }
//        if (numIdx > 9) {
//            if ("0".equals(illegal(charCount))) {
//                return "";
//            } else {
//                return "x";
//            }
//        }
//        String digitStr = digits[numIdx];
//        // 判断是否可以
//        boolean can = true;
//        for (int i = 0; i < digitStr.length(); i++) {
//            if (--charCount[digitStr.charAt(i) - 'a'] < 0) {
//                can = false;
//            }
//        }
//        if (can) {
//            int[] copy = new int[26];
//            System.arraycopy(charCount, 0, copy, 0, charCount.length);
//            String dfs = dfs(copy, numIdx);
//            if (!"x".equals(dfs)) {
//                return numIdx + dfs;
//            }
//            dfs = dfs(charCount, numIdx + 1);
//            return "x".equals(dfs) ? "x" : numIdx + dfs;
//        } else {
//            for (int i = 0; i < digitStr.length(); i++) {
//                charCount[digitStr.charAt(i) - 'a']++;
//            }
//            return dfs(charCount, numIdx + 1);
//        }
//
//    }
//
//    private String illegal(int[] charCount) {
//        int sum = 0;
//        for (int i = 0; i < charCount.length; i++) {
//            if (charCount[i] < 0) {
//                return "x";
//            }
//            sum += charCount[i];
//        }
//        return sum == 0 ? "0" : "1";
//
//    }


}

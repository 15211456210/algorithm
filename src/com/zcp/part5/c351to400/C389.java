package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C389
 * @projectName algorithm
 * @description: https://leetcode.com/problems/find-the-difference/
 * @date 2022/11/3 10:18
 */
public class C389 {

    public char findTheDifference(String s, String t) {
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--chars[t.charAt(i) - 'a'] < 0) {
                return t.charAt(i);
            }
        }
        return 0;

    }
}

package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c058
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/length-of-last-word/submissions/
 * @date 2022/8/28 18:41
 */
public class c058 {

    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int end = 0;
        int begin = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] != ' ') {
                end = i;
                break;
            }
        }
        for (int i = chars.length - 1; i >= 0; i--) {
            if (i < end && chars[i] == ' ') {
                begin = i + 1;
                break;
            }
        }
        return end - begin + 1;

    }
}

package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C344
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/reverse-string/description/
 * @date 2022/10/20 9:26
 */
public class C344 {

    public void reverseString(char[] s) {
        if (s == null || s.length <= 1) {
            return;
        }
        int l = 0, r = s.length - 1;
        char tmp;
        while (l < r) {
            tmp = s[l];
            s[l++] = s[r];
            s[r--] = tmp;
        }
    }
}

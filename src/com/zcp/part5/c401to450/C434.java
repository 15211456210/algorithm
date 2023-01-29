package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C434
 * @projectName algorithm
 * @description: https://leetcode.com/problems/number-of-segments-in-a-string/
 * @date 2023/1/28 13:59
 */
public class C434 {

    public int countSegments(String s) {
        int count = 0;
        char pre = ' ';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ' && pre == ' ') {
                count++;
            }
            pre = c;
        }
        return count;
    }
}

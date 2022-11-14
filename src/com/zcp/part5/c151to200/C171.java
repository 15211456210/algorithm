package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C171
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/excel-sheet-column-number/
 * @date 2022/9/9 9:38
 */
public class C171 {

    public int titleToNumber(String columnTitle) {
        int ans = 0;
        int mul = 1;
        for (int i = columnTitle.length() - 1; i >= 0; --i) {
            ans += ((columnTitle.charAt(i) - 'A') + 1) * mul;
            mul *= 26;
        }
        return ans;
    }
}

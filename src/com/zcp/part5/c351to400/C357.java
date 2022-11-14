package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C357
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/count-numbers-with-unique-digits/
 * @date 2022/10/24 17:20
 */
public class C357 {

    public int countNumbersWithUniqueDigits(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans += (A(10, i) - A(9, i - 1));
        }
        return ans;
    }

    private int A(int m, int n) {
        int ans = 1;
        for (int i = 0; i < n; i++) {
            ans *= (m - i);
        }
        return ans;
    }
}

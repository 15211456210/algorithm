package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C172
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/factorial-trailing-zeroes/submissions/
 * @date 2022/9/9 9:52
 */
public class C172 {
    public int trailingZeroes(int n) {
        int ans = 0;
        int mod = 5;
        while (n / mod > 0) {
            ans += n / mod;
            mod *= 5;
        }
        return ans;
    }
}

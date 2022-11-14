package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C191
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/number-of-1-bits/submissions/
 * @date 2022/9/13 16:41
 */
public class C191 {

    public int hammingWeight(int n) {
        int ans = 0;
        for (int i = 0; i <= 31; i++) {
            ans += ((n >>> i) & 1);
        }
        return ans;
    }
}

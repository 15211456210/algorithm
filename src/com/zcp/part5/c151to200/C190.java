package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C190
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/reverse-bits/
 * @date 2022/9/13 16:25
 */
public class C190 {

    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 31; i >= 0; i--) {
            ans |= ((n >>> i) & 1) << (31 - i);
        }
        return ans;
    }
}

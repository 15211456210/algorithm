package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C231
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/power-of-two/submissions/
 * @date 2022/9/19 14:26
 */
public class C231 {

    public boolean isPowerOfTwo(int n) {
        int t = 1;
        for (int i = 0; i <= 31; i++) {
            if (t < 0) {
                break;
            }
            if (n == t) {
                return true;
            }
            t *= 2;
        }
        return false;
    }
}

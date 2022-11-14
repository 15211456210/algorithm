package com.zcp.part5.c251to300;

/**
 * @author ZCP
 * @title: C263
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/ugly-number/
 * @date 2022/9/23 9:46
 */
public class C263 {

    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1;
    }
}

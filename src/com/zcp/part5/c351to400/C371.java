package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C371
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/sum-of-two-integers/description/
 * @date 2022/10/26 10:52
 */
public class C371 {

    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}

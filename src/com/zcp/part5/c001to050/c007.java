package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c007
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/reverse-integer/submissions/
 * @date 2022/8/25 13:28
 */
public class c007 {

    public int reverse(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x /= 10;
        }
        return (int) n == n ? (int) n : 0;
    }
}

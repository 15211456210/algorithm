package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C400
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/nth-digit/description/
 * @date 2022/11/12 11:18
 */
public class C400 {

    public int findNthDigit(int n) {
        long i = 1;
        long d = 9;
        int s = 1;
        while (n > i * d) {
            n -= i * d;
            i++;
            d *= 10;
            s *= 10;
        }

        long num = s + (n - 1) / i;

        return Integer.valueOf(String.valueOf(num).substring((int) ((n - 1) % i), (int) ((n - 1) % i + 1)));
    }
}

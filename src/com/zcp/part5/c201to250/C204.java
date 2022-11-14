package com.zcp.part5.c201to250;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C204
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/count-primes/
 * @date 2022/9/16 8:50
 */
public class C204 {
    static int[] bitmap = new int[250000];

    public int countPrimes(int n) {
        Arrays.fill(bitmap, 0);
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if ((((bitmap[i / 32]) >>> (31 - i % 32)) & 1) == 0) {
                ++ans;
                int num = i;
                while (num < n) {
                    bitmap[num / 32] |= (1 << (31 - num % 32));
                    num += i;
                }
            }
        }
        return ans;
    }
}

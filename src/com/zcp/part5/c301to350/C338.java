package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C338
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/counting-bits/submissions/
 * @date 2022/10/19 17:04
 */
public class C338 {

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int num = i;
            int count = 0;
            for (int k = 31; k >= 0; k--) {
                int div = 1 << k;
                if (num / div > 0) {
                    num = num % div;
                    ++count;
                }
            }
            ans[i] = count;
        }
        return ans;
    }
}

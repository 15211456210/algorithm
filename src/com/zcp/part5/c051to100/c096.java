package com.zcp.part5.c051to100;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: c096
 * @projectName algorithm
 * @description: https://leetcode.com/problems/unique-binary-search-trees/
 * @date 2022/9/1 15:45
 */
public class c096 {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return fun(n, dp);
    }

    public int fun(int n, int[] dp) {
        if (n <= 1) {
            return 1;
        }
        if (dp[n] != -1) {
            return dp[n];
        }

        int num = 0;
        for (int ln = 0; ln < n / 2; ln++) {
            // 左子树种数 * 右子树种数
            num += 2 * numTrees(ln) * numTrees(n - ln - 1);

        }
        if (n % 2 != 0) {
            num += numTrees(n / 2) * numTrees(n / 2);
        }
        dp[n] = num;
        return num;
    }
}

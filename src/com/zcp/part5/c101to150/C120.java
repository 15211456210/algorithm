package com.zcp.part5.c101to150;

import java.util.Arrays;
import java.util.List;

/**
 * @author ZCP
 * @title: C120
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/triangle/submissions/
 * @date 2022/9/1 17:10
 */
public class C120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        int deep = triangle.size();
        int[][] dp = new int[deep + 1][deep + 1];
        for (int i = 0; i <= deep; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        return process(triangle, 0, 0, dp);
    }

    public int process(List<List<Integer>> triangle, int i, int j, int[][] dp) {
        if (j < 0 || j >= triangle.get(i).size()) {
            return Integer.MAX_VALUE;
        }
        if (i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }
        if (dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }
        dp[i][j] = triangle.get(i).get(j) + Math.min(process(triangle, i + 1, j, dp), process(triangle, i + 1, j + 1, dp));
        return dp[i][j];

    }
}

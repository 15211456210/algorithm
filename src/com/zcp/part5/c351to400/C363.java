package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C363
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/max-sum-of-rectangle-no-larger-than-k/
 * @date 2022/10/24 17:21
 */
public class C363 {

    int[][] preSum;

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        preSum = new int[m + 1][n + 1];
        init(matrix);

        int ans = Integer.MAX_VALUE;
        for (int i1 = 0; i1 < m; i1++) {
            for (int j1 = 0; j1 < n; j1++) {

                for (int i2 = i1; i2 < m; i2++) {
                    for (int j2 = j1; j2 < n; j2++) {
                        int sum = sum(i1, j1, i2, j2);
                        if (sum == k) {
                            return k;
                        } else if (sum < k) {
                            if (ans == Integer.MAX_VALUE) {
                                ans = sum;
                            } else {
                                ans = Math.max(ans, sum);
                            }
                        }
                    }
                }
            }
        }
        return ans;

    }

    private int sum(int i1, int j1, int i2, int j2) {
        return preSum[i2 + 1][j2 + 1] - preSum[i2 + 1][j1] - preSum[i1][j2 + 1] + preSum[i1][j1];
    }

    private void init(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    public static void main(String[] args) {
        // [[1,0,1],[0,-2,3]] 2
        System.out.println(new C363().maxSumSubmatrix(new int[][]{{2, 2, -1}}, 0));

    }
}

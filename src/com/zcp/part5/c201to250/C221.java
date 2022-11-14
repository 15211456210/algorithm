package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C221
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/maximal-square/submissions/
 * @date 2022/9/18 17:16
 */
public class C221 {

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] r = new int[m][n];
        int[][] d = new int[m][n];
        r[m - 1][n - 1] = matrix[m - 1][n - 1] == '1' ? 1 : 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '0') {
                    r[i][j] = 0;
                    d[i][j] = 0;
                } else {
                    r[i][j] = 1;
                    d[i][j] = 1;
                    if (j + 1 < n) {
                        r[i][j] = r[i][j] + r[i][j + 1];
                    }
                    if (i + 1 < m) {
                        d[i][j] = d[i][j] + d[i + 1][j];
                        ;
                    }
                }
            }

        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {

                    int maxExpect = Math.min(r[i][j], d[i][j]);
                    int curMax = maxExpect;
                    for (int k = 1; k < maxExpect; k++) {
                        int nexti = i + k;
                        int nextj = j + k;
                        int maxSubExpect = maxExpect - k;
                        int maxSubFact = Math.min(r[nexti][nextj], d[nexti][nextj]);
                        if (maxSubFact <= maxSubExpect) {
                            curMax = Math.min(curMax, maxSubFact + k);
                        }
                    }
                    max = Math.max(max, curMax);

                }
            }
        }
        return max * max;
    }
}

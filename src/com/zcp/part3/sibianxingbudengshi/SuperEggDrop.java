package com.zcp.part3.sibianxingbudengshi;

/**
 * @description:https://leetcode.com/problems/super-egg-drop
 * @projectName:algorithm
 * @see:com.zcp.part3.sibianxingbudengshi
 * @author:ZCP
 * @createTime:2021/12/1
 * @version:1.0
 */
public class SuperEggDrop {

    /**
     * 最优解，二维表[k][m] k:鸡蛋数，m:扔次数
     *
     * @param k 鸡蛋
     * @param n 楼层
     * @return
     */
    public int superEggDrop(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        int[] dp = new int[k + 1];
        if (n == 1) {
            return 1;
        }
        int dropCount = 1;
        for (int i = 1; i < k + 1; i++) {
            dp[i] = 1;
        }
        boolean notMoreThen = true;
        while (notMoreThen) {
            dropCount++;
            for (int i = k; i > 0; i--) {
                dp[i] = dp[i] + dp[i - 1] + 1;
                if (dp[i] >= n) {
                    notMoreThen = false;
                    break;
                }
            }
        }
        return dropCount;
    }

    /**
     * 普通dp
     *
     * @param k 鸡蛋
     * @param n 楼层
     * @return
     */
    public int superEggDrop2(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[1][i] = i;
        }
        for (int i = 1; i < k + 1; i++) {
            dp[i][1] = 1;
        }
        for (int i = 2; i < k + 1; i++) {
            for (int j = 2; j < n + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int m = 1; m <= j; m++) {
                    //从m层开始试
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i][j - m], dp[i - 1][m - 1]) + 1);
                }
            }
        }
        return dp[k][n];
    }


    /**
     * 四边形不等式优化dp
     *
     * @param k 鸡蛋
     * @param n 楼层
     * @return
     */
    public int superEggDrop3(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[k + 1][n + 1];
        int[][] best = new int[k + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[1][i] = i;
        }
        for (int i = 1; i < k + 1; i++) {
            dp[i][1] = 1;
        }
        for (int j = 2; j < n + 1; j++) {
            for (int i = k; i > 1; i--) {
                dp[i][j] = Integer.MAX_VALUE;
                int down = i == k ? j : best[i + 1][j];
                int left = j == 2 ? 1 : best[i][j - 1];
                for (int m = left; m <= down; m++) {
                    //从m层开始试
                    int caseCount = Math.max(dp[i][j - m], dp[i - 1][m - 1]) + 1;
                    if (caseCount <= dp[i][j]) {
                        dp[i][j] = caseCount;
                        best[i][j] = m;
                    }
                }
            }
        }
        return dp[k][n];
    }

}

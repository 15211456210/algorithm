package com.zcp.part4.day10;

// 测试链接 : https://leetcode.com/problems/k-inverse-pairs-array/
public class Code03_KInversePairs {


    /**
     * p2:优化点：
     * 斜率优化
     * 思路：
     * 动态规划
     *
     * @param n
     * @param k
     * @return
     */
    public int kInversePairs4(int n, int k) {
        if (n <= 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int mod = 1000000007;
                int ans = (dp[i][j - 1] + dp[i - 1][j] + mod) % mod;
                if (i <= j) {
                    ans = (ans - dp[i - 1][j - i] + mod) % mod;
                }
                dp[i][j] = ans;
            }
        }
        return dp[n][k];
    }

    /**
     * p1:无忧化的动态递归
     * 思路：
     * 动态规划
     * 举个例子：
     * 求dp[4][50]
     * 可以看作将 4 分别插入 1前面，1-2之间,2-3之间，3之后，分别求出对应的排列组合个数
     *
     * @param n
     * @param k
     * @return
     */
    public int kInversePairs3(int n, int k) {
        if (n <= 0) {
            return 0;
        }
        //dp[i][j] 表示i个数随意排列组合，形成j个逆序对的组合个数
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int mod = 1000000007;
                int sum = 0;
                if (i <= j) {
                    for (int m = 0; m < i; m++) {
                        sum = (sum + dp[i - 1][j - m]) % mod;
                    }
                } else {
                    for (int m = 0; m <= j; m++) {
                        sum = (sum + dp[i - 1][m]) % mod;
                    }
                }
                dp[i][j] = sum;
            }
        }
        return dp[n][k];
    }

    public static int kInversePairs(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        int mod = 1000000007;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
                if (j >= i) {
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
                }
            }
        }
        return dp[n][k];
    }

    public static int kInversePairs2(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (j >= i) {
                    dp[i][j] -= dp[i - 1][j - i];
                }
            }
        }
        return dp[n][k];
    }

}

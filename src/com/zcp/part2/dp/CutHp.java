package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/24
 * @description：[[英雄砍死怪兽的概率]] 3个参数  N怪物血量，M最大攻击值，K攻击次数
 * 假设每次攻击该物血量随机减少[0..M]之间的任意值
 * 问砍完K次后 怪物死亡（Hp <= 0）的概率
 * @version:
 */
public class CutHp {

    public static double solution(int N, int M, int K) {
        if (N <= 0) {
            return 1;
        }
        if (K <= 0 || M <= 0) {
            return 0;
        }
        double pow = Math.pow(M + 1, K);
        double process = process(N, M, K);
        return process / pow;
    }

    /**
     * 剩余restK次将hp血条怪物砍死的次数
     *
     * @param hp
     * @param M
     * @param restK
     * @return
     */
    public static double process(int hp, int M, int restK) {
        if (hp <= 0) {
            return Math.pow(M + 1, restK);
        }
        if (restK == 0 && hp > 0) {
            return 0;
        }
        double ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(hp - i, M, restK - 1);
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N <= 0) {
            return 1;
        }
        if (K <= 0 || M <= 0) {
            return 0;
        }
        double pow = Math.pow(M + 1, K);
        double[][] dp = new double[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            dp[i][0] = Math.pow(M + 1, i);
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                double ways = 0;
                for (int cut = 0; cut <= M; cut++) {
                    if (j - cut <= 0) {
                        ways += Math.pow(M + 1, i - 1);
                    } else {
                        ways += dp[i - 1][j - cut];
                    }
                }
                dp[i][j] = ways;
            }
        }
        return dp[K][N] / pow;
    }

    /**
     * 斜率优化
     *
     * @param N
     * @param M
     * @param K
     * @return
     */
    public static double dp2(int N, int M, int K) {
        if (N <= 0) {
            return 1;
        }
        if (K <= 0 || M <= 0) {
            return 0;
        }
        double pow = Math.pow(M + 1, K);
        double[][] dp = new double[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            dp[i][0] = Math.pow(M + 1, i);
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j - M - 1 >= 0) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - M - 1];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - Math.pow(M + 1, i - 1);
                }
            }
        }
        return dp[K][N] / pow;
    }


    public static void main(String[] args) {
        System.out.println(solution(80, 12, 8));
        System.out.println(dp1(80, 12, 8));
        System.out.println(dp2(80, 12, 8));
    }


}

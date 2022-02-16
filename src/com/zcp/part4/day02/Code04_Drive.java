package com.zcp.part4.day02;

import java.util.Arrays;

/**
 * 现有司机N*2人，调度中心会将所有司机平分给A、B两个区域第 i 个司机去A可得收入为income[i][0]，
 * 第 i 个司机去B可得收入为income[i][1]， 返回所有调度方案中能使所有司机总收入最高的方案，是多少钱？
 */
public class Code04_Drive {


    /**
     * 递归改动态规划
     *
     * @param income
     * @return
     */
    public static int maxMoney5(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length;
        int M = N / 2;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = N - 1; i >= 0; i--) {
            dp[i][0] = income[i][1] + dp[i + 1][0];
        }


        for (int i = N - 1; i >= 0; i--) {
            for (int j = 1; j <= M; j++) {
                if (j == N - i) {
                    dp[i][j] = income[i][0] + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(income[i][0] + dp[i + 1][j - 1], income[i][1] + dp[i + 1][j]);
                }
            }
        }
        return dp[0][M];
    }

    /**
     * 思路：
     * 递归
     *
     * @param income
     * @return
     */
    public static int maxMoney4(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        return process4(income, 0, income.length / 2);
    }

    /**
     * index...往后的区域任意选择，去A区域的人员额度还剩下rest个，
     * 返回后续最大的收益
     *
     * @param income
     * @param index
     * @param restA
     * @return
     */
    public static int process4(int[][] income, int index, int restA) {
        if (index == income.length) {
            return 0;
        }
        int N = income.length;
        //还有司机可以选择
        if (restA == N - index) {
            //如果剩下的（N-index）区域数正好=rest，那么剩下司机只能都去 都去A区域
            return income[index][0] + process4(income, index + 1, restA - 1);
        }
        if (restA == 0) {
            //如果剩下的（N-index）区域数正好=index - rest，那么剩下司机只能都去 都去B区域
            return income[index][1] + process4(income, index + 1, restA);
        }
        //否则两种情况选择最大收益
        return Math.max(income[index][0] + process4(income, index + 1, restA - 1), income[index][1] + process4(income, index + 1, restA));

    }


    // 课上的现场版本
    // income -> N * 2 的矩阵 N是偶数！
    // 0 [9, 13]
    // 1 [45,60]
    public static int maxMoney1(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int N = income.length; // 司机数量一定是偶数，所以才能平分，A N /2 B N/2
        int M = N >> 1; // M = N / 2 要去A区域的人
        return process1(income, 0, M);
    }

    // index.....所有的司机，往A和B区域分配！
    // A区域还有rest个名额!
    // 返回把index...司机，分配完，并且最终A和B区域同样多的情况下，index...这些司机，整体收入最大是多少！
    public static int process1(int[][] income, int index, int rest) {
        if (index == income.length) {
            return 0;
        }
        // 还剩下司机！
        if (income.length - index == rest) {
            return income[index][0] + process1(income, index + 1, rest - 1);
        }
        if (rest == 0) {
            return income[index][1] + process1(income, index + 1, rest);
        }
        // 当前司机，可以去A，或者去B
        int p1 = income[index][0] + process1(income, index + 1, rest - 1);
        int p2 = income[index][1] + process1(income, index + 1, rest);
        return Math.max(p1, p2);
    }

    // 严格位置依赖的动态规划版本
    public static int maxMoney2(int[][] income) {
        int N = income.length;
        int M = N >> 1;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= M; j++) {
                if (N - i == j) {
                    dp[i][j] = income[i][0] + dp[i + 1][j - 1];
                } else if (j == 0) {
                    dp[i][j] = income[i][1] + dp[i + 1][j];
                } else {
                    int p1 = income[i][0] + dp[i + 1][j - 1];
                    int p2 = income[i][1] + dp[i + 1][j];
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][M];
    }

    // 这题有贪心策略 :
    // 假设一共有10个司机，思路是先让所有司机去A，得到一个总收益
    // 然后看看哪5个司机改换门庭(去B)，可以获得最大的额外收益
    // 这道题有贪心策略，打了我的脸
    // 但是我课上提到的技巧请大家重视
    // 根据数据量猜解法可以省去大量的多余分析，节省时间
    // 这里感谢卢圣文同学
    public static int maxMoney3(int[][] income) {
        int N = income.length;
        int[] arr = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = income[i][1] - income[i][0];
            sum += income[i][0];
        }
        Arrays.sort(arr);
        int M = N >> 1;
        for (int i = N - 1; i >= M; i--) {
            sum += arr[i];
        }
        return sum;
    }

    // 返回随机len*2大小的正数矩阵
    // 值在0~value-1之间
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney1(matrix);
            int ans2 = maxMoney2(matrix);
            int ans3 = maxMoney3(matrix);
            int ans4 = maxMoney4(matrix);
            int ans5 = maxMoney5(matrix);
            if (ans1 != ans2 || ans1 != ans3 || ans3 != ans4 || ans1 != ans5) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                System.out.println(ans5);
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }

}

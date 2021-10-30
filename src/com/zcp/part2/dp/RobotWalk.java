package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/19
 * @description：机器人行走，4个参数 可移动的总大小L，当前位置POS，目标位置DEST，可移动步数K
 * 要求返回，机器人从原始位置经过K步走到目标位置的所有走法的次数，一次只能移动一个，端点只能向内移动
 * 例子L=6，pos=2，dest=4，k=4
 * 2-3-4-3-4，2-3-4-5-4，2-3-2-3-4,2-1-2-3-4
 * 结果为4
 * @version:
 */
public class RobotWalk {


    /**
     * 方法一，暴力递归
     *
     * @param L
     * @param pos
     * @param dest
     * @param K
     * @return
     */
    public static int solution1(int L, int pos, int dest, int K) {
        if (L < 1 || pos < 1 || pos > L || dest < 1 || dest > L || K < 1) {
            return -1;
        }
        return process(L, pos, dest, K);
    }

    /**
     * 总长度为L 当前位置pos，走K步到dest位置的方案数量
     *
     * @param L
     * @param pos
     * @param dest
     * @param K
     * @return
     */
    public static int process(int L, int pos, int dest, int K) {
        if (K == 0) {
            return pos == dest ? 1 : 0;
        }
        if (pos == 1) {
            return process(L, 2, dest, K - 1);
        }
        if (pos == L) {
            return process(L, L - 1, dest, K - 1);
        }
        return process(L, pos - 1, dest, K - 1) + process(L, pos + 1, dest, K - 1);
    }

    /**
     * 方法二：使用换成输出（动态规划）
     *
     * @param L
     * @param pos
     * @param dest
     * @param K
     * @return
     */
    public static int solution2(int L, int pos, int dest, int K) {
        if (L < 1 || pos < 1 || pos > L || dest < 1 || dest > L || K < 1) {
            return -1;
        }
        int[][] dp = new int[L + 1][K + 1];
        for (int i = 1; i < L + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return process(L, pos, dest, K, dp);
    }

    /**
     * 总长度为L 当前位置pos，走K步到dest位置的方案数量
     *
     * @param L
     * @param pos
     * @param dest
     * @param K
     * @return
     */
    public static int process(int L, int pos, int dest, int K, int[][] dp) {
        if (dp[pos][K] != -1) {
            //先查dp数组中是否有值，如果有值说明之前已经计算过了，直接取值返回
            return dp[pos][K];
        }
        int ans = 0;
        if (K == 0) {
            ans = pos == dest ? 1 : 0;
        } else if (pos == 1) {
            ans = process(L, 2, dest, K - 1);
        } else if (pos == L) {
            ans = process(L, L - 1, dest, K - 1);
        } else {
            ans = process(L, pos - 1, dest, K - 1) + process(L, pos + 1, dest, K - 1);
        }
        dp[pos][K] = ans;
        return ans;
    }

    /**
     * 方法二：动态规划最终版本，通过dp图，找到规律，直接生成dp数值
     *
     * @param L
     * @param pos
     * @param dest
     * @param K
     * @return
     */
    public static int solution3(int L, int pos, int dest, int K) {
        if (L < 1 || pos < 1 || pos > L || dest < 1 || dest > L || K < 1) {
            return -1;
        }
        int[][] dp = new int[L + 1][K + 1];
        for (int i = 1; i < L + 1; i++) {
            dp[i][0] = i == dest ? 1 : 0;
        }
        for (int j = 1; j < K + 1; j++) {
            dp[1][j] = dp[2][j-1];
            for (int i = 2; i < L; i++) {
                dp[i][j] = dp[i+1][j-1] + dp[i-1][j-1];
            }
            dp[L][j] = dp[L-1][j-1];
        }
        return dp[pos][K];
    }


    public static void main(String[] args) {
        System.out.println(solution1(6, 2, 4, 4));
        System.out.println(solution2(6, 2, 4, 4));
        System.out.println(solution3(6, 2, 4, 4));
    }


}

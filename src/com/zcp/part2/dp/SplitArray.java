package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/26
 * @description：正数数组分割为累加和接近的两个集合 返回和较小的数组和
 * @version:
 */
public class SplitArray {


    public static int solution(int[] array) {
        if (array == null || array.length < 1) {
            return -1;
        }
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        return process(array, 0, sum / 2);
    }

    /**
     * index 开始只能选择之后的数字，在总和不超过rest的范围中 返回最接近rest的和
     *
     * @param array
     * @param index
     * @param rest
     * @return
     */
    public static int process(int[] array, int index, int rest) {
        if (index == array.length) {
            return 0;
        }
        if (rest <= 0) {
            return 0;
        }
        int p1 = process(array, index + 1, rest);
        int p2 = 0;
        if (rest - array[index] >= 0) {
            p2 = array[index] + process(array, index + 1, rest - array[index]);
        }
        return Math.max(p1, p2);
    }


    public static int dp(int[] array) {
        if (array == null || array.length < 1) {
            return -1;
        }

        int sum = 0;
        for (int i : array) {
            sum += i;
        }

        int[][] dp = new int[array.length + 1][sum / 2 + 1];
        for (int index = array.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum / 2; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest - array[index] >= 0) {
                    p2 = array[index] + dp[index + 1][rest - array[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum / 2];
    }

    public static void main(String[] args) {
        int[] array = {4, 2};
        System.out.println(solution(array));
        System.out.println(dp(array));
    }

}

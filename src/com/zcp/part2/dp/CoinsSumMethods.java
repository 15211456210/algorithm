package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/23
 * @description：货币面值系列问题 [[货币数组组成面值的方法数-同值认为不同]]
 * 给出一个货币的数组[1,1,2,2,3,5],和目标金额 6 ，返回可以组成目标金额的方法数
 * @version:
 */
public class CoinsSumMethods {


    public static int solution(int[] coins, int aim) {
        if (coins == null || coins.length < 1 || aim < 0) {
            return -1;
        }
        return process(coins, 0, aim);
    }

    /**
     * index位置开始，剩余金额rest金额花完的方法数
     *
     * @param coins
     * @param index
     * @param rest
     * @return
     */
    public static int process(int[] coins, int index, int rest) {
        if (index == coins.length && rest == 0) {
            return 1;
        }
        if (rest < 0 || index == coins.length) {
            return 0;
        }
        //p1 选择当前货币
        //p2 不选择当前货币
        //方法数=p1+p2
        return process(coins, index + 1, rest - coins[index]) + process(coins, index + 1, rest);
    }

    /**
     * 动态规划
     *
     * @param coins
     * @param aim
     * @return
     */
    public static int dp(int[] coins, int aim) {
        if (coins == null || coins.length < 1 || aim < 0) {
            return -1;
        }
        int coinCount = coins.length;
        int[][] dp = new int[coinCount + 1][aim + 1];
        dp[coinCount][0] = 1;
        for (int i = coinCount - 1; i >= 0; i--) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = dp[i + 1][j] + ((j - coins[i]) < 0 ? 0 : dp[i + 1][j - coins[i]]);
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] coins = {1, 1, 2, 2, 3, 5};
        int aim = 6;
        System.out.println(solution(coins, aim));
        System.out.println(dp(coins, aim));
    }


}

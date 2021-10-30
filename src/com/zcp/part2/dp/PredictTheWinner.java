package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/19
 * @description：预测赢家 https://leetcode-cn.com/problems/predict-the-winner/
 * @version:
 */
public class PredictTheWinner {
    /**
     * 方案3：动态规划最终版
     * 根据两个dp数组的依赖关系，直接生成数组值
     *
     * @param nums
     * @return
     */
    public static boolean solution3(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }
        int N = nums.length;
        int[][] pre = new int[N][N];
        int[][] post = new int[N][N];
        for (int i = 0; i < N; i++) {
            pre[i][i] = nums[i];
            post[i][i] = 0;
        }
        for (int j = 1; j < N; j++) {
            int row = 0;
            int col = j;
            for (; row < N - 1 && col < N; ) {
                pre[row][col] = Math.max(nums[col] + post[row][col - 1], nums[row] + post[row + 1][col]);
                post[row][col] = Math.min(pre[row][col - 1], pre[row + 1][col]);
                row++;
                col++;
            }
        }
        //先手最后得分是否大于后手
        return pre[0][N - 1] >= post[0][N - 1];
    }


    /**
     * 方案2：动态规划
     * 将先手答案和后手答案分配存储在两个二维数组中
     *
     * @param nums
     * @return
     */
    public static boolean solution2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }
        int[][] pre = new int[nums.length][nums.length];
        int[][] post = new int[nums.length][nums.length];
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < pre.length; j++) {
                pre[i][j] = -1;
            }
        }
        for (int i = 0; i < post.length; i++) {
            for (int j = 0; j < post.length; j++) {
                post[i][j] = -1;
            }
        }
        //先手最后得分是否大于后手
        return pre(nums, 0, nums.length - 1, pre, post) >= post(nums, 0, nums.length - 1, pre, post);
    }

    /**
     * 先手 在nums[L...R]中的最大收益值
     *
     * @param nums
     * @param L
     * @param R
     * @return
     */
    public static int pre(int[] nums, int L, int R, int[][] pre, int[][] post) {
        if (pre[L][R] != -1) {
            return pre[L][R];
        }
        int ans = 0;
        if (L == R) {
            //只剩下一张牌
            ans = nums[L];
        } else {
            ans = Math.max(nums[L] + post(nums, L + 1, R, pre, post), nums[R] + post(nums, L, R - 1, pre, post));
        }
        pre[L][R] = ans;
        return ans;
    }

    /**
     * 后手 在nums[L...R]中的最大收益值
     *
     * @param nums
     * @param L
     * @param R
     * @return
     */
    public static int post(int[] nums, int L, int R, int[][] pre, int[][] post) {
        if (post[L][R] != -1) {
            return post[L][R];
        }
        int ans = 0;
        if (L == R) {
            //只剩下一张牌
            ans = 0;
        } else {
            //因为两个玩家都是决定聪明，所以后手总是会得到交差的哪一个结果
            ans = Math.min(pre(nums, L + 1, R, pre, post), pre(nums, L, R - 1, pre, post));
        }
        post[L][R] = ans;
        return ans;
    }


    /**
     * 方案1：暴力递归
     *
     * @param nums
     * @return
     */
    public static boolean solution1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }
        //先手最后得分是否大于后手
        return pre(nums, 0, nums.length - 1) >= post(nums, 0, nums.length - 1);
    }

    /**
     * 先手 在nums[L...R]中的最大收益值
     *
     * @param nums
     * @param L
     * @param R
     * @return
     */
    public static int pre(int[] nums, int L, int R) {
        if (L == R) {
            //只剩下一张牌
            return nums[L];
        }
        return Math.max(nums[L] + post(nums, L + 1, R), nums[R] + post(nums, L, R - 1));
    }

    /**
     * 后手 在nums[L...R]中的最大收益值
     *
     * @param nums
     * @param L
     * @param R
     * @return
     */
    public static int post(int[] nums, int L, int R) {
        if (L == R) {
            //只剩下一张牌
            return 0;
        }
        //因为两个玩家都是决定聪明，所以后手总是会得到交差的哪一个结果
        return Math.min(pre(nums, L + 1, R), pre(nums, L, R - 1));
    }


    public static void main(String[] args) {
        System.out.println(solution1(new int[]{1, 5, 3}));
        System.out.println(solution2(new int[]{1, 5, 3}));
        System.out.println(solution3(new int[]{1, 5, 3}));
    }
}

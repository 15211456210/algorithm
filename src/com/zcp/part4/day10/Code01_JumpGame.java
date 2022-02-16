package com.zcp.part4.day10;

// 本题测试链接 : https://leetcode.com/problems/jump-game-ii/
public class Code01_JumpGame {

    /**
     * 优化
     * 思路：跳N次能到的最远距离，依次迭代下去
     * @param nums
     * @return
     */
    public int jump3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int jumpCount = 0;
        int nextEnd = 0;
        int preEnd = -1;//记录上一次的最远位置，作为这一次遍历的开始位置
        while ((nextEnd + 1) < len) {
            int curEnd = nextEnd;
            for (int i = preEnd + 1; i <= curEnd; i++) {
                nextEnd = Math.max(nextEnd, nums[i] + i);
            }
            preEnd = curEnd;
            jumpCount++;
        }
        return jumpCount;
    }

    public static void main(String[] args) {
        System.out.println(new Code01_JumpGame().jump3(new int[]{2, 3, 1, 1, 4}));
    }

    /**
     * 思路：
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] dp = new int[N];

        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] + i > N - 2) {
                dp[i] = 1;
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + nums[i]; j++) {
                min = Math.min(min, dp[j]);
            }
            dp[i] = (min == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (min + 1);
        }
        return dp[0];
    }


    public static int jump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int step = 0;
        int cur = 0;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }

}

package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c045
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/jump-game-ii/
 * @date 2022/8/28 15:48
 */
public class c045 {

    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int jumpCount = 0;
        int nextEnd = 0;
        int preEnd = -1;
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
}

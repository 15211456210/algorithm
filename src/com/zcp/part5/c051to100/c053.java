package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c053
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/maximum-subarray/submissions/
 * @date 2022/8/28 16:32
 */
public class c053 {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int N = nums.length;
        int pre = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            ans = Math.max(ans, pre);
        }
        return ans;
    }
}

package com.zcp.part5.c151to200;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C164
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/maximum-gap/submissions/
 * @date 2022/9/8 16:27
 */
public class C164 {

    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        return max;

    }
}

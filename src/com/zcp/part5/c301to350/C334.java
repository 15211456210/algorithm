package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C334
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/increasing-triplet-subsequence/description/
 * @date 2022/10/18 11:26
 */
public class C334 {

    public boolean increasingTriplet(int[] nums) {
        int len = nums.length;
        int min = nums[0];
        boolean[] help = new boolean[len];
        for (int i = 1; i < len; i++) {
            if (nums[i] > min) {
                help[i] = true;
            } else {
                min = nums[i];
            }
        }
        int max = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < max && help[i]) {
                return true;
            } else if (nums[i] > max) {
                max = nums[i];
            }
        }
        return false;
    }
}

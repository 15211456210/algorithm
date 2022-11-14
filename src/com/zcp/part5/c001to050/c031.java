package com.zcp.part5.c001to050;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: c031
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/next-permutation/submissions/
 * @date 2022/8/26 16:28
 */
public class c031 {

    public void nextPermutation(int[] nums) {

        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                int minIdx = i;
                for (int k = i; k < nums.length; k++) {
                    if (nums[k] > nums[i - 1] && nums[k] < nums[minIdx]) {
                        minIdx = k;
                    }
                }
                swap(nums, minIdx, i - 1);
                Arrays.sort(nums, i, nums.length);
                return;
            }
        }
        reserve(nums);
    }

    /**
     * 逆序
     */
    public void reserve(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            swap(nums, l++, r--);
        }
    }


    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

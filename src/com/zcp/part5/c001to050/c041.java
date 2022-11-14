package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c041
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/first-missing-positive/
 * @date 2022/8/27 15:03
 */
public class c041 {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 1;
        }
        int len = nums.length;
        int L = 0;
        int R = len;

        while (L < R) {
            if (nums[L] == L + 1) {
                L++;
            } else if (nums[L] <= 0 || nums[L] > R || nums[L] == nums[nums[L] - 1]) {
                swap(nums, L, --R);
            } else {
                swap(nums, L, nums[L] - 1);
            }
        }
        return L + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

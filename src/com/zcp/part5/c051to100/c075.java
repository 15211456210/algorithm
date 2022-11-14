package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c075
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/sort-colors/submissions/
 * @date 2022/8/30 10:01
 */
public class c075 {

    public void sortColors(int[] nums) {
        int left = -1;
        int right = nums.length;
        int index = 0;
        while (index < right) {
            if (nums[index] == 0) {
                swap(nums, ++left, index++);
            } else if (nums[index] == 1) {
                index++;
            } else {
                swap(nums, --right, index);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

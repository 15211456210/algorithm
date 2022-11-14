package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c035
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/search-insert-position/
 * @date 2022/8/26 16:30
 */
public class c035 {

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length - 1;
        int mid = 0;
        while (l <= r) {
            mid = l + ((r - l) / 2);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            }
            if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        return l;

    }
}

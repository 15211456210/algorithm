package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c033
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/search-in-rotated-sorted-array/submissions/
 * @date 2022/8/26 16:29
 */
public class c033 {

    public int search(int[] nums, int target) {
        if (nums.length < 5) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    return i;
                }
            }

        } else {
            int left = 0;
            int right = nums.length - 1;

            while (left <= right) {
                if (left == right) {
                    return nums[left] == target ? left : -1;
                }
                int mid = left + ((right - left) / 2);
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[left] <= nums[mid]) {
                    if (target >= nums[left] && target <= nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else if (nums[mid] <= nums[right]) {
                    if (target >= nums[mid] && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }
        return -1;
    }
}

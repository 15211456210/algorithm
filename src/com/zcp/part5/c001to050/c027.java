package com.zcp.part5.c001to050;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: c027
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/remove-element/submissions/
 * @date 2022/8/25 15:43
 */
public class c027 {

    public int removeElement(int[] nums, int val) {
        if (nums.length == 1) {
            return nums[0] == val ? 0 : 1;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                int r = i + 1;
                while (r < nums.length && nums[r] == val) {
                    ++r;
                }
                while (r < nums.length) {
                    nums[i++] = nums[r++];
                }
                return i;
            }
        }
        return nums.length;
    }
}

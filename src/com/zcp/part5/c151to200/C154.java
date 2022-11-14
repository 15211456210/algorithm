package com.zcp.part5.c151to200;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C154
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/
 * @date 2022/9/8 15:19
 */
public class C154 {

    public int findMin(int[] nums) {
        Arrays.sort(nums);
        return nums[0];
    }
}

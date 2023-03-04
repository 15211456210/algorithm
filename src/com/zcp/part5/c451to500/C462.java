package com.zcp.part5.c451to500;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C462
 * @projectName algorithm
 * @description: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
 * @date 2023/2/8 10:47
 */
public class C462 {

    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int sum = 0, len = nums.length;
        int mid = nums[len / 2];
        for (int i = 0; i < len; i++) {
            sum += Math.abs(nums[i] - mid);
        }
        return sum;
    }
}

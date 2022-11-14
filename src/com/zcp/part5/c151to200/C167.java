package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C167
 * @projectName algorithm
 * @description: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * @date 2022/9/9 9:16
 */
public class C167 {


    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            }
            if (numbers[l] + numbers[r] < target) {
                ++l;
            }
            if (numbers[l] + numbers[r] > target) {
                --r;
            }
        }
        return new int[2];
    }
}

package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C189
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/rotate-array/
 * @date 2022/9/13 16:25
 */
public class C189 {

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] newArr = new int[nums.length];
        int index = 0;
        for (int i = nums.length - k; i < nums.length; i++) {
            newArr[index++] = nums[i];
        }
        for (int i = 0; i < nums.length - k; i++) {
            newArr[index++] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, nums.length);
    }
}

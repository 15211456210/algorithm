package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C396
 * @projectName algorithm
 * @description: https://leetcode.com/problems/rotate-function/
 * @date 2022/11/12 10:54
 */
public class C396 {

    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int psum = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i * nums[i];
            psum += nums[i];
        }
        max = Math.max(max, sum);
        for (int i = n - 1; i > 0; i--) {
            sum = sum + psum - n * nums[i];
            max = Math.max(max, sum);
        }
        return max;
    }
}

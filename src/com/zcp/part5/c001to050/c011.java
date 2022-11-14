package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c011
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/container-with-most-water/
 * @date 2022/8/25 13:31
 */
public class c011 {

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int N = height.length;
        int left = 0;
        int right = N - 1;
        int max = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                max = Math.max(max, (right - left) * height[left++]);
            } else {
                max = Math.max(max, (right - left) * height[right--]);
            }
        }
        return max;
    }
}

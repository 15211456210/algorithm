package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c042
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/trapping-rain-water/
 * @date 2022/8/27 15:04
 */
public class c042 {

    public int trap(int[] height) {
        int maxLeft = height[0];
        int maxRight = height[height.length - 1];
        int lIndex = 1;
        int rIndex = height.length - 2;
        int size = 0;
        while (lIndex <= rIndex) {
            if (maxLeft <= maxRight) {
                size += Math.max(0, maxLeft - height[lIndex]);
                maxLeft = Math.max(maxLeft, height[lIndex++]);
            } else {
                size += Math.max(0, maxRight - height[rIndex]);
                maxRight = Math.max(maxRight, height[rIndex--]);
            }
        }
        return size;
    }
}

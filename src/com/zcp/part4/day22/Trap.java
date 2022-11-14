package com.zcp.part4.day22;

/**
 * @author ZCP
 * @title: Trap
 * @projectName algorithm
 * @description: TODO
 * @date 2022/3/14 18:38
 */
public class Trap {

    /**
     * 思路：
     * 双指针
     *
     * @param height
     * @return
     */
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

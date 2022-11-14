package com.zcp.part5.c051to100;

import java.util.Stack;

/**
 * @author ZCP
 * @title: c084
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/largest-rectangle-in-histogram/submissions/
 * @date 2022/8/30 18:15
 */
public class c084 {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length < 1) {
            return -1;
        }

        int ans = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                Integer pop = stack.pop();
                if (heights[pop] > heights[i]) {
                    //计算
                    //高是heights[pop],宽度是right-left -1
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    int right = i;
                    ans = Math.max(ans, heights[pop] * (right - left - 1));
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int right = i;
            ans = Math.max(ans, heights[pop] * (right - left - 1));
        }
        return ans;
    }
}

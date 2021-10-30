package com.zcp.part2.singlestack;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Stack;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/30
 * @description：[[柱状图中最大的矩形]] https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * https://leetcode-cn.com/problems/0ynMMM/
 * @version:
 */
public class LargestRectangleInHistogram {


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
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int right = i;
            ans = Math.max(ans, heights[pop] * (right - left - 1));
        }
        return ans;
    }
}

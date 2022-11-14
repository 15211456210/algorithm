package com.zcp.part5.c051to100;

import java.util.Stack;

/**
 * @author ZCP
 * @title: c085
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/maximal-rectangle/submissions/
 * @date 2022/8/30 18:16
 */
public class c085 {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        int[] histogramArray = new int[matrix[0].length];//直方图数组

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                histogramArray[j] = matrix[i][j] == '0' ? 0 : histogramArray[j] + 1;
            }
            //求每一层直方图的最大矩形面积
            Stack<Integer> stack = new Stack<>();
            int k = 0;
            for (; k < histogramArray.length; k++) {
                while (!stack.isEmpty() && histogramArray[stack.peek()] >= histogramArray[k]) {
                    Integer pop = stack.pop();
                    if (histogramArray[pop] > histogramArray[k]) {
                        //计算
                        //高是heights[pop],宽度是right-left -1
                        int left = stack.isEmpty() ? -1 : stack.peek();
                        int right = k;
                        ans = Math.max(ans, histogramArray[pop] * (right - left - 1));
                    }
                }
                stack.push(k);
            }
            while (!stack.isEmpty()) {
                Integer pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = k;
                ans = Math.max(ans, histogramArray[pop] * (right - left - 1));
            }

        }
        return ans;
    }
}

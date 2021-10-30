package com.zcp.part2.singlestack;

import java.util.Stack;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/30
 * @description：[[全是1的最大子矩形面积]] https://leetcode-cn.com/problems/maximal-rectangle/
 * @version:
 */
public class MaximalRectangle {

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

    public static void main(String[] args) {
//        [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
//        MaximalRectangle maximalRectangle = new MaximalRectangle();
//        maximalRectangle.maximalRectangle(new int[][]{
//                {},
//                {},
//                {},
//                {},
//        })
    }
}

package com.zcp.part2.singlestack;

import java.util.Stack;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/30
 * @description：[[全是1的子矩阵数量]] https://leetcode-cn.com/problems/count-submatrices-with-all-ones/
 * @version:
 */
public class CountSubmatricesWithAllOnes {

    public int numSubmat(int[][] mat) {
        if (mat == null || mat.length < 1) {
            return 0;
        }
        int ans = 0;
        int[] histogramArray = new int[mat[0].length];//直方图数组

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                histogramArray[j] = mat[i][j] == 0 ? 0 : histogramArray[j] + 1;
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
                        //取左右两边高度较高的
                        int bigH = left == -1 ? histogramArray[k] : Math.max(histogramArray[k], histogramArray[left]);
                        //计算以该层为底，高度在bigH - histogramArray[pop],宽度在1-right - left - 1上的矩形数量
                        int width = right - left - 1;
                        ans += (histogramArray[pop] - bigH) * ((width + 1) * width / 2);
                    }
                }
                stack.push(k);
            }
            while (!stack.isEmpty()) {
                Integer pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = k;
                int bigH = left == -1 ? 0 : histogramArray[left];
                int width = right - left - 1;
                ans += (histogramArray[pop] - bigH) * ((width + 1) * width / 2);
            }
        }
        return ans;
    }

}

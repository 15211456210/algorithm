package com.zcp.part4.day04;

import com.zcp.part4.day03.Code03_Largest1BorderedSquare;
import org.omg.CORBA.MARSHAL;

/**
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 * <p>
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 * <p>
 * 注意：本题相对书上原题稍作改动
 */
public class Code03_SubMatrixMaxSum {


    /**
     * 思路：
     * 动态规划，技巧
     *
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix4(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return new int[4];
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int max = Integer.MIN_VALUE;
        int[] ans = new int[4];//记录最大结果的坐标位置
        for (int i = 0; i < N; i++) {
            int[] temp = new int[M];//每一种情况的预处理结构
            for (int j = i; j < N; j++) {
                int pre = 0;//当前循环的结果最佳结果
                int left = 0;//记录左边界
                for (int x = 0; x < M; x++) {
                    temp[x] += matrix[j][x];
                    if (pre < 0) {
                        left = x;
                        pre = temp[x];
                    }else {
                        pre = temp[x] + pre;
                    }
                    if (max < pre) {
                        max = pre;
                        ans[0] = i;
                        ans[1] = left;
                        ans[2] = j;
                        ans[3] = x;
                    }
                }
            }
        }
        return ans;
    }


    /**
     * 思路：
     * 预处理
     *
     * @param matrix
     * @return
     */
    public int[] getMaxMatrix2(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return new int[4];
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] sum = new int[N][M];
        sum[0][0] = matrix[0][0];//生成累加和数组
        for (int y = 1; y < M; y++) {
            sum[0][y] = matrix[0][y] + sum[0][y - 1];
        }
        for (int x = 1; x < N; x++) {
            sum[x][0] = matrix[x][0] + sum[x - 1][0];
        }
        for (int x = 1; x < N; x++) {
            for (int y = 1; y < M; y++) {
                sum[x][y] = matrix[x][y] + sum[x - 1][y] + sum[x][y - 1] - sum[x - 1][y - 1];
            }
        }
        int[] ans = new int[4];
        //计算每种情况（x1，y1） （x2，y2）的累加和
        int max = Integer.MIN_VALUE;
        for (int x1 = 0; x1 < N; x1++) {
            for (int y1 = 0; y1 < M; y1++) {

                for (int x2 = x1; x2 < N; x2++) {
                    for (int y2 = y1; y2 < M; y2++) {
                        int cSum = sum3(sum, x1, y1, x2, y2);
                        if (max < cSum) {
                            max = cSum;
                            ans[0] = x1;
                            ans[1] = y1;
                            ans[2] = x2;
                            ans[3] = y2;
                        }
                    }
                }
            }
        }
        return ans;
    }

    private int sum3(int[][] sum, int x1, int y1, int x2, int y2) {
        if (x1 == 0 && y1 == 0) {
            return sum[x2][y2];
        } else if (x1 == 0) {
            return sum[x2][y2] - sum[x2][y1 - 1];
        } else if (y1 == 0) {
            return sum[x2][y2] - sum[x1 - 1][y2];
        } else {
            return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
        }

    }

    public static void main(String[] args) {
        new Code03_SubMatrixMaxSum().getMaxMatrix4(new int[][]{{-1, -2, -9, 6}, {8, -9, -3, -6}, {2, 9, -7, -6}});
    }

    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        // O(N^2 * M)
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            // i~j
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    s[k] += m[j][k];
                }
                max = Math.max(max, maxSubArray(s));
            }
        }
        return max;
    }

    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    // 本题测试链接 : https://leetcode-cn.com/problems/max-submatrix-lcci/
    public static int[] getMaxMatrix(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        for (int i = 0; i < N; i++) {
            int[] s = new int[M];
            for (int j = i; j < N; j++) {
                cur = 0;
                int begin = 0;
                for (int k = 0; k < M; k++) {
                    s[k] += m[j][k];
                    cur += s[k];
                    if (max < cur) {
                        max = cur;
                        a = i;
                        b = begin;
                        c = j;
                        d = k;
                    }
                    if (cur < 0) {
                        cur = 0;
                        begin = k + 1;
                    }
                }
            }
        }
        return new int[]{a, b, c, d};
    }

}

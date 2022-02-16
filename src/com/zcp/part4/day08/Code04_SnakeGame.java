package com.zcp.part4.day08;

import java.util.Arrays;

/**
 * 给定一个矩阵matrix，值有正、负、0 蛇可以空降到最左列的任何一个位置，初始增长值是0 蛇每一步可以选择右上、右、右下三个方向的任何一个前进 沿途的数字累加起来，作为增长值；
 * 但是蛇一旦增长值为负数，就会死去
 * 蛇有一种能力，可以使用一次：
 * 把某个格子里的数变成相反数 蛇可以走到任何格子的时候停止
 * 返回蛇能获得的最大增长值
 */
public class Code04_SnakeGame {

    /**
     * 思路：
     * 动态规划
     * 常数时间的优化
     *
     * @param matrix
     * @return
     */
    public static int walk4(int[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return 0;
        }
        int max = 0;//结果值
        int N = matrix.length;
        int M = matrix[0].length;

        //优化处理 上下分别都加上一行-1值，减少循环里面边界判断
        //处理后 第一行 和 最后一行默认都是-1 代表到不了的位置
        //dp[i][j][0] 贪吃蛇到位置matrix[i-1][j]位置的且不使用技能的最有结果，如果不能到达该位置，结果为-1
        //dp[i][j][1] 贪吃蛇到位置matrix[i-1][j]位置的且使用技能的最有结果，如果不能到达该位置，结果为-1
        int[][][] dp = new int[N + 2][M][2];
        //处理第一行（最后一行）数据
        for (int j = 0; j < M; j++) {
            dp[0][j][0] = -1;
            dp[0][j][1] = -1;
            dp[N + 1][j][0] = -1;
            dp[N + 1][j][1] = -1;
        }
        //处理第一列数据（不包括首位行）
        for (int i = 1; i < N + 1; i++) {
            dp[i][0][0] = Math.max(-1, matrix[i - 1][0]);
            dp[i][0][1] = Math.max(-1, -matrix[i - 1][0]);
            max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
        }
        for (int j = 1; j < M; j++) {
            for (int i = 1; i < N + 1; i++) {

                int curNum = matrix[i - 1][j];//因为第一行加了一行所以i-1取相对位置，不然下标会越界
                int curNoUse = -1;
                int curUse = -1;
                //一共3种情况

                //1.左侧过来的情况
                //不使用技能
                int preNoUse = dp[i][j - 1][0];
                int preUse = dp[i][j - 1][1];
                curNoUse = preNoUse == -1 ? -1 : preNoUse + curNum;
                //使用技能，要分情况讨论
                curUse = Math.max(
                        preUse == -1 ? -1 : preUse + curNum,
                        preNoUse == -1 ? -1 : preNoUse - curNum
                );

                //2.左下过来的情况
                preNoUse = dp[i + 1][j - 1][0];
                preUse = dp[i + 1][j - 1][1];
                //不使用技能
                curNoUse = Math.max(curNoUse, preNoUse == -1 ? -1 : preNoUse + curNum);
                //使用技能
                curUse = Math.max(curUse, Math.max(
                        preUse == -1 ? -1 : preUse + curNum,
                        preNoUse == -1 ? -1 : preNoUse - curNum
                ));

                //3.左上过来的情况
                preNoUse = dp[i - 1][j - 1][0];
                preUse = dp[i - 1][j - 1][1];
                curNoUse = Math.max(curNoUse, preNoUse == -1 ? -1 : preNoUse + curNum);
                curUse = Math.max(curUse, Math.max(
                        preUse == -1 ? -1 : preUse + curNum,
                        preNoUse == -1 ? -1 : preNoUse - curNum
                ));
                //设置dp的值
                dp[i][j][0] = curNoUse;
                dp[i][j][1] = curUse;
                max = Math.max(max, Math.max(curNoUse, curUse));
            }
        }
        return max;

    }


    /**
     * 思路：
     * 递归
     *
     * @param matrix
     * @return
     */
    public static int walk3(int[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return 0;
        }
        int max = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int[] ans = process2(matrix, i, j);
                max = Math.max(max, Math.max(ans[0], ans[1]));
            }
        }
        return max;
    }

    /**
     * 从开始选择最优路径 -> matrix[i][j]结束 的最优解
     * dp[i][j][0] 不使用技能
     * dp[i][j][1] 使用技能
     *
     * @param matrix
     * @param i
     * @param j
     * @return ans[2]
     * ans[0]:表示不使用技能到达（i，j）位置的最长长度，如果到不了-1表示
     * ans[1]:表示必须使用技能到达（i，j）位置的最长长度，如果到不了-1表示
     */
    public static int[] process2(int[][] matrix, int i, int j) {
        if (j == 0) {
            //第一列
            return new int[]{Math.max(matrix[i][j], -1), Math.max(-matrix[i][j], -1)};
        }
        int[] ans = new int[2];
        //j>0
        //p1 从左侧过来，且不使用技能的最好值
        //首先要判断能否到前面格子
        ans[0] = process2(matrix, i, j - 1)[0] == -1 ? -1 : (process2(matrix, i, j - 1)[0] + matrix[i][j]);

        //p2 使用技能
        //情况1：之前位置使用过技能，当前位置不能使用技能
        //情况2：之前位置未使用技能，则当前位置必须使用技能
        ans[1] = Math.max(-1, Math.max(
                process2(matrix, i, j - 1)[1] == -1 ? -1 : (process2(matrix, i, j - 1)[1] + matrix[i][j]),
                process2(matrix, i, j - 1)[0] == -1 ? -1 : (process2(matrix, i, j - 1)[0] - matrix[i][j])));
        //如果只有一行，计算到这一步就可以返回了
        if (matrix.length == 1) {
            return ans;
        }
        if (i == 0) {
            //左下方过来的情况
            ans[0] = Math.max(ans[0], process2(matrix, i + 1, j - 1)[0] == -1 ? -1 : (process2(matrix, i + 1, j - 1)[0] + matrix[i][j]));

            //p2 使用技能
            //情况1：之前位置使用过技能，当前位置不能使用技能
            //情况2：之前位置未使用技能，则当前位置必须使用技能
            ans[1] = Math.max(ans[1], Math.max(
                    process2(matrix, i + 1, j - 1)[1] == -1 ? -1 : (process2(matrix, i + 1, j - 1)[1] + matrix[i][j]),
                    process2(matrix, i + 1, j - 1)[0] == -1 ? -1 : (process2(matrix, i + 1, j - 1)[0] - matrix[i][j])));

        } else if (i == matrix.length - 1) {
            //左上方过来的情况
            ans[0] = Math.max(ans[0], process2(matrix, i - 1, j - 1)[0] == -1 ? -1 : (process2(matrix, i - 1, j - 1)[0] + matrix[i][j]));
            //p2 使用技能
            //情况1：之前位置使用过技能，当前位置不能使用技能
            //情况2：之前位置未使用技能，则当前位置必须使用技能
            ans[1] = Math.max(ans[1], Math.max(
                    process2(matrix, i - 1, j - 1)[1] == -1 ? -1 : (process2(matrix, i - 1, j - 1)[1] + matrix[i][j]),
                    process2(matrix, i - 1, j - 1)[0] == -1 ? -1 : (process2(matrix, i - 1, j - 1)[0] - matrix[i][j])));
        } else {
            //左上、左下都有
            ans[0] = Math.max(ans[0], process2(matrix, i - 1, j - 1)[0] == -1 ? -1 : (process2(matrix, i - 1, j - 1)[0] + matrix[i][j]));
            ans[0] = Math.max(ans[0], process2(matrix, i + 1, j - 1)[0] == -1 ? -1 : (process2(matrix, i + 1, j - 1)[0] + matrix[i][j]));
            //p2 使用技能
            //情况1：之前位置使用过技能，当前位置不能使用技能
            //情况2：之前位置未使用技能，则当前位置必须使用技能
            ans[1] = Math.max(ans[1], Math.max(
                    process2(matrix, i - 1, j - 1)[1] == -1 ? -1 : (process2(matrix, i - 1, j - 1)[1] + matrix[i][j]),
                    process2(matrix, i - 1, j - 1)[0] == -1 ? -1 : (process2(matrix, i - 1, j - 1)[0] - matrix[i][j])));
            ans[1] = Math.max(ans[1], Math.max(
                    process2(matrix, i + 1, j - 1)[1] == -1 ? -1 : (process2(matrix, i + 1, j - 1)[1] + matrix[i][j]),
                    process2(matrix, i + 1, j - 1)[0] == -1 ? -1 : (process2(matrix, i + 1, j - 1)[0] - matrix[i][j])));
        }
        return ans;
    }

    public static int walk1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int[] ans = process(matrix, i, j);
                res = Math.max(res, Math.max(ans[0], ans[1]));
            }
        }
        return res;
    }

    public static int zuo(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Info cur = f(matrix, i, j);
                ans = Math.max(ans, Math.max(cur.no, cur.yes));
            }
        }
        return ans;
    }

    public static class Info {
        public int no;
        public int yes;

        public Info(int n, int y) {
            no = n;
            yes = y;
        }
    }

    // 蛇从某一个最左列，且最优的空降点降落
    // 沿途走到(i,j)必须停！
    // 返回，一次能力也不用，获得的最大成长值
    // 返回，用了一次能力，获得的最大成长值
    // 如果蛇从某一个最左列，且最优的空降点降落，不用能力，怎么都到不了(i,j)，那么no = -1
    // 如果蛇从某一个最左列，且最优的空降点降落，用了一次能力，怎么都到不了(i,j)，那么yes = -1
    public static Info f(int[][] matrix, int i, int j) {
        if (j == 0) { // 最左列
            int no = Math.max(matrix[i][0], -1);
            int yes = Math.max(-matrix[i][0], -1);
            return new Info(no, yes);
        }
        // j > 0 不在最左列
        int preNo = -1;
        int preYes = -1;
        Info pre = f(matrix, i, j - 1);
        preNo = Math.max(pre.no, preNo);
        preYes = Math.max(pre.yes, preYes);
        if (i > 0) {
            pre = f(matrix, i - 1, j - 1);
            preNo = Math.max(pre.no, preNo);
            preYes = Math.max(pre.yes, preYes);
        }
        if (i < matrix.length - 1) {
            pre = f(matrix, i + 1, j - 1);
            preNo = Math.max(pre.no, preNo);
            preYes = Math.max(pre.yes, preYes);
        }
        int no = preNo == -1 ? -1 : (Math.max(-1, preNo + matrix[i][j]));
        // 能力只有一次，是之前用的！
        int p1 = preYes == -1 ? -1 : (Math.max(-1, preYes + matrix[i][j]));
        // 能力只有一次，就当前用！
        int p2 = preNo == -1 ? -1 : (Math.max(-1, preNo - matrix[i][j]));
        int yes = Math.max(Math.max(p1, p2), -1);
        return new Info(no, yes);
    }

    // 从假想的最优左侧到达(i,j)的旅程中
    // 0) 在没有使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
    // 1) 在使用过能力的情况下，返回路径最大和，没有可能到达的话，返回负
    public static int[] process(int[][] m, int i, int j) {
        if (j == 0) { // (i,j)就是最左侧的位置
            return new int[]{m[i][j], -m[i][j]};
        }
        int[] preAns = process(m, i, j - 1);
        // 所有的路中，完全不使用能力的情况下，能够到达的最好长度是多大
        int preUnuse = preAns[0];
        // 所有的路中，使用过一次能力的情况下，能够到达的最好长度是多大
        int preUse = preAns[1];
        if (i - 1 >= 0) {
            preAns = process(m, i - 1, j - 1);
            preUnuse = Math.max(preUnuse, preAns[0]);
            preUse = Math.max(preUse, preAns[1]);
        }
        if (i + 1 < m.length) {
            preAns = process(m, i + 1, j - 1);
            preUnuse = Math.max(preUnuse, preAns[0]);
            preUse = Math.max(preUse, preAns[1]);
        }
        // preUnuse 之前旅程，没用过能力
        // preUse 之前旅程，已经使用过能力了
        int no = -1; // 之前没使用过能力，当前位置也不使用能力，的最优解
        int yes = -1; // 不管是之前使用能力，还是当前使用了能力，请保证能力只使用一次，最优解
        if (preUnuse >= 0) {
            no = m[i][j] + preUnuse;
            yes = -m[i][j] + preUnuse;
        }
        if (preUse >= 0) {
            yes = Math.max(yes, m[i][j] + preUse);
        }
        return new int[]{no, yes};
    }

    public static int walk2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int[][][] dp = new int[matrix.length][matrix[0].length][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0][0] = matrix[i][0];
            dp[i][0][1] = -matrix[i][0];
            max = Math.max(max, Math.max(dp[i][0][0], dp[i][0][1]));
        }
        for (int j = 1; j < matrix[0].length; j++) {
            for (int i = 0; i < matrix.length; i++) {
                int preUnuse = dp[i][j - 1][0];
                int preUse = dp[i][j - 1][1];
                if (i - 1 >= 0) {
                    preUnuse = Math.max(preUnuse, dp[i - 1][j - 1][0]);
                    preUse = Math.max(preUse, dp[i - 1][j - 1][1]);
                }
                if (i + 1 < matrix.length) {
                    preUnuse = Math.max(preUnuse, dp[i + 1][j - 1][0]);
                    preUse = Math.max(preUse, dp[i + 1][j - 1][1]);
                }
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
                if (preUnuse >= 0) {
                    dp[i][j][0] = matrix[i][j] + preUnuse;
                    dp[i][j][1] = -matrix[i][j] + preUnuse;
                }
                if (preUse >= 0) {
                    dp[i][j][1] = Math.max(dp[i][j][1], matrix[i][j] + preUse);
                }
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }
        return max;
    }

    public static int[][] generateRandomArray(int row, int col, int value) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 30000;
        int M = 30000;
        int V = 10;
        int times = 1;
        long time1 = 0l;
        long time2 = 0l;

        for (int i = 0; i < times; i++) {
            int r = (int) (Math.random() * N) + 1;
            int c = (int) (Math.random() * M) + 1;
            int[][] matrix = generateRandomArray(r, c, V);
//            int ans1 = zuo(matrix);
            long begin = System.currentTimeMillis();
            int ans2 = walk2(matrix);
            long end = System.currentTimeMillis();
            time1 += (end - begin);

//            int ans3 = walk3(matrix);
            begin = System.currentTimeMillis();
            int ans4 = walk4(matrix);
            end = System.currentTimeMillis();
            time2 += (end - begin);
            if (ans2 != ans4) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops ans2:" + ans2 + "   ans4:" + ans4);
                break;
            }
        }
        System.out.println("finish" + " time1:" + time1 + " time2:" + time2);
    }

}

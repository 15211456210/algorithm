package com.zcp.part4.day03;

// 本题测试链接 : https://leetcode.com/problems/largest-1-bordered-square/
public class Code03_Largest1BorderedSquare {


    /**
     * 思路：
     * 预处理
     *
     * @param grid
     * @return
     */
    public int largest1BorderedSquare2(int[][] grid) {
        if (grid == null || grid.length < 0 || grid[0].length < 1) {
            return 0;
        }
        int N = grid.length;
        int M = grid[0].length;
        //两个预处理数组分别表是grid[i][j]右侧（下侧）有多少个连续的1（包括自身）
        int[][] right = new int[N][M];
        int[][] down = new int[N][M];
        initPrepare(grid, right, down);

        //枚举所有点 以及点有可能的边长
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int l = 1; l <= Math.min(N - i, M - j); l++) {
                    if (l > maxLen) {
                        if (right[i][j] >= l && down[i][j] >= l && right[i + l - 1][j] >= l && down[i][j + l - 1] >= l) {
                            maxLen = l;
                        }
                    }
                }
            }
        }
        return maxLen * maxLen;
    }

    /**
     * 存储预处理结构
     *
     * @param grid
     * @param right
     * @param down
     */
    private void initPrepare(int[][] grid, int[][] right, int[][] down) {
        int N = grid.length;
        int M = grid[0].length;
        for (int i = 0; i < N; i++) {
            right[i][M - 1] = grid[i][M - 1];
        }
        for (int i = 0; i < N; i++) {
            for (int j = M - 2; j >= 0; j--) {
                right[i][j] = grid[i][j] == 0 ? 0 : right[i][j + 1] + 1;
            }
        }

        for (int j = 0; j < M; j++) {
            down[N - 1][j] = grid[N - 1][j];
        }
        for (int j = 0; j < M; j++) {
            for (int i = N - 2; i >= 0; i--) {
                down[i][j] = grid[i][j] == 0 ? 0 : down[i + 1][j] + 1;
            }
        }
    }

    public static void main(String[] args) {
        new Code03_Largest1BorderedSquare().largest1BorderedSquare2(new int[][]{
                {1,1,1},
                {1,0,1},
                {1,1,1}

        });
    }

    public static int largest1BorderedSquare(int[][] m) {
        int[][] right = new int[m.length][m[0].length];
        int[][] down = new int[m.length][m[0].length];
        setBorderMap(m, right, down);
        for (int size = Math.min(m.length, m[0].length); size != 0; size--) {
            if (hasSizeOfBorder(size, right, down)) {
                return size * size;
            }
        }
        return 0;
    }


    public static void setBorderMap(int[][] m, int[][] right, int[][] down) {
        int r = m.length;
        int c = m[0].length;
        if (m[r - 1][c - 1] == 1) {
            right[r - 1][c - 1] = 1;
            down[r - 1][c - 1] = 1;
        }
        for (int i = r - 2; i != -1; i--) {
            if (m[i][c - 1] == 1) {
                right[i][c - 1] = 1;
                down[i][c - 1] = down[i + 1][c - 1] + 1;
            }
        }
        for (int i = c - 2; i != -1; i--) {
            if (m[r - 1][i] == 1) {
                right[r - 1][i] = right[r - 1][i + 1] + 1;
                down[r - 1][i] = 1;
            }
        }
        for (int i = r - 2; i != -1; i--) {
            for (int j = c - 2; j != -1; j--) {
                if (m[i][j] == 1) {
                    right[i][j] = right[i][j + 1] + 1;
                    down[i][j] = down[i + 1][j] + 1;
                }
            }
        }
    }

    public static boolean hasSizeOfBorder(int size, int[][] right, int[][] down) {
        for (int i = 0; i != right.length - size + 1; i++) {
            for (int j = 0; j != right[0].length - size + 1; j++) {
                if (right[i][j] >= size && down[i][j] >= size && right[i + size - 1][j] >= size
                        && down[i][j + size - 1] >= size) {
                    return true;
                }
            }
        }
        return false;
    }

}

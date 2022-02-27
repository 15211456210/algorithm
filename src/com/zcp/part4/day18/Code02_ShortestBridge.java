package com.zcp.part4.day18;

import java.util.ArrayList;
import java.util.List;

// 本题测试链接 : https://leetcode.com/problems/shortest-bridge/
public class Code02_ShortestBridge {


    class UnionFind {

        int[] src;
        int[] head;
        int row;
        int col;

        public UnionFind(int[][] grid) {
            row = grid.length;
            col = grid[0].length;
            src = new int[row * col];
            head = new int[row * col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    src[i * col + j] = grid[i][j];
                    head[i * col + j] = i * col + j;
                }
            }
        }

        int findHead(int index) {
            List<Integer> list = new ArrayList<>();
            while (head[index] != index) {
                list.add(index);
                index = head[index];
            }
            for (Integer i : list) {
                head[i] = index;
            }
            return index;
        }

        int findHead(int i, int j) {
            return findHead(i * col + j);
        }

        void uniun(int index1, int index2) {
            int head1 = findHead(index1);
            int head2 = findHead(index2);
            if (head1 == head2) {
                return;
            } else {
                head[head2] = head1;
            }
            return;
        }

        void uniun(int i1, int j1, int i2, int j2) {
            uniun(i1 * col + j1, i2 * col + j2);
        }

        List<Integer> getHeadIndexs() {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < head.length; i++) {
                if (src[i] == 1 && head[i] == i) {
                    list.add(i);
                }
            }
            return list;
        }

    }

    /**
     * 思路：
     * 并查集 + 扩散
     *
     * @param grid
     * @return
     */
    public int shortestBridge2(int[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(grid);

        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    if (i > 0 && grid[i - 1][j] == 1) {
                        unionFind.uniun(i - 1, j, i, j);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        unionFind.uniun(i, j - 1, i, j);
                    }
                }
            }
        }
        //假设有N座岛屿
        List<Integer> heads = unionFind.getHeadIndexs();
        int[][][] distanceMaps = new int[heads.size()][row][col];

        for (int m = 0; m < heads.size(); m++) {
            int head = heads.get(m);
            infect3(grid, distanceMaps[m], head, unionFind);
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; j++) {
//                    if (grid[i][j] == 1 && unionFind.findHead(i, j) == head) {
//                        distanceMaps[m][i][j] = 1;
//                        infect2(grid, distanceMaps, m, i, j);
//                    }
//                }
//            }
        }


        int min = Integer.MAX_VALUE;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 1) {
                    //不是岛屿部分
                    //岛屿数量
                    int size = heads.size();
                    //计算总距离
                    int sum = 0;
                    for (int m = 0; m < size; m++) {
                        //去掉首尾
                        sum += distanceMaps[m][i][j] - 2;
                    }
                    //最后+1
                    min = Math.min(min, sum + 1);
                }
            }
        }
        return min;
    }


    int[][] vector = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 传染
     *
     * @param grid
     * @param distanceMap
     * @param head
     * @param unionFind
     */
    private void infect3(int[][] grid, int[][] distanceMap, int head, UnionFind unionFind) {
        int row = distanceMap.length;
        int col = distanceMap[0].length;
        int extNum = row + col;//一共要往外括几轮

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1 && unionFind.findHead(i, j) == head) {
                    distanceMap[i][j] = 1;
                }
            }
        }

        for (int num = 0; num < extNum; num++) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (distanceMap[i][j] != 0) {
                        continue;
                    }
                    for (int v = 0; v < vector.length; v++) {
                        int di = i + vector[v][0];
                        int dj = j + vector[v][1];
                        if (di >= 0 && di < row && dj >= 0 && dj < col) {
                            if (distanceMap[di][dj] == 0) {
                                continue;
                            } else {
                                distanceMap[i][j] = (distanceMap[i][j] == 0 ? distanceMap[di][dj] + 1 : Math.min(distanceMap[i][j], distanceMap[di][dj] + 1));
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * 扩散生成距离图
     *
     * @param grid
     * @param distanceMaps
     * @param m
     * @param i
     * @param j
     */
    private void infect2(int[][] grid, int[][][] distanceMaps, int m, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;
        int curDistance = distanceMaps[m][i][j];
        for (int v = 0; v < vector.length; v++) {
            int di = i + vector[v][0];
            int dj = j + vector[v][1];
            if (di >= 0 && di < row && dj >= 0 && dj < col) {
                if (distanceMaps[m][di][dj] == 0) {
                    distanceMaps[m][di][dj] = curDistance + 1;
                } else {
                    distanceMaps[m][di][dj] = Math.min(distanceMaps[m][di][dj], curDistance + 1);
                }
                if (distanceMaps[m][di][dj] > distanceMaps[m][i][j]) {
                    //进入下一个位置传播下去
                    infect2(grid, distanceMaps, m, di, dj);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Code02_ShortestBridge().shortestBridge2(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 0, 1}}));
    }

    public static int shortestBridge(int[][] m) {
        int N = m.length;
        int M = m[0].length;
        int all = N * M;
        int island = 0;
        int[] curs = new int[all];
        int[] nexts = new int[all];
        int[][] records = new int[2][all];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (m[i][j] == 1) { // 当前位置发现了1！
                    // 把这一片的1，都变成2，同时，抓上来了，这一片1组成的初始队列
                    // curs, 把这一片的1到自己的距离，都设置成1了，records
                    int queueSize = infect(m, i, j, N, M, curs, 0, records[island]);
                    int V = 1;
                    while (queueSize != 0) {
                        V++;
                        // curs里面的点，上下左右，records[点]==0， nexts
                        queueSize = bfs(N, M, all, V, curs, queueSize, nexts, records[island]);
                        int[] tmp = curs;
                        curs = nexts;
                        nexts = tmp;
                    }
                    island++;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < all; i++) {
            min = Math.min(min, records[0][i] + records[1][i]);
        }
        return min - 3;
    }

    // 当前来到m[i][j] , 总行数是N，总列数是M
    // m[i][j]感染出去(找到这一片岛所有的1),把每一个1的坐标，放入到int[] curs队列！
    // 1 (a,b) -> curs[index++] = (a * M + b)
    // 1 (c,d) -> curs[index++] = (c * M + d)
    // 二维已经变成一维了， 1 (a,b) -> a * M + b
    // 设置距离record[a * M +b ] = 1
    public static int infect(int[][] m, int i, int j, int N, int M, int[] curs, int index, int[] record) {
        if (i < 0 || i == N || j < 0 || j == M || m[i][j] != 1) {
            return index;
        }
        // m[i][j] 不越界，且m[i][j] == 1
        m[i][j] = 2;
        int p = i * M + j;
        record[p] = 1;
        // 收集到不同的1
        curs[index++] = p;
        index = infect(m, i - 1, j, N, M, curs, index, record);
        index = infect(m, i + 1, j, N, M, curs, index, record);
        index = infect(m, i, j - 1, N, M, curs, index, record);
        index = infect(m, i, j + 1, N, M, curs, index, record);
        return index;
    }

    // 二维原始矩阵中，N总行数，M总列数
    // all 总 all = N * M
    // V 要生成的是第几层 curs V-1 nexts V
    // record里面拿距离
    public static int bfs(int N, int M, int all, int V,
                          int[] curs, int size, int[] nexts, int[] record) {
        int nexti = 0; // 我要生成的下一层队列成长到哪了？
        for (int i = 0; i < size; i++) {
            // curs[i] -> 一个位置
            int up = curs[i] < M ? -1 : curs[i] - M;
            int down = curs[i] + M >= all ? -1 : curs[i] + M;
            int left = curs[i] % M == 0 ? -1 : curs[i] - 1;
            int right = curs[i] % M == M - 1 ? -1 : curs[i] + 1;
            if (up != -1 && record[up] == 0) {
                record[up] = V;
                nexts[nexti++] = up;
            }
            if (down != -1 && record[down] == 0) {
                record[down] = V;
                nexts[nexti++] = down;
            }
            if (left != -1 && record[left] == 0) {
                record[left] = V;
                nexts[nexti++] = left;
            }
            if (right != -1 && record[right] == 0) {
                record[right] = V;
                nexts[nexti++] = right;
            }
        }
        return nexti;
    }

}

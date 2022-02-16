package com.zcp.part4.day13;

import com.zcp.part4.day05.Hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 本题测试链接 : https://leetcode.com/problems/bricks-falling-when-hit/
public class Code04_BricksFallingWhenHit {

    /**
     * 思路：
     * 并查集+逆向
     *
     * @param grid
     * @param hits
     * @return
     */

    //head[i] 表示某个砖块的代表节点是谁
    int[] head;
    //size[i] 表示某个代表节点所在集合的元素个数
    int[] size;
    //原始数组扁平化处理后的 数据
    int[] data;

    int rowSize;
    int colSize;

    int stableNum;

    public int[] hitBricks(int[][] grid, int[][] hits) {
        if (grid == null || grid.length < 1) {
            return new int[hits.length];
        }
        //初始化并查集
        initUnionFind(grid);
        initData(grid, hits);

        int[] ans = new int[hits.length];

        //计算结果
        for (int i = hits.length - 1; i >= 0; i--) {
            int dataIndex = hits[i][0] * colSize + hits[i][1];
            if (data[dataIndex] == 2) {
                data[dataIndex] = 1;
                //分别向4个方向去合并
                union(hits[i][0], hits[i][1], hits[i][0] - 1, hits[i][1]);
                union(hits[i][0], hits[i][1], hits[i][0], hits[i][1] - 1);
                union(hits[i][0], hits[i][1], hits[i][0] + 1, hits[i][1]);
                union(hits[i][0], hits[i][1], hits[i][0], hits[i][1] + 1);
                //计算 稳定 砖块数
                int curStableNum = calcStableNum();
                //计算第i次炮弹打击后 掉落的砖块数（逆向思维），其中需要减去自身的砖块
                //可能这次炮弹打击后，并没有掉落砖块，所以需要0兜底
                ans[i] = Math.max(0, curStableNum - stableNum - 1);
                stableNum = curStableNum;
            }
        }
        return ans;
    }

    /**
     * 初始化大小
     *
     * @param grid
     */
    private void initUnionFind(int[][] grid) {
        int rowSize = grid.length;
        int colSize = grid[0].length;

        head = new int[rowSize * colSize];
        size = new int[rowSize * colSize];
        data = new int[rowSize * colSize];
    }

    /**
     * 初始化数据
     *
     * @param grid
     * @param hits
     */
    private void initData(int[][] grid, int[][] hits) {
        rowSize = grid.length;
        colSize = grid[0].length;
        //设置data数据
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                int dataIndex = i * colSize + j;
                if (grid[i][j] == 1) {
                    data[dataIndex] = grid[i][j];
                    //将每一个砖块的head都指向自己 size设置为1
                    head[dataIndex] = dataIndex;
                    size[dataIndex] = 1;
                }
            }
        }
        //标记炮弹位置
        for (int i = 0; i < hits.length; i++) {
            if (data[hits[i][0] * colSize + hits[i][1]] == 1) {
                data[hits[i][0] * colSize + hits[i][1]] = 2;//标记打砖块的位置
            }
        }
        //初始化集合
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                int dataIndex = i * colSize + j;
                if (data[dataIndex] == 1) {
                    //分别向4个方向去合并
                    union(i, j, i - 1, j);
                    union(i, j, i, j - 1);
                    union(i, j, i + 1, j);
                    union(i, j, i, j + 1);
                }
            }
        }
        //计算当前粘墙上 总砖块数量
        stableNum = calcStableNum();
    }

    /**
     * 计算当前粘墙上 总砖块数量
     */
    private int calcStableNum() {
        int num = 0;
        boolean[] checked = new boolean[colSize];
        for (int i = 0; i < colSize; i++) {
            if (data[i] == 1) {
                int head = findHead(i);
                if (!checked[head]) {
                    num += size[head];
                    checked[head] = true;
                }
            }
        }
        return num;
    }

    /**
     * 合并两个集合
     *
     * @param i1
     * @param j1
     * @param i2
     * @param j2
     */
    private void union(int i1, int j1, int i2, int j2) {
        if (i1 < 0 || i2 < 0 || j1 < 0 || j2 < 0 || i1 >= rowSize || i2 >= rowSize || j1 >= colSize || j2 >= colSize) {
            return;
        }
        int index1 = i1 * colSize + j1;
        int index2 = i2 * colSize + j2;
        if (data[index1] == 1 && data[index2] == 1) {
            //只有 两个点都为 1 才合并
            int headIdx1 = findHead(index1);
            int headIdx2 = findHead(index2);
            if (headIdx1 != headIdx2) {
                //如果 2个 不在同一个集合 需要合并
                if (headIdx1 < colSize && headIdx2 >= colSize) {
                    //说明headIdx1是贴墙的，headIdx2不是，那么需要将idx2合并到idx1中去
                    head[headIdx2] = headIdx1;
                    size[headIdx1] += size[headIdx2];
                } else if (headIdx1 >= colSize && headIdx2 < colSize) {
                    //idx1->idx2
                    head[headIdx1] = headIdx2;
                    size[headIdx2] += size[headIdx1];
                } else {
                    //说明 idx1，idx2所在集合 都是贴墙 或则 都不贴墙
                    //那么无所谓谁合并谁
                    head[headIdx1] = headIdx2;
                    size[headIdx2] += size[headIdx1];
                }
            }

        }
    }

    /**
     * 返回data[idx]的head节点
     *
     * @param idx
     * @return
     */
    private int findHead(int idx) {
//        List<Integer> paths = new ArrayList<>();
        while (head[idx] != idx) {
//            paths.add(idx);
            idx = head[idx];
        }
        //优化：将路经的点直接指向头
//        for (Integer path : paths) {
//            head[path] = idx;
//        }
        return idx;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Code04_BricksFallingWhenHit().hitBricks(new int[][]{{0, 1}, {1, 1}}, new int[][]{{1, 1}})));
    }

    /**
     * ============================================================================================================================
     */

    /**
     * 思路：
     * 每次炮弹 计算一下所有剩余且稳定的砖块
     *
     * @param grid
     * @param hits
     * @return
     */
    public int[] hitBricks2(int[][] grid, int[][] hits) {
        if (grid == null || grid.length < 1) {
            return new int[hits.length];
        }
        int[] ans = new int[hits.length];
        int m = grid.length;
        int n = grid[0].length;
        //用来标记grid[i][j]位置在当前是否计算过
        //下标换算： grid[i][j] -> checkMap[i*n+j]
        boolean[] checkMap = new boolean[m * n];
        int totalStable = getStableCnt(grid, checkMap);

        for (int i = 0; i < hits.length; i++) {
            if (grid[hits[i][0]][hits[i][1]] == 0) {
                continue;
            }
            grid[hits[i][0]][hits[i][1]] = 0;
            Arrays.fill(checkMap, false);
            int curStable = getStableCnt(grid, checkMap);
            ans[i] = Math.max(0, totalStable - curStable - 1);
            totalStable = curStable;
        }
        return ans;
    }

    /**
     * 获取稳定的 砖块数
     *
     * @param grid
     * @param checkMap
     * @return
     */
    private int getStableCnt(int[][] grid, boolean[] checkMap) {
        int n = grid[0].length;

        //计算所有和第一排粘在一起的砖块数量（稳定的砖块总数）
        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum += process3(grid, checkMap, 0, j);
        }
        return sum;
    }

    /**
     * 返回 从grid[i][j]位置开始 感染，稳定砖块的总数
     *
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public int process3(int[][] grid, boolean[] checkMap, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || checkMap[i * grid[0].length + j]) {
            //grid[i][j] == 0 或则 下标越界 或则 说明这个砖块已经计算过了
            return 0;
        }

        checkMap[i * grid[0].length + j] = true;

        int sum = 0;
        sum += process3(grid, checkMap, i - 1, j);
        sum += process3(grid, checkMap, i, j - 1);
        sum += process3(grid, checkMap, i + 1, j);
        sum += process3(grid, checkMap, i, j + 1);
        return sum + 1;
    }

//    public static int[] hitBricks(int[][] grid, int[][] hits) {
//        for (int i = 0; i < hits.length; i++) {
//            if (grid[hits[i][0]][hits[i][1]] == 1) {
//                grid[hits[i][0]][hits[i][1]] = 2;
//            }
//        }
//        UnionFind unionFind = new UnionFind(grid);
//        int[] ans = new int[hits.length];
//        for (int i = hits.length - 1; i >= 0; i--) {
//            if (grid[hits[i][0]][hits[i][1]] == 2) {
//                ans[i] = unionFind.finger(hits[i][0], hits[i][1]);
//            }
//        }
//        return ans;
//    }

//    // 并查集
//    public static class UnionFind {
//        private int N;
//        private int M;
//        // 有多少块砖，连到了天花板上
//        private int cellingAll;
//        // 原始矩阵，因为炮弹的影响，1 -> 2
//        private int[][] grid;
//        // cellingSet[i] = true; i 是头节点，所在的集合是天花板集合
//        private boolean[] cellingSet;
//        private int[] fatherMap;
//        private int[] sizeMap;
//        private int[] stack;
//
//        public UnionFind(int[][] matrix) {
//            initSpace(matrix);
//            initConnect();
//        }
//
//        private void initSpace(int[][] matrix) {
//            grid = matrix;
//            N = grid.length;
//            M = grid[0].length;
//            int all = N * M;
//            cellingAll = 0;
//            cellingSet = new boolean[all];
//            fatherMap = new int[all];
//            sizeMap = new int[all];
//            stack = new int[all];
//            for (int row = 0; row < N; row++) {
//                for (int col = 0; col < M; col++) {
//                    if (grid[row][col] == 1) {
//                        int index = row * M + col;
//                        fatherMap[index] = index;
//                        sizeMap[index] = 1;
//                        if (row == 0) {
//                            cellingSet[index] = true;
//                            cellingAll++;
//                        }
//                    }
//                }
//            }
//        }
//
//        private void initConnect() {
//            for (int row = 0; row < N; row++) {
//                for (int col = 0; col < M; col++) {
//                    union(row, col, row - 1, col);
//                    union(row, col, row + 1, col);
//                    union(row, col, row, col - 1);
//                    union(row, col, row, col + 1);
//                }
//            }
//        }
//
//        private int find(int row, int col) {
//            int stackSize = 0;
//            int index = row * M + col;
//            while (index != fatherMap[index]) {
//                stack[stackSize++] = index;
//                index = fatherMap[index];
//            }
//            while (stackSize != 0) {
//                fatherMap[stack[--stackSize]] = index;
//            }
//            return index;
//        }
//
//        private void union(int r1, int c1, int r2, int c2) {
//            if (valid(r1, c1) && valid(r2, c2)) {
//                int father1 = find(r1, c1);
//                int father2 = find(r2, c2);
//                if (father1 != father2) {
//                    int size1 = sizeMap[father1];
//                    int size2 = sizeMap[father2];
//                    boolean status1 = cellingSet[father1];
//                    boolean status2 = cellingSet[father2];
//                    if (size1 <= size2) {
//                        fatherMap[father1] = father2;
//                        sizeMap[father2] = size1 + size2;
//                        if (status1 ^ status2) {
//                            cellingSet[father2] = true;
//                            cellingAll += status1 ? size2 : size1;
//                        }
//                    } else {
//                        fatherMap[father2] = father1;
//                        sizeMap[father1] = size1 + size2;
//                        if (status1 ^ status2) {
//                            cellingSet[father1] = true;
//                            cellingAll += status1 ? size2 : size1;
//                        }
//                    }
//                }
//            }
//        }
//
//        private boolean valid(int row, int col) {
//            return row >= 0 && row < N && col >= 0 && col < M && grid[row][col] == 1;
//        }
//
//        public int cellingNum() {
//            return cellingAll;
//        }
//
//        public int finger(int row, int col) {
//            grid[row][col] = 1;
//            int cur = row * M + col;
//            if (row == 0) {
//                cellingSet[cur] = true;
//                cellingAll++;
//            }
//            fatherMap[cur] = cur;
//            sizeMap[cur] = 1;
//            int pre = cellingAll;
//            union(row, col, row - 1, col);
//            union(row, col, row + 1, col);
//            union(row, col, row, col - 1);
//            union(row, col, row, col + 1);
//            int now = cellingAll;
//            if (row == 0) {
//                return now - pre;
//            } else {
//                return now == pre ? 0 : now - pre - 1;
//            }
//        }
//    }

}

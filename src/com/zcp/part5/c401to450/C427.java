package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C427
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/construct-quad-tree/
 * @date 2023/1/26 19:56
 */
public class C427 {

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    class Info {
        boolean isAllOne;
        boolean isAllZero;
        int len;
        Node node;
    }

    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length).node;
    }

    /**
     * @param grid
     * @param i1   左上角位置
     * @param j1
     * @return
     */
    private Info dfs(int[][] grid, int i1, int j1, int len) {
        Info info = new Info();
        info.node = new Node();
        info.len = len;
        if (len == 1) {
            info.isAllOne = grid[i1][j1] == 1;
            info.isAllZero = grid[i1][j1] == 0;
            info.node.isLeaf = true;
            info.node.val = grid[i1][j1] == 1;
        } else {
            Info topLeft = dfs(grid, i1, j1, len / 2);
            Info topRight = dfs(grid, i1, j1 + len / 2, len / 2);
            Info bottomLeft = dfs(grid, i1 + len / 2, j1, len / 2);
            Info bottomRight = dfs(grid, i1 + len / 2, j1 + len / 2, len / 2);
            info.isAllOne = topLeft.isAllOne && topRight.isAllOne && bottomLeft.isAllOne && bottomRight.isAllOne;
            info.isAllZero = topLeft.isAllZero && topRight.isAllZero && bottomLeft.isAllZero && bottomRight.isAllZero;
            info.node.isLeaf = info.isAllOne || info.isAllZero;
            info.node.val = !info.isAllZero;
            if (!info.node.isLeaf) {
                info.node.topLeft = topLeft.node;
                info.node.topRight = topRight.node;
                info.node.bottomLeft = bottomLeft.node;
                info.node.bottomRight = bottomRight.node;
            }
        }
        return info;

    }

}

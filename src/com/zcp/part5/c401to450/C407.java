package com.zcp.part5.c401to450;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ZCP
 * @title: C407
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/trapping-rain-water-ii/description/
 * @date 2022/11/21 15:11
 */
public class C407 {

    class Node {
        int val;
        int row;
        int col;

        public Node(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.val - n2.val;
            }
        });

        boolean[][] isAdd = new boolean[m][n];

        for (int i = 0; i < n; i++) {
            queue.add(new Node(heightMap[0][i], 0, i));
            isAdd[0][i] = true;
            queue.add(new Node(heightMap[m - 1][i], m - 1, i));
            isAdd[m - 1][i] = true;
        }

        for (int i = 1; i < m - 1; i++) {
            queue.add(new Node(heightMap[i][0], i, 0));
            isAdd[i][0] = true;
            queue.add(new Node(heightMap[i][n - 1], i, n - 1));
            isAdd[i][n - 1] = true;
        }

        int maxH = 0;
        int ans = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            maxH = Math.max(maxH, node.val);
            ans += Math.max(0, maxH - node.val);

            if (node.row > 0 && !isAdd[node.row - 1][node.col]) {
                queue.add(new Node(heightMap[node.row - 1][node.col], node.row - 1, node.col));
                isAdd[node.row - 1][node.col] = true;
            }
            if (node.row < m - 1 && !isAdd[node.row + 1][node.col]) {
                queue.add(new Node(heightMap[node.row + 1][node.col], node.row + 1, node.col));
                isAdd[node.row + 1][node.col] = true;
            }
            if (node.col > 0 && !isAdd[node.row][node.col - 1]) {
                queue.add(new Node(heightMap[node.row][node.col - 1], node.row, node.col - 1));
                isAdd[node.row][node.col - 1] = true;
            }
            if (node.col < n - 1 && !isAdd[node.row][node.col + 1]) {
                queue.add(new Node(heightMap[node.row][node.col + 1], node.row, node.col + 1));
                isAdd[node.row][node.col + 1] = true;
            }
        }
        return ans;
    }
}

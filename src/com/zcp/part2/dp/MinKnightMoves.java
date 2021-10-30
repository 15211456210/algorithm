package com.zcp.part2.dp;

import com.sun.org.apache.regexp.internal.RE;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/22
 * @description： [[象棋问题-马只走K步跳到指定位置的方法数]]
 * https://leetcode-cn.com/problems/minimum-knight-moves/
 * @version:
 */
public class MinKnightMoves {


    public int minKnightMoves2(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        int[][] ds = {{1, 2}, {2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};//表示8个方向
        Node node = new Node(333, 333, 0);
        x += 333;
        y += 333;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            int[] dist = new int[8];
            int minIndex = 0;
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < ds.length; i++) {
                int cx = poll.x + ds[i][0];
                int cy = poll.y + ds[i][1];
                dist[i] = calcDest(x, y, cx, cy);//计算每个方向的点离目标的的距离
                if (dist[i] == 0) {
                    return poll.moveCnt + 1;
                }
                if (dist[i] > 1 && dist[i] < minDist && dist[i] != 8) {
                    //田字格不考虑
                    minIndex = i;//选出距离至少是2的下一步离目标点最近的点
                    minDist = dist[i];
                }
            }
            queue.add(new Node(poll.x + ds[minIndex][0], poll.y + ds[minIndex][1], poll.moveCnt + 1));
        }
        return -1;
    }

    /**
     * 计算距离
     *
     * @param x
     * @param y
     * @param cx
     * @param cy
     * @return
     */
    public int calcDest(int x, int y, int cx, int cy) {
        int dx = Math.abs(x - cx);
        int dy = Math.abs(y - cy);
        return dx * dx + dy * dy;
    }

    public static class Node {
        int x;
        int y;
        int moveCnt;

        public Node(int x, int y, int moveCnt) {
            this.x = x;
            this.y = y;
            this.moveCnt = moveCnt;
        }
    }

    public static void main(String[] args) {
        MinKnightMoves minKnightMoves = new MinKnightMoves();
        System.out.println(minKnightMoves.minKnightMoves2(5, 5));
    }
}

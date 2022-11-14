package com.zcp.part5.c351to400;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ZCP
 * @title: C391
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/perfect-rectangle/description/
 * @date 2022/11/3 11:02
 */
public class C391 {

    class Pair implements Comparable<Pair> {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair p) {
            return x + y - (p.x + p.y);
        }

        @Override
        public String toString() {
            return x + "_" + y;
        }
    }

    public boolean isRectangleCover(int[][] rectangles) {
        Map<String, int[]> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < rectangles.length; i++) {
//            Pair p1 = new Pair(rectangles[i][0], rectangles[i][1]);
//            Pair p2 = new Pair(rectangles[i][2], rectangles[i][3]);
//            Pair p3 = new Pair(rectangles[i][0], rectangles[i][3]);
//            Pair p4 = new Pair(rectangles[i][2], rectangles[i][1]);

            sum += (rectangles[i][2] - rectangles[i][0]) * (rectangles[i][3] - rectangles[i][1]);
            String p1 = rectangles[i][0] + "_" + rectangles[i][1];
            if (map.containsKey(p1)) {
                map.remove(p1);
            } else {
                map.put(p1, new int[]{rectangles[i][0], rectangles[i][1]});
            }
            String p2 = rectangles[i][2] + "_" + rectangles[i][3];
            if (map.containsKey(p2)) {
                map.remove(p2);
            } else {
                map.put(p2, new int[]{rectangles[i][2], rectangles[i][3]});
            }
            String p3 = rectangles[i][0] + "_" + rectangles[i][3];
            if (map.containsKey(p3)) {
                map.remove(p3);
            } else {
                map.put(p3, new int[]{rectangles[i][0], rectangles[i][3]});
            }
            String p4 = rectangles[i][2] + "_" + rectangles[i][1];
            if (map.containsKey(p4)) {
                map.remove(p4);
            } else {
                map.put(p4, new int[]{rectangles[i][2], rectangles[i][1]});
            }
        }
        if (map.size() != 4) {
            return false;
        }
        Iterator<int[]> iterator = map.values().iterator();
        int[] min = null, max = null;
        while (iterator.hasNext()) {
            int[] next = iterator.next();
            min = min == null ? next : min[0] + min[1] - (next[0] + next[1]) > 0 ? next : min;
            max = max == null ? next : max[0] + max[1] - (next[0] + next[1]) < 0 ? next : max;
        }
        return (max[0] - min[0]) * (max[1] - min[1]) == sum;
    }

    public static void main(String[] args) {
        // [[1,1,3,3],[3,1,4,2],[3,2,4,4],[1,3,2,4],[2,3,3,4]]
        System.out.println(new C391().isRectangleCover(new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}}));
    }
}

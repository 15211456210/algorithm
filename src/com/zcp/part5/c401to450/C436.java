package com.zcp.part5.c401to450;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author ZCP
 * @title: C436
 * @projectName algorithm
 * @description: https://leetcode.com/problems/find-right-interval/
 * @date 2023/1/30 19:26
 */
public class C436 {

    class Node implements Comparable {
        int start;
        int index;

        public Node(int start, int index) {
            this.start = start;
            this.index = index;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Integer) {
                return start - (int) o;
            }
            if (o instanceof Node) {
                return start - ((Node) o).start;
            }
            return 0;
        }
    }

    public int[] findRightInterval(int[][] intervals) {
        TreeSet<Node> treeSet = new TreeSet<>();
        int length = intervals.length;
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            treeSet.add(new Node(intervals[i][0], i));
        }
        for (int i = 0; i < length; i++) {
            Object ceiling = treeSet.ceiling(new Node(intervals[i][1], -1));
            ans[i] = ceiling == null ? -1 : ((Node) ceiling).index;
        }
        return ans;
    }
}

package com.zcp.part4.day19;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

// 本题测试链接 : https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
public class Code04_SmallestRangeCoveringElementsfromKLists {

    public static class Node {
        public int value;
        public int arrid;
        public int index;

        public Node(int v, int ai, int i) {
            value = v;
            arrid = ai;
            index = i;
        }
    }


    /**
     * 思路：
     * 有序表
     *
     * @param nums
     * @return
     */
    public int[] smallestRange2(List<List<Integer>> nums) {
        if (nums == null || nums.size() < 1) {
            return new int[0];
        }
        TreeSet<Node> set = new TreeSet<>(new NodeComparator());
        int size = nums.size();

        for (int i = 0; i < size; i++) {
            set.add(new Node(nums.get(i).get(0), i, 0));
        }

        int[] ans = new int[2];
        ans[0] = set.first().value;
        ans[1] = set.last().value;
        int space = ans[1] - ans[0];
        while (true) {
            Node node = set.pollFirst();
            if (nums.get(node.arrid).size() - 1 == node.index) {
                return ans;
            }
            node.value = nums.get(node.arrid).get(++node.index);
            set.add(node);
            if (set.last().value - set.first().value < space) {
                space = set.last().value - set.first().value;
                ans[0] = set.first().value;
                ans[1] = set.last().value;
            }
        }
    }


    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value != o2.value ? o1.value - o2.value : o1.arrid - o2.arrid;
        }

    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        int N = nums.size();
        TreeSet<Node> orderSet = new TreeSet<>(new NodeComparator());
        for (int i = 0; i < N; i++) {
            orderSet.add(new Node(nums.get(i).get(0), i, 0));
        }
        boolean set = false;
        int a = 0;
        int b = 0;
        while (orderSet.size() == N) {
            Node min = orderSet.first();
            Node max = orderSet.last();
            if (!set || (max.value - min.value < b - a)) {
                set = true;
                a = min.value;
                b = max.value;
            }
            min = orderSet.pollFirst();
            int arrid = min.arrid;
            int index = min.index + 1;
            if (index != nums.get(arrid).size()) {
                orderSet.add(new Node(nums.get(arrid).get(index), arrid, index));
            }
        }
        return new int[]{a, b};
    }

}

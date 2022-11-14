package com.zcp.part5.c201to250;

import java.util.*;

/**
 * @author ZCP
 * @title: C218
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/the-skyline-problem/
 * @date 2022/9/17 17:30
 */
public class C218 {

    public static class Node {
        public int x;
        public boolean isAdd;
        public int h;

        public Node(int x, boolean isAdd, int h) {
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }


    /**
     * 思路：
     * 有序表，
     *
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length < 1) {
            return new ArrayList<>();
        }
        TreeSet<Node> treeSet = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.x == o2.x ? (o1.isAdd == o2.isAdd ? o1.h - o2.h : o1.isAdd ? 1 : -1) : o1.x - o2.x;
            }
        });
        int N = buildings.length;
        for (int i = 0; i < N; i++) {
            treeSet.add(new Node(buildings[i][0], true, buildings[i][2]));
            treeSet.add(new Node(buildings[i][1], false, buildings[i][2]));
        }

        TreeMap<Integer, Integer> rank = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        ArrayList<List<Integer>> ans = new ArrayList<>();

        List<Integer> pre = null;
        Iterator<Node> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.isAdd) {
                if (!rank.containsKey(next.h)) {
                    rank.put(next.h, 0);
                }
                rank.put(next.h, rank.get(next.h) + 1);
            } else {
                if (rank.get(next.h).equals(1)) {
                    rank.remove(next.h);
                } else {
                    rank.put(next.h, rank.get(next.h) - 1);
                }
            }
            //判断高度变化
            java.util.Map.Entry<Integer, Integer> entry = rank.firstEntry();
            if (pre == null) {
                pre = Arrays.asList(new Integer[]{next.x, entry.getKey()});
                ans.add(pre);
            } else {
                if (pre.get(0).equals(next.x)) {
                    //相同x坐标 多次操作 取最后一次
                    int max = entry == null ? 0 : entry.getKey();
                    pre.set(1, max);
                    ans.set(ans.size() - 1, pre);
                } else {
                    //x不同的情况
                    int max = entry == null ? 0 : entry.getKey();
                    if (!pre.get(1).equals(max)) {
                        //高度变化了才更新
                        pre = Arrays.asList(new Integer[]{next.x, max});
                        ans.add(pre);
                    }
                }
                //如果这一测计算的结果高度 和 上一个高度一样，只保留先前的节点
                if (ans.size() > 1 && ans.get(ans.size() - 1).get(1).equals(ans.get(ans.size() - 2).get(1))) {
                    ans.remove(ans.size() - 1);
                    pre = ans.get(ans.size() - 1);
                }
            }
        }
        return ans;
    }
}

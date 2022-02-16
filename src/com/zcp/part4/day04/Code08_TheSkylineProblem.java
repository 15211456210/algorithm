package com.zcp.part4.day04;

import java.util.*;
import java.util.Map.Entry;

// 本题测试链接 : https://leetcode.com/problems/the-skyline-problem/
public class Code08_TheSkylineProblem {

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
     * 有序表
     *
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline2(int[][] buildings) {
        if (buildings == null || buildings.length < 1) {
            return new ArrayList<>();
        }
        //存放所有位置的高度（每座楼对应两条高度Node对象）
        TreeSet<Node> treeSet = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                //排序很有讲究 优先更具x坐标排序，如果x坐标相等，更具是否add=true（表示楼左侧高度），在更具楼高度排序（最高的应该留到最后处理）
                return o1.x == o2.x ? (o1.isAdd == o2.isAdd ? o1.h - o2.h : o1.isAdd ? 1 : -1) : o1.x - o2.x;
            }
        });
        int N = buildings.length;
        for (int i = 0; i < N; i++) {
            treeSet.add(new Node(buildings[i][0], true, buildings[i][2]));
            treeSet.add(new Node(buildings[i][1], false, buildings[i][2]));
        }

        //存储当前位置 （还存在的楼） 楼高有序表 根据楼高降序 排序
        TreeMap<Integer, Integer> rank = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        //存放结果的数组
        ArrayList<List<Integer>> ans = new ArrayList<>();

        List<Integer> pre = null;
        Iterator<Node> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            //从treeSet去除每个元素，判断是否有高度变化，如果有，则更新ans数据
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


    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.x - o2.x;
        }
    }

    public static List<List<Integer>> getSkyline(int[][] matrix) {
        Node[] nodes = new Node[matrix.length * 2];
        for (int i = 0; i < matrix.length; i++) {
            nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);
            nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
        }
        Arrays.sort(nodes, new NodeComparator());
        // key  高度  value 次数
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();
        TreeMap<Integer, Integer> mapXHeight = new TreeMap<>();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isAdd) {
                if (!mapHeightTimes.containsKey(nodes[i].h)) {
                    mapHeightTimes.put(nodes[i].h, 1);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
                }
            } else {
                if (mapHeightTimes.get(nodes[i].h) == 1) {
                    mapHeightTimes.remove(nodes[i].h);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
                }
            }
            if (mapHeightTimes.isEmpty()) {
                mapXHeight.put(nodes[i].x, 0);
            } else {
                mapXHeight.put(nodes[i].x, mapHeightTimes.lastKey());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Entry<Integer, Integer> entry : mapXHeight.entrySet()) {
            int curX = entry.getKey();
            int curMaxHeight = entry.getValue();
            if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight) {
                ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
            }
        }
        return ans;
    }

}

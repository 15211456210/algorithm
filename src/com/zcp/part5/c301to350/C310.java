package com.zcp.part5.c301to350;

import java.util.*;

/**
 * @author ZCP
 * @title: C310
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/minimum-height-trees/
 * @date 2022/9/29 14:58
 */
public class C310 {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (edges.length == 0) {
            ArrayList list = new ArrayList<>();
            list.add(0);
            return list;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int[] degree = new int[n];
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int[] edge : edges) {
            List<Integer> list = map.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            map.put(edge[0], list);
            list = map.getOrDefault(edge[1], new ArrayList<>());
            list.add(edge[0]);
            map.put(edge[1], list);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }


        while (!queue.isEmpty()) {
            ans.clear();
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                Integer cur = queue.poll();
                ans.add(cur);
                List<Integer> nexts = map.getOrDefault(cur, new ArrayList<>());
                for (int next : nexts) {
                    if (--degree[next] == 1) {
                        queue.offer(next);
                    }
                }

            }
        }


        return ans;

    }
}

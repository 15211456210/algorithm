package com.zcp.part5.c201to250;

import java.util.*;

/**
 * @author ZCP
 * @title: C210
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/course-schedule-ii/submissions/
 * @date 2022/9/16 9:38
 */
public class C210 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new HashSet<Integer>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            map.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        int[] ans = new int[numCourses];

        int index = 0;

        while (index < numCourses) {
            List<Integer> candicates = new ArrayList<>();
            for (Integer cNum : map.keySet()) {
                if (map.get(cNum).size() == 0) {
                    ans[index++] = cNum;
                    candicates.add(cNum);
                }
            }
            if (candicates.size() == 0) {
                return new int[0];
            }
            for (Integer delNum : candicates) {
                map.remove(delNum);
            }

            for (Integer cNum : map.keySet()) {
                Set<Integer> set = map.get(cNum);
                for (Integer delNum : candicates) {
                    set.remove(delNum);
                }
            }
        }
        return ans;
    }
}

package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ZCP
 * @title: c057
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/insert-interval/submissions/
 * @date 2022/8/28 16:35
 */
public class c057 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        Queue<int[]> queue = new LinkedList<>();
        boolean hasAdd = false;

        for (int i = 0; i < intervals.length; i++) {
            if (!hasAdd && intervals[i][0] >= newInterval[0]) {
                queue.offer(newInterval);
                hasAdd = true;
            }
            queue.offer(intervals[i]);
        }
        if (!hasAdd) {
            queue.offer(newInterval);
        }

        ArrayList<int[]> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int end = poll[1];
            while (!queue.isEmpty() && queue.peek()[0] <= end) {
                end = Math.max(end, queue.poll()[1]);
            }
            ans.add(new int[]{poll[0], end});
        }
        int[][] ints = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ints[i] = ans.get(i);
        }
        return ints;
    }
}

package com.zcp.part5.c401to450;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author ZCP
 * @title: C435
 * @projectName algorithm
 * @description: https://leetcode.com/problems/non-overlapping-intervals/
 * @date 2023/1/29 19:58
 */
public class C435 {


    /**
     * 贪心
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);
        int ans = 1;
        int right = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= right) {
                ans++;
                right = intervals[i][1];
            }
        }
        return intervals.length - ans;
    }

//    class Segment implements Comparable<Segment> {
//        int start;
//        int end;
//
//        public Segment(int start, int end) {
//            this.start = start;
//            this.end = end;
//        }
//
//        @Override
//        public int compareTo(Segment o) {
//            return start - o.start;
//        }
//    }
//
//    public int eraseOverlapIntervals(int[][] intervals) {
//        TreeSet<Segment> treeSet = new TreeSet<>();
//        int length = intervals.length;
//        int ans = 0;
//        for (int i = 0; i < length; i++) {
//            Segment cur = new Segment(intervals[i][0], intervals[i][1]);
//            // 首先先判断是否处于 现有的区间内部
//            Segment floor = treeSet.floor(cur);
//            if (floor != null && floor.end >= cur.end){
//                ans++;
//                continue;
//            }
//            // 检查是否覆盖
//            Segment higher = treeSet.ceiling(cur);
//            while (higher != null && higher.end <= cur.end) {
//                ans++;
//                treeSet.remove(higher);
//            }
//            treeSet.add(cur);
//        }
//        return ans;
//    }
}

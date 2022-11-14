package com.zcp.part5.c351to400;

import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author ZCP
 * @title: C352
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/data-stream-as-disjoint-intervals/submissions/
 * @date 2022/10/20 16:20
 */
public class C352 {

    class SummaryRanges {

        HashSet<Integer> set;

        TreeSet<Integer> head;

        TreeSet<Integer> tail;

        public SummaryRanges() {
            set = new HashSet<>();
            head = new TreeSet<>();
            tail = new TreeSet<>();
        }

        public void addNum(int num) {
            if (set.contains(num)) {
                return;
            }
            set.add(num);
            head.add(num);
            tail.add(num);
            //检查前面是否有num-1结尾的
            if (tail.contains(num - 1)) {
                tail.remove(num - 1);
                head.remove(num);
            }

            //检查后面是否有num+1的节点

            if (head.contains(num + 1)) {
                head.remove(num + 1);
                tail.remove(num);
            }

        }

        public int[][] getIntervals() {
            int[][] ans = new int[head.size()][2];

            TreeSet<Integer> newhead = new TreeSet<>();

            TreeSet<Integer> newtail = new TreeSet<>();
            int size = head.size();
            for (int i = 0; i < size; i++) {
                ans[i][0] = head.pollFirst();
                ans[i][1] = tail.pollFirst();
                newhead.add(ans[i][0]);
                newtail.add(ans[i][1]);
            }

            head = newhead;
            tail = newtail;

            return ans;
        }
    }
}

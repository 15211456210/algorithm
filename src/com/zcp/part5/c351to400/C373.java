package com.zcp.part5.c351to400;

import java.util.*;

/**
 * @author ZCP
 * @title: C373
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/description/
 * @date 2022/10/26 15:28
 */
public class C373 {

    class Node implements Comparable<Node> {
        int v1;
        int v2;

        int i1;
        int i2;

        public Node(int v1, int v2, int i1, int i2) {
            this.v1 = v1;
            this.v2 = v2;
            this.i1 = i1;
            this.i2 = i2;
        }

        @Override
        public int compareTo(Node o) {
            return v1 + v2 - (o.v1 + o.v2);
        }

        public long hash() {
            return (long) i1 * 100000 + (long) i2;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        HashSet<Long> set = new HashSet<>();

        ArrayList<List<Integer>> ans = new ArrayList<>();

        Node node = new Node(nums1[0], nums2[0], 0, 0);
        priorityQueue.offer(node);
        set.add(node.hash());
        while (k > 0 && !priorityQueue.isEmpty()) {
            --k;
            Node poll = priorityQueue.poll();
            ArrayList<Integer> list = new ArrayList<>();
            list.add(poll.v1);
            list.add(poll.v2);
            ans.add(list);
            if (poll.i1 + 1 < nums1.length) {
                Node tn = new Node(nums1[poll.i1 + 1], nums2[poll.i2], poll.i1 + 1, poll.i2);
                if (!set.contains(tn.hash())) {
                    priorityQueue.offer(tn);
                    set.add(tn.hash());
                }
            }
            if (poll.i2 + 1 < nums2.length) {
                Node tn = new Node(nums1[poll.i1], nums2[poll.i2 + 1], poll.i1, poll.i2 + 1);
                if (!set.contains(tn.hash())) {
                    priorityQueue.offer(tn);
                    set.add(tn.hash());
                }
            }
        }
        return ans;
    }
}

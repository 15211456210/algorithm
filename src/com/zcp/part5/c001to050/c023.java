package com.zcp.part5.c001to050;

import com.zcp.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ZCP
 * @title: c023
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/merge-k-sorted-lists/submissions/
 * @date 2022/8/25 15:40
 */
public class c023 {

    public static class MyComparator implements Comparator<ListNode> {

        /* (non-Javadoc)
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }


    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        }

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<ListNode>(new MyComparator());

        for (ListNode listNode : lists) {
            if (listNode != null) {
                priorityQueue.add(listNode);
            }

        }
        if (priorityQueue.isEmpty()) {
            return null;
        }
        ListNode head = priorityQueue.poll();
        ListNode cur = head;
        if (head.next != null) {
            priorityQueue.add(head.next);
        }
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
            cur.next = node;
            cur = cur.next;
        }
        return head;
    }
}

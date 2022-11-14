package com.zcp.part5.c051to100;

import com.zcp.common.ListNode;

import java.util.HashMap;

/**
 * @author ZCP
 * @title: c061
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/rotate-list/
 * @date 2022/8/29 10:28
 */
public class c061 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        HashMap<ListNode, ListNode> parent = new HashMap<>();
        ListNode c = head;
        ListNode pre = null;
        while (c != null) {
            parent.put(c, pre);
            pre = c;
            c = c.next;
        }
        ListNode tail = pre;
        k = k % parent.size();
        for (int i = 0; i < k; i++) {
            tail.next = head;
            parent.put(head,tail);
            head = tail;
            ListNode newTail = parent.get(tail);
            newTail.next = null;
            parent.put(tail,null);
            tail = newTail;
        }
        return head;
    }
}

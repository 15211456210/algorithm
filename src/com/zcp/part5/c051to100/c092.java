package com.zcp.part5.c051to100;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: c092
 * @projectName algorithm
 * @description: https://leetcode.com/problems/reverse-linked-list-ii/submissions/
 * @date 2022/8/31 16:49
 */
public class c092 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode h = head;
        ListNode l1 = null, l2 = head, r1 = null;
        int cnt = 0;
        while (h != null) {
            cnt++;
            if (cnt == left - 1) {
                l1 = h;
            }
            if (cnt == left) {
                l2 = h;
            }
            if (cnt == right) {
                r1 = h;
            }
            h = h.next;
        }
        ListNode cT = r1.next;
        ListNode cH = reverse(l2, right - left + 1);

        if (left != 1) {
            l1.next = cH;
        }
        l2.next = cT;

        return left == 1 ? cH : head;

    }

    public ListNode reverse(ListNode node, int len) {
        if (len == 1) {
            return node;
        }
        ListNode pre = null;

        ListNode cur = node;

        ListNode next = node.next;

        ListNode temp;
        for (int i = 0; i < len; i++) {
            temp = next == null ? null : next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            next = temp;
        }
        return pre;
    }
}

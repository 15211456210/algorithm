package com.zcp.part5.c201to250;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: C206
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/reverse-linked-list/submissions/
 * @date 2022/9/16 9:16
 */
public class C206 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }


        return reverse(head, null);

    }

    public ListNode reverse(ListNode head, ListNode pre) {
        ListNode next = head.next;
        head.next = pre;
        if (next == null) {
            return head;
        }
        return reverse(next, head);
    }
}

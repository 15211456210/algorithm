package com.zcp.part5.c101to150;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: C142
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/linked-list-cycle-ii/submissions/
 * @date 2022/9/7 10:17
 */
public class C142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        for (; ; ) {
            if (head == slow) {
                return head;
            }
            head = head.next;
            slow = slow.next;
        }
    }
}

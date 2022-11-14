package com.zcp.part5.c001to050;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: c021
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/merge-two-sorted-lists/submissions/
 * @date 2022/8/25 15:38
 */
public class c021 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        ListNode r = null;
        ListNode head = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (r == null) {
                    r = new ListNode(l1.val);
                    head = r;
                } else {
                    r.next = new ListNode(l1.val);
                    r = r.next;
                }
                l1 = l1.next;
            } else {
                if (r == null) {
                    r = new ListNode(l2.val);
                    head = r;
                } else {
                    r.next = new ListNode(l2.val);
                    r = r.next;
                }
                l2 = l2.next;
            }
//            r = r.next;
        }
        if (l1 == null) {
            r.next = l2;
        } else {
            r.next = l1;
        }
        return head;
    }
}

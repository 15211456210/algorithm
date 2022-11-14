package com.zcp.part5.c151to200;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: C160
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/intersection-of-two-linked-lists/submissions/
 * @date 2022/9/8 15:53
 */
public class C160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0;
        int len2 = 0;
        ListNode hA = headA;
        ListNode hB = headB;
        while (hA.next != null) {
            len1++;
            hA = hA.next;
        }
        while (hB.next != null) {
            len2++;
            hB = hB.next;
        }

        if (hA != hB) {
            return null;
        }
        ListNode longNode = len1 > len2 ? headA : headB;

        ListNode shortNode = longNode == headA ? headB : headA;


        for (int i = Math.max(len1, len2) - Math.min(len1, len2); i > 0; i--) {
            longNode = longNode.next;
        }

        while (longNode != shortNode) {
            longNode = longNode.next;
            shortNode = shortNode.next;
        }

        return longNode;
    }
}

package com.zcp.part5.c101to150;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: C148
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/sort-list/submissions/
 * @date 2022/9/7 10:55
 */
public class C148 {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head;
        ListNode preSlow = null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            preSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        preSlow.next = null;//中间切断
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {
        if (left == right) {
            return left;
        }
        ListNode head = left.val < right.val ? left : right;
        ListNode ret = head;
        left = head == left ? left.next : left;
        right = head == right ? right.next : right;
        while (left != null && right != null) {
            head.next = left.val < right.val ? left : right;
            head = head.next;
            left = head == left ? left.next : left;
            right = head == right ? right.next : right;
        }
        if (left != null) {
            head.next = left;
        }
        if (right != null) {
            head.next = right;
        }
        return ret;
    }
}

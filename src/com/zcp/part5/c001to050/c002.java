package com.zcp.part5.c001to050;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: c002
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/add-two-numbers/submissions/
 * @date 2022/8/25 11:27
 */
public class c002 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int f = 0;
        ListNode pre = null;
        ListNode head = null;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int cnum = v1 + v2 + f;
            f = cnum / 10;
            int v = cnum % 10;
            ListNode listNode = new ListNode(v);
            if (pre == null) {
                pre = listNode;
                head = listNode;
            } else {
                pre.next = listNode;
                pre = pre.next;
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (f != 0) {
            pre.next = new ListNode(1);
        }

        return head;
    }
}

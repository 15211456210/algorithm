package com.zcp.part5.c201to250;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: C203
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/remove-linked-list-elements/
 * @date 2022/9/16 8:45
 */
public class C203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null && cur.val == val) {
            cur = cur.next;
        }

        head = cur;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;


    }
}

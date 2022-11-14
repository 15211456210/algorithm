package com.zcp.part5.c001to050;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: c024
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/swap-nodes-in-pairs/submissions/
 * @date 2022/8/25 15:41
 */
public class c024 {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}

package com.zcp.part5.c051to100;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: c083
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/remove-duplicates-from-sorted-list/submissions/
 * @date 2022/8/30 18:15
 */
public class c083 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode l = head;
        ListNode r = head.next;
        while (r != null) {
            if (l.val == r.val) {
                l.next = r.next;
                r.next = null;
            } else {
                l = r;
            }
            r = l.next;
        }
        return head;
    }
}

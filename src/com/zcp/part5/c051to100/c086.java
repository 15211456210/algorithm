package com.zcp.part5.c051to100;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: c086
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/partition-list/submissions/
 * @date 2022/8/30 18:17
 */
public class c086 {

    public ListNode partition(ListNode head, int x) {
        ListNode slow = null;
        ListNode fast = head;
        ListNode preFast = null;

        while (fast != null) {
            if (fast.val >= x) {
                preFast = fast;
                fast = fast.next;
            } else {
                if (slow == preFast) {
                    preFast = fast;
                    slow = fast;

                }else {
                    if (preFast == null) {
                        preFast = fast;
                    } else {
                        preFast.next = fast.next;
                    }
                    if (slow == null) {
                        if (head != fast) {
                            fast.next = head;
                            head = fast;
                            slow = head;
                        } else {
                            slow = head;
                        }
                    } else {
                        fast.next = slow.next;
                        slow.next = fast;
                        slow = fast;
                    }
                }

                fast = preFast.next;
            }
        }

        return head;
    }
}

package com.zcp.part5.c101to150;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: C141
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/linked-list-cycle/submissions/
 * @date 2022/9/7 10:17
 */
public class C141 {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            if (fast.next == null || fast.next.next == null) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;


    }
}

package com.zcp.part5.c051to100;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: c082
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/submissions/
 * @date 2022/8/30 17:01
 */
public class c082 {

    public ListNode deleteDuplicates(ListNode head) {

        ListNode h = head;
        int dc = 0;
        while ((dc = dupCnt(h)) > 1) {
            for (int i = 0; i < dc; i++) {
                h = h.next;
            }
        }
        if (h == null) {
            return null;
        }
        head = h;
        ListNode next = h.next;

        while (next != null) {
            if ((dc = dupCnt(next)) <= 1) {
                h.next = next;
                h = h.next;
                next = next.next;
            } else {
                for (int i = 0; i < dc; i++) {
                    next = next.next;
                }
            }

        }
        h.next = next;

        return head;
    }

    public int dupCnt(ListNode node) {
        if (node == null) {
            return 0;
        }
        int cnt = 1;
        while (node.next != null && node.val == node.next.val) {
            cnt++;
            node = node.next;
        }
        return cnt;
    }
}

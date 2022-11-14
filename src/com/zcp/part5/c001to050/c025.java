package com.zcp.part5.c001to050;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: c025
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/reverse-nodes-in-k-group/
 * @date 2022/8/25 15:41
 */
public class c025 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }

        ListNode afterKNode = getAfterKNode(head, k);
        if (afterKNode == null) {
            return head;
        }
        ListNode cur = head;
        ListNode next = reverseK(cur, k);
        while (next != null) {
            ListNode afterK = getAfterKNode(next, k);
            if (afterK == null) {
                break;
            }
            if (afterK != null) {
                cur.next = afterK;
                cur = next;
                next = reverseK(next, k);
            }
        }
        return afterKNode;
    }

    /**
     * @param cur 当前节点
     * @param k   往后数第K个
     * @return 当前节点往后数第K个节点
     */
    public ListNode getAfterKNode(ListNode cur, int k) {
        while (--k > 0 && cur != null) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 反转 head 后 k个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseK(ListNode head, int k) {
        ListNode pre = null, next = null;
        ListNode cur = head;
        while (k-- > 0) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = cur;
        return cur;
    }
}

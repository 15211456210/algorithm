package com.zcp.part5.c301to350;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: C328
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/odd-even-linked-list/description/
 * @date 2022/10/15 16:59
 */
public class C328 {

    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null){
            return head;
        }
        ListNode oddHead = null, oddTail = null;
        ListNode evenHead = null, evenTail = null;

        int index = 1;
        while (head != null) {
            if (index % 2 == 1) {
                if (oddHead == null) {
                    oddHead = head;
                    oddTail = head;
                } else {
                    oddTail.next = head;
                    oddTail = oddTail.next;
                }
            } else {
                if (evenHead == null) {
                    evenHead = head;
                    evenTail = head;
                } else {
                    evenTail.next = head;
                    evenTail = oddTail.next;
                }
            }
            index++;
            head = head.next;
        }
        evenTail.next = null;
        oddTail.next = evenHead;
        return oddHead;
    }
}

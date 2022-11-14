package com.zcp.part5.c001to050;

import com.zcp.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c019
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/remove-nth-node-from-end-of-list/submissions/
 * @date 2022/8/25 15:37
 */
public class c019 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();

        while (head.next != null) {
            list.add(head);
            head = head.next;
        }
        list.add(head);
        if (list.size() == 1) {
            return null;
        }
        boolean isHead = n == list.size();
        ListNode lastN = list.get(list.size() - n);
        if (isHead) {
            lastN.next = null;
            return list.get(1);
        } else {
            list.get(list.size() - n - 1).next = lastN.next;
        }
        return list.get(0);
    }
}

package com.zcp.part5.c101to150;

import com.zcp.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C143
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/reorder-list/submissions/
 * @date 2022/9/7 10:21
 */
public class C143 {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }

        int l = 0;
        int r = list.size() - 1;
        ListNode pre = null;
        while (l < r) {
            if (pre != null) {
                pre.next = list.get(l);
            }
            list.get(l).next = list.get(r);
            pre = list.get(r);
            pre.next = null;
            l++;
            r--;
        }
        if (l == r) {
            pre.next = list.get(l);
            pre.next.next = null;
        }

    }
}

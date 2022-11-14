package com.zcp.part5.c201to250;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: C237
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/delete-node-in-a-linked-list/submissions/
 * @date 2022/9/21 17:21
 */
public class C237 {

    public void deleteNode(ListNode node) {
        while (node.next != null) {
            node.val = node.next.val;
            if (node.next.next == null) {
                node.next = null;
                break;
            }
            node = node.next;
        }
    }


}

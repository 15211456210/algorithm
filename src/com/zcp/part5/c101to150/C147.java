package com.zcp.part5.c101to150;

import com.zcp.common.ListNode;

/**
 * @author ZCP
 * @title: C147
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/insertion-sort-list/submissions/
 * @date 2022/9/7 10:26
 */
public class C147 {

    public ListNode insertionSortList(ListNode head) {
        ListNode cur = head;
        ListNode preLast = null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            ListNode it = head;
            ListNode itPre = null;
            while (it != null && it.val <= cur.val && it != cur) {
                itPre = it;
                it = it.next;
            }
            // 插入节点
            if (itPre == null){
                cur.next = head;
                head = cur;
            }else {
                cur.next = it;
                itPre.next = cur;
            }
            if (preLast == null || preLast.val <= cur.val){
                preLast = cur;
            }
            preLast.next = next;
            cur = next;
        }
        return head;
    }
}

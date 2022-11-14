package com.zcp.part5.c201to250;

import com.zcp.common.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C234
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/palindrome-linked-list/submissions/
 * @date 2022/9/21 17:09
 */
public class C234 {
    public boolean isPalindrome(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int l = 0;
        int r = list.size() - 1;
        while (l < r && list.get(l).val == list.get(r).val) {
            l++;
            r--;
        }
        return l >= r;
    }
}

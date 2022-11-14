package com.zcp.common;

/**
 * @author ZCP
 * @title: ListNode
 * @projectName algorithm
 * @description: TODO
 * @date 2022/8/25 11:29
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

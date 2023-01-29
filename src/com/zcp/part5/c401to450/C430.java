package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C430
 * @projectName algorithm
 * @description: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 * @date 2023/1/26 21:10
 */
public class C430 {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        flat(head);
        return head;
    }

    private Node flat(Node head) {
        while (head.next != null) {
            Node child = head.child;
            Node next = head.next;
            if (child != null) {
                Node childLast = flat(child);
                head.child = null;
                head.next = child;
                child.prev = head;
                childLast.next = next;
                if (next != null) {
                    next.prev = childLast;
                }
                head = next;
            } else {
                head = head.next;
            }
        }
        Node child = head.child;
        if (child != null) {
            head.child = null;
            head.next = child;
            child.prev = head;
            return flat(child);
        }
        return head;
    }

}

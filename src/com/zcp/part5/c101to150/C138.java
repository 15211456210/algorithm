package com.zcp.part5.c101to150;


import java.util.HashMap;

/**
 * @author ZCP
 * @title: C138
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/copy-list-with-random-pointer/submissions/
 * @date 2022/9/6 17:02
 */
public class C138 {


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();

        Node cur = head;

        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        map.forEach((k, v) -> {
            v.next = map.get(k.next);
            v.random = map.get(k.random);
        });

        return map.get(head);

    }
}

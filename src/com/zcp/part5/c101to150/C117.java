package com.zcp.part5.c101to150;

import com.zcp.common.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ZCP
 * @title: C117
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/submissions/
 * @date 2022/9/1 16:26
 */
public class C117 {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();

        queue.offer(root);
        Node pre;
        while (!queue.isEmpty()) {
            int size = queue.size();
            pre = null;
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }

                if (pre == null) {
                    pre = poll;
                } else {
                    pre.next = poll;
                    pre = poll;
                }
            }

        }

        return root;
    }
}

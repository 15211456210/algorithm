package com.zcp.part5.c101to150;

import com.zcp.common.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ZCP
 * @title: C116
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/submissions/
 * @date 2022/9/1 16:24
 */
public class C116 {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            for (int i = 0; i < size - 1; i++) {
                Node next = queue.poll();
                node.next = next;
                node = next;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }
}

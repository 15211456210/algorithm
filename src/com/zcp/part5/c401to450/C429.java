package com.zcp.part5.c401to450;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ZCP
 * @title: C429
 * @projectName algorithm
 * @description: https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 * @date 2023/1/26 20:35
 */
public class C429 {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            ans.add(list);
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                list.add(poll.val);
                if (poll.children != null) {
                    for (Node node : poll.children) {
                        queue.offer(node);
                    }
                }
            }
        }
        return ans;
    }
}

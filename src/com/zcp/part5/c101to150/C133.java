package com.zcp.part5.c101to150;


import java.util.*;

/**
 * @author ZCP
 * @title: C133
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/clone-graph/submissions/
 * @date 2022/9/6 16:27
 */
public class C133 {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {

        if (node == null) {
            return null;
        }


        HashMap<Node, Node> map = new HashMap<>();

        LinkedList<Node> queue = new LinkedList<>();

        queue.offer(node);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            map.put(poll, new Node(poll.val));
            poll.neighbors.forEach(neighbor -> {
                if (map.containsKey(neighbor)) {
                    return;
                }
                queue.offer(neighbor);
            });
        }

        map.forEach((k, v) -> {
            k.neighbors.forEach(neighbor -> {
                v.neighbors.add(map.get(neighbor));
            });
        });

        return map.get(node);

    }
}

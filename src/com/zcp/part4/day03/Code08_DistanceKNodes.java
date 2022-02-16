package com.zcp.part4.day03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 014.与 target的距离是K的所有节点
 * #预处理#bfs
 * 给定三个参数:二叉树的头节点head,树上某个节点 target,正数K 从 target开始，可以向上走或者向下走 返回与 target的距离是K的所有节点
 */
public class Code08_DistanceKNodes {


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }


    /**
     * 思路：
     * 预处理，宽度优先遍历
     *
     * @param root
     * @param target
     * @param K
     * @return
     */
    public static List<Node> distanceKNodes2(Node root, Node target, int K) {
        HashMap<Node, Node> parentMap = new HashMap<>();
        createParentMap2(root, parentMap);

        ArrayList<Node> ans = new ArrayList<>();
        //宽度优先遍历
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int deep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                if(parentMap.containsKey(poll) && !visited.contains(parentMap.get(poll))){
                    queue.offer(parentMap.get(poll));
                    visited.add(parentMap.get(poll));
                }
                if(poll.left!=null && !visited.contains(poll.left)){
                    queue.offer(poll.left);
                    visited.add(poll.left);
                }
                if(poll.right!=null && !visited.contains(poll.right)){
                    queue.offer(poll.right);
                    visited.add(poll.right);
                }
            }
            if(++deep == K){
                while (!queue.isEmpty()){
                    ans.add(queue.poll());
                }
            }
        }
        return ans;
    }

    /**
     * 遍历二叉树，将父节点保存再map中
     *
     * @param root
     * @param parentMap
     */
    private static void createParentMap2(Node root, HashMap<Node, Node> parentMap) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parentMap.put(root.left, root);
            createParentMap2(root.left,parentMap);
        }
        if (root.right != null) {
            parentMap.put(root.right, root);
            createParentMap2(root.right,parentMap);
        }
    }


    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        HashMap<Node, Node> parents = new HashMap<>();
        parents.put(root, null);
        createParentMap(root, parents);
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int curLevel = 0;
        List<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                if (curLevel == K) {
                    ans.add(cur);
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    visited.add(cur.left);
                    queue.offer(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    visited.add(cur.right);
                    queue.offer(cur.right);
                }
                if (parents.get(cur) != null && !visited.contains(parents.get(cur))) {
                    visited.add(parents.get(cur));
                    queue.offer(parents.get(cur));
                }
            }
            curLevel++;
            if (curLevel > K) {
                break;
            }
        }
        return ans;
    }

    public static void createParentMap(Node cur, HashMap<Node, Node> parents) {
        if (cur == null) {
            return;
        }
        if (cur.left != null) {
            parents.put(cur.left, cur);
            createParentMap(cur.left, parents);
        }
        if (cur.right != null) {
            parents.put(cur.right, cur);
            createParentMap(cur.right, parents);
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

        List<Node> ans2 = distanceKNodes2(root, target, K);
        for (Node o1 : ans2) {
            System.out.println(o1.value);
        }


    }

}

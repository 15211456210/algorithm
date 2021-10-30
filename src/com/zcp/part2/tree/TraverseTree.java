package com.zcp.part2.tree;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/6 15:57
 * @description：非递归实现二叉树的前、中、后序遍历
 * @version:
 */
public class TraverseTree {

    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 非递归实现二叉树先序遍历
     *
     * @param root
     */
    public static void pre(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public static void mid(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashMap<Node, Integer> map = new HashMap<>();
        stack.push(root);
        map.put(root, 0);
        while (!stack.isEmpty()) {
            Node peek = stack.peek();
            if (peek.left != null && map.get(peek) < 1) {
                map.put(peek, map.get(peek) + 1);
                stack.push(peek.left);
                map.put(peek.left, 0);
                continue;
            }
            Node pop = stack.pop();
            map.remove(pop);
            System.out.print(pop.val + " ");
            if (pop.right != null) {
                stack.push(pop.right);
                map.put(pop.right, 0);
            }
        }
    }

    /**
     * 后续遍历
     *
     * @param root
     */
    public static void pos(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            stack2.push(pop);
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }


    }


    public static void main(String[] args) {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(2, null, null);
        Node node3 = new Node(3, null, null);
        Node node4 = new Node(4, null, null);
        Node node5 = new Node(5, null, null);
        Node node6 = new Node(6, null, null);
        Node node7 = new Node(7, null, null);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        pre(node1);//1 2 4 5 3 6 7
        System.out.println();
        mid(node1);//4 2 5 1 6 3 7
        System.out.println();
        pos(node1);//4 5 2 6 7 3 1

    }


}

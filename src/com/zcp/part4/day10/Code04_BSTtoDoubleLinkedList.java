package com.zcp.part4.day10;

// 本题测试链接 : https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
public class Code04_BSTtoDoubleLinkedList {

    // 提交时不要提交这个类
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public class Info2 {
        Node head;
        Node tail;

        public Info2() {
        }

        public Info2(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    /**
     * 思路：
     * 二叉树递归套路
     *
     * @param root
     * @return
     */
    public Node treeToDoublyList2(Node root) {
        if (root == null) {
            return null;
        }

        Info2 info2 = process2(root);
        info2.head.left = info2.tail;
        info2.tail.right = info2.head;
        return info2.head;
    }

    /**
     * 返回 以root为头节点的二叉树--》转化成链表形式后头节点和尾节点信息（并且内部已经按照大小连接好了）
     *
     * @param root
     * @return
     */
    public Info2 process2(Node root) {
        if (root == null) {
            return null;
        }

        Info2 left = process2(root.left);
        Info2 right = process2(root.right);
        Info2 ans = new Info2();
        if (left != null && right != null) {
            ans.head = left.head;
            left.tail.right = root;
            root.left = left.tail;

            ans.tail = right.tail;
            right.head.left = root;
            root.right = right.head;

        } else if (left == null && right != null) {
            ans.head = root;
            root.right = right.head;
            right.head.left = root;

            ans.tail = right.tail;
        } else if (left != null && right == null) {
            ans.head = left.head;
            left.tail.right = root;
            root.left = left.tail;

            ans.tail = root;
        }else {
            //left == null && right == null
            ans.head = root;
            ans.tail = root;
        }
        return ans;
    }


    // 提交下面的代码
    public static Node treeToDoublyList(Node head) {
        if (head == null) {
            return null;
        }
        Info allInfo = process(head);
        allInfo.end.right = allInfo.start;
        allInfo.start.left = allInfo.end;
        return allInfo.start;
    }

    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(null, null);
        }
        Info lInfo = process(X.left);
        Info rInfo = process(X.right);
        if (lInfo.end != null) {
            lInfo.end.right = X;
        }
        X.left = lInfo.end;
        X.right = rInfo.start;
        if (rInfo.start != null) {
            rInfo.start.left = X;
        }
        // 整体链表的头    lInfo.start != null ? lInfo.start : X
        // 整体链表的尾    rInfo.end != null ? rInfo.end : X
        return new Info(lInfo.start != null ? lInfo.start : X, rInfo.end != null ? rInfo.end : X);
    }

}
package com.zcp.part4.day14;

public class Code04_CompleteTreeNodeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    /**
     * 思路：
     *
     * @param head
     * @return
     */
    public static int nodeNum2(Node head) {
        if (head == null) {
            return 0;
        }
        return process2(head);
    }

    /**
     * 获取以 head为根节点的完全二叉树的节点数量
     *
     * @param head
     * @return
     */
    public static int process2(Node head) {
        if (head == null) {
            return 0;
        }
        int nodeNum = 1;
        //当前节点为根节点的树最大高度
        int maxLevel = getMaxLevel(head);
        //当前节点右子树的最大高度
        int rightMaxLevel = getMaxLevel(head.right);
        if (maxLevel - 2 == rightMaxLevel) {
            //说明 右树是满二叉树 且高度为 rightMaxLevel
            //左树为满二叉树
            nodeNum += Math.pow(2, rightMaxLevel) - 1;//右树节点个数
            nodeNum += process2(head.left);//左数节点个数
        } else {
            //maxLevel - 1 == rightMaxLevel
            //左树是满二叉树，且高度为 maxLevel-1
            //右树是完全二叉树
            nodeNum += Math.pow(2, maxLevel - 1) - 1;//右树节点个数
            nodeNum += process2(head.right);
        }
        return nodeNum;
    }

    /**
     * 获取树的最大高度
     *
     * @param head
     * @return
     */
    private static int getMaxLevel(Node head) {
        if (head == null) {
            return 0;
        }
        int level = 1;
        while (head.left != null) {
            level++;
            head = head.left;
        }
        return level;
    }


    // 请保证head为头的树，是完全二叉树
    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    // 当前来到node节点，node节点在level层，总层数是h
    // 返回node为头的子树(必是完全二叉树)，有多少个节点
    public static int bs(Node node, int Level, int h) {
        if (Level == h) {
            return 1;
        }
        if (mostLeftLevel(node.right, Level + 1) == h) {
            return (1 << (h - Level)) + bs(node.right, Level + 1, h);
        } else {
            return (1 << (h - Level - 1)) + bs(node.left, Level + 1, h);
        }
    }

    // 如果node在第level层，
    // 求以node为头的子树，最大深度是多少
    // node为头的子树，一定是完全二叉树
    public static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));
        System.out.println(nodeNum2(head));

    }

}

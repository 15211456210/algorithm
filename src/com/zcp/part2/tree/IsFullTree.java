package com.zcp.part2.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/10 10:23
 * @description：是否是满二叉树
 * @version:
 */
public class IsFullTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static boolean solution(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isFullTree;
    }


    public static class Info {
        boolean isFullTree;
        int height;

        public Info(boolean isFullTree, int height) {
            this.isFullTree = isFullTree;
            this.height = height;
        }
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFullTree = false;
        if (leftInfo.height == rightInfo.height && leftInfo.isFullTree && rightInfo.isFullTree) {
            isFullTree = true;
        }
        return new Info(isFullTree, height);
    }

    public static void main(String[] args) {
        int range = 10;
        int size = 100;
        int testTime = 1000000;
        int count = 0;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            TreeNode treeNode = generateRandomTree(range, size);
            boolean r1 = solution(treeNode);
            boolean r2 = solution2(treeNode);
            if (r1 != r2) {
                System.out.println("出错了");
                break;
            }
            if (r1 == true) {
                count++;
            }
        }
        System.out.println("测试结束");
        System.out.println("count:" + count);
    }
    
    /**
     * 对照函数
     *
     * @param head
     * @return
     */
    public static boolean solution2(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        int deep = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            deep++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                count++;
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return count == (Math.pow(2, deep) - 1);
    }


    /**
     * 根据 range szie 生成随机二叉树
     *
     * @param range
     * @param size
     * @return
     */
    public static TreeNode generateRandomTree(int range, int size) {
        int sz = (int) (Math.random() * size) + 1;
        List<TreeNode> list = new ArrayList<>();
        TreeNode root = new TreeNode((int) (Math.random() * range));
        list.add(root);
        for (int i = 0; i < sz; i++) {
            TreeNode node = new TreeNode((int) (Math.random() * range));
            TreeNode parentNode = null;
            if (Math.random() < 0.5) {
                do {
                    parentNode = list.get((int) (Math.random() * list.size()));
                } while (parentNode.left != null);
                parentNode.left = node;
            } else {
                do {
                    parentNode = list.get((int) (Math.random() * list.size()));
                } while (parentNode.right != null);
                parentNode.right = node;
            }
            list.add(node);
        }
        return root;
    }

}

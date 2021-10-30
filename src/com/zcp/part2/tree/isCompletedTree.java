package com.zcp.part2.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/10 9:38
 * @description：判断是否是完全二叉树
 * @version:
 */
public class isCompletedTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static boolean find = false;

    public static boolean solution(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left == null && poll.right != null) {
                return false;
            }
            if (find && (poll.left != null || poll.right != null)) {
                return false;
            }
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
            if (poll.left == null || poll.right == null) {
                find = true;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        int range = 10;
        int size = 100;
        int testTime = 1000000;
        int count = 0;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            find = false;
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
        ArrayList<TreeNode> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            list.add(poll);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            if (node.left != null && (i * 2 + 1 >= list.size() || node.left != list.get(i * 2 + 1))) {
                return false;
            }
            if (node.right != null && (i * 2 + 2 >= list.size() || node.right != list.get(i * 2 + 2))) {
                return false;
            }
        }
        return true;
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
        TreeNode root = new TreeNode((int) (Math.random() * range), null, null);
        list.add(root);
        for (int i = 0; i < sz; i++) {
            TreeNode node = new TreeNode((int) (Math.random() * range), null, null);
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

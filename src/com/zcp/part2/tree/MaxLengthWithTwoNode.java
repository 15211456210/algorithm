package com.zcp.part2.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/10 13:58
 * @description：求二叉树两个节点的最大距离
 * @version:
 */
public class MaxLengthWithTwoNode {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 思路：
     * p1：与本节点无关
     * 返回左右子树最大的距离
     * p2：与本节点有关
     * 左树高度+右树高度+1
     *
     * @param head
     * @return
     */
    public static int solution(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxLength;
    }

    public static class Info {
        int maxLength;
        int height;

        public Info(int maxLength, int height) {
            this.maxLength = maxLength;
            this.height = height;
        }
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int maxLength = 1;
        int height = 1;

        height = Math.max(leftInfo.height, rightInfo.height) + 1;
        maxLength = Math.max(Math.max(leftInfo.maxLength, rightInfo.maxLength), leftInfo.height + rightInfo.height + 1);
        return new Info(maxLength,height);
    }


    public static void main(String[] args) {
        int range = 10;
        int size = 100;
        int testTime = 1000000;
        int count = 0;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            TreeNode treeNode = generateRandomTree(range, size);
            int r1 = solution(treeNode);
            if (r1 > 14) {
                count++;
            }
        }
        System.out.println("测试结束");
        System.out.println("count:" + count);
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

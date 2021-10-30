package com.zcp.part2.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/10 10:44
 * @description：二叉树中最大的二叉搜索子树的大小 https://leetcode-cn.com/problems/largest-bst-subtree/
 * @version:
 */
public class MaxBSTSzie {

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
     * Info: 节点总数、最大搜索子树大小，最大值，最小值
     * 情况一：与当前节点无关
     * 最大搜索子树大小=左右子树Info搜索子树大小的较大值
     * 情况二：与当前节点有关
     * 左右子树各自整体都是搜索二叉树、左树最大值<当前节点值、右树最小值>当前节点值
     * 最大搜索子树大小=左树大小+右树大小+1
     *
     * @param head
     * @return
     */
    public static int solution(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBSTSize;
    }

    public static class Info {
        int totalSize;
        int maxBSTSize;
        int max;
        int min;

        public Info(int totalSize, int maxBSTSize, int max, int min) {
            this.totalSize = totalSize;
            this.maxBSTSize = maxBSTSize;
            this.max = max;
            this.min = min;
        }
    }


    public static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        //P1与当前节点无关
        int min = node.val;
        int max = node.val;
        int totalSize = 1;
        int maxBSTSize = 1;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            totalSize += leftInfo.totalSize;
            maxBSTSize = Math.max(maxBSTSize, leftInfo.maxBSTSize);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            totalSize += rightInfo.totalSize;
            maxBSTSize = Math.max(maxBSTSize, rightInfo.maxBSTSize);
        }

        //P2与当前节点有关
        if (leftInfo != null && rightInfo == null) {
            if (leftInfo.totalSize == leftInfo.maxBSTSize && leftInfo.max < node.val) {
                maxBSTSize = leftInfo.maxBSTSize + 1;
            }
        }
        if(leftInfo == null && rightInfo != null){
            if (rightInfo.totalSize == rightInfo.maxBSTSize && rightInfo.min > node.val) {
                maxBSTSize = rightInfo.maxBSTSize + 1;
            }
        }
        if(leftInfo != null && rightInfo != null){
            if(leftInfo.totalSize == leftInfo.maxBSTSize && rightInfo.totalSize == rightInfo.maxBSTSize){
                if(leftInfo.max < node.val && rightInfo.min > node.val){
                    maxBSTSize = leftInfo.maxBSTSize + rightInfo.maxBSTSize + 1;
                }
            }
        }
        return new Info(totalSize, maxBSTSize, max, min);
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
            if (r1 > 5) {
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

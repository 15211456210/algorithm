package com.zcp.part2.tree;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/10 10:13
 * @description：判断是否是平衡二叉树 https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
 * @version:
 */
public class IsBalancedTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static boolean solution(TreeNode head){
        if(head == null){
            return true;
        }
        return process(head).isBalanced;

    }

    public static class Info{
        boolean isBalanced;
        int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static Info process(TreeNode node){
        if (node == null){
            return new Info(true,0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        boolean isBalanced = false;
        if (leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height-rightInfo.height) < 2){
            isBalanced = true;
        }
        return new Info(isBalanced,height);
    }

}

package com.zcp.part2.tree;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/11 13:34
 * @description：给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * @version:
 */
public class LowestCommonAncestor {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Info {
        boolean hasP;
        boolean hasQ;
        TreeNode ans;

        public Info(boolean hasP, boolean hasQ, TreeNode ans) {
            this.hasP = hasP;
            this.hasQ = hasQ;
            this.ans = ans;
        }
    }

    public static TreeNode solution(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return process(root,p,q).ans;
    }

    public static Info process(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(node.left, p, q);
        Info rightInfo = process(node.right, p, q);
        boolean hasP = leftInfo.hasP || rightInfo.hasP || node == p;
        boolean hasQ = leftInfo.hasQ || rightInfo.hasQ || node == q;
        TreeNode ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if(hasP && hasQ){
                ans = node;
            }
        }
        return new Info(hasP,hasQ,ans);
    }

}

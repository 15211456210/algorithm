package com.zcp.part5.c201to250;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * @date 2022/9/21 17:18
 */
public class C236 {

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

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return process(root, p, q).ans;
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
            if (hasP && hasQ) {
                ans = node;
            }
        }
        return new Info(hasP, hasQ, ans);
    }
}

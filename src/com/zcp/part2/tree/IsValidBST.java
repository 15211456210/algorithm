package com.zcp.part2.tree;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/10 9:21
 * @description：判断是否是搜索二叉树 https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @version:
 */
public class IsValidBST {
    public class TreeNode {
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

    public static class Info {
        boolean isValidBST;
        int min;
        int max;

        public Info(boolean isValidBST, int min, int max) {
            this.isValidBST = isValidBST;
            this.min = min;
            this.max = max;
        }
    }

    public static boolean solution(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isValidBST;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int min = node.val;
        int max = node.val;
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
        }
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
        }
        boolean isValidBST = true;
        if (leftInfo != null) {
            if (!leftInfo.isValidBST) {
                isValidBST = false;
            }
            if (leftInfo.max >= node.val) {
                isValidBST = false;
            }
        }
        if (rightInfo != null) {
            if (!rightInfo.isValidBST) {
                isValidBST = false;
            }
            if (rightInfo.min <= node.val) {
                isValidBST = false;
            }
        }
        return new Info(isValidBST, min, max);
    }


}

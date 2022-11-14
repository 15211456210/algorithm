package com.zcp.part5.c051to100;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: c098
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/validate-binary-search-tree/submissions/
 * @date 2022/9/1 15:50
 */
public class c098 {

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

    public static boolean isValidBST(TreeNode head) {
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

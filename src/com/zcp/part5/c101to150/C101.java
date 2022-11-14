package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: c101
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/symmetric-tree/
 * @date 2022/9/1 15:53
 */
public class C101 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root, root);
    }

    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if ((t1 == null && t2 != null) || (t2 == null && t1 != null) || t1.val != t2.val) {
            return false;
        }
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}

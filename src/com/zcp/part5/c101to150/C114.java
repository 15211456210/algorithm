package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C114
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/submissions/
 * @date 2022/9/1 16:23
 */
public class C114 {
    TreeNode pre = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (pre != null) {
            pre.left = null;
            pre.right = root;
        }
        TreeNode right = root.right;
        pre = root;
        if (root.left != null) {
            flatten(root.left);
        }
        if (right != null) {
            flatten(right);
        }
    }


}

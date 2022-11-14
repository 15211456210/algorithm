package com.zcp.part5.c201to250;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C226
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/invert-binary-tree/submissions/
 * @date 2022/9/19 11:29
 */
public class C226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        return root;
    }
}

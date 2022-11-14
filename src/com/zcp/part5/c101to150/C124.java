package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C124
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-tree-maximum-path-sum/submissions/
 * @date 2022/9/2 10:28
 */
public class C124 {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        process(root);
        return max;
    }

    public int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = process(root.left);
        int right = process(root.right);
        max = Math.max(max, root.val + Math.max(0, Math.max(left + right, Math.max(right, left))));
        return Math.max(Math.max(left, right) + root.val, root.val);
    }
}

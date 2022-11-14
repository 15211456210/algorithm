package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C104
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/maximum-depth-of-binary-tree/submissions/
 * @date 2022/9/1 15:57
 */
public class C104 {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

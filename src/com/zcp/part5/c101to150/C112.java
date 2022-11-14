package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C112
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/path-sum/
 * @date 2022/9/1 16:21
 */
public class C112 {

    public boolean flag = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        hasPathSum(root, 0, targetSum);
        return flag;
    }

    public void hasPathSum(TreeNode root, int preSum, int targetSum) {
        if (root.left == null && root.right == null && root.val + preSum == targetSum) {
            //如果是叶子节点，且当前节点值+之前节点的值=目标值，设置flag=true
            flag = true;
            return;
        }
        preSum += root.val;
        if (root.left != null) {
            hasPathSum(root.left, preSum, targetSum);
        }
        if (root.right != null) {
            hasPathSum(root.right, preSum, targetSum);
        }
    }
}

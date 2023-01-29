package com.zcp.part5.c401to450;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C404
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/sum-of-left-leaves/description/
 * @date 2022/11/15 15:55
 */
public class C404 {

    int sum;

    public int sumOfLeftLeaves(TreeNode root) {
        process(root, 1);
        return sum;
    }

    private void process(TreeNode node, int flag) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null && flag == 0) {
            sum += node.val;
            return;
        }
        process(node.left, 0);
        process(node.right, 1);
    }
}

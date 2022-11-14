package com.zcp.common;

/**
 * @author ZCP
 * @title: TreeNode
 * @projectName algorithm
 * @description: TODO
 * @date 2022/9/1 14:40
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

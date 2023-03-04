package com.zcp.part5.c401to450;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C450
 * @projectName algorithm
 * @description: https://leetcode.com/problems/delete-node-in-a-bst/
 * @date 2023/2/2 18:34
 */
public class C450 {


    boolean find = false;

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (find) {
            return root;
        }
        if (root.val == key) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }
            TreeNode newRoot = left;
            // 找到左子树最右节点
            while (left.right != null) {
                left = left.right;
            }
            // 将右子树 接到 左子树最右节点 的右子树上面
            left.right = right;
            find = true;
            return newRoot;
        } else {
            root.left = deleteNode(root.left, key);
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
}

package com.zcp.part5.c201to250;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C222
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/count-complete-tree-nodes/submissions/
 * @date 2022/9/18 17:16
 */
public class C222 {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ll = 1;
        int lr = 1;
        int rr = 1;
        TreeNode l = root, r = root, lrn = root.left;
        while (l.left != null) {
            ++ll;
            l = l.left;
        }

        while (r.right != null) {
            ++rr;
            r = r.right;
        }

        while (lrn != null) {
            ++lr;
            lrn = lrn.right;
        }


        if (ll == rr) {
            return (int) (Math.pow(2, ll) - 1);
        }

        if (lr == ll) {
            // 左边是满二叉树
            return (int) (Math.pow(2, ll - 1)) + countNodes(root.right);
        } else {
            // 右边是满二叉树
            return (int) (Math.pow(2, rr - 1)) + countNodes(root.left);
        }


    }

    public static void main(String[] args) {

        TreeNode treeNode1 = new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3, new TreeNode(6), null));

        System.out.println(new C222().countNodes(treeNode1));
    }
}

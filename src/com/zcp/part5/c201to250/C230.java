package com.zcp.part5.c201to250;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C230
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/kth-smallest-element-in-a-bst/submissions/
 * @date 2022/9/19 14:12
 */
public class C230 {


    static int cnt = 0;

    public int kthSmallest(TreeNode root, int k) {
        cnt = k;

        return in(root);
    }


    public int in(TreeNode root) {
        if (root.left != null) {
            int left = in(root.left);
            if (left >= 0) {
                return left;
            }
        }
        cnt--;
        if (cnt == 0) {
            return root.val;
        }

        if (root.right != null) {
            int right = in(root.right);
            if (right >= 0) {
                return right;
            }
        }

        return -1;
    }
}

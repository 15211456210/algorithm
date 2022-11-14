package com.zcp.part5.c051to100;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: c100
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/same-tree/submissions/
 * @date 2022/9/1 15:52
 */
public class c100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (p != null && q == null) || (p.val != q.val)) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

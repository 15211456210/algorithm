package com.zcp.part5.c051to100;

import com.zcp.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c0
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-tree-inorder-traversal/submissions/
 * @date 2022/9/1 14:39
 */
public class c094 {

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode cur = root;
        while (cur != null) {
            TreeNode mostSubRight = cur.left;
            if (mostSubRight != null) {
                while (mostSubRight.right != null && mostSubRight.right != cur) {
                    mostSubRight = mostSubRight.right;
                }
                if (mostSubRight.right != cur) {
                    mostSubRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                //从最右子节点跳上来的情况 mostSubRight.right == cur
                mostSubRight.right = null;
            }
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }
}

package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C144
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-tree-preorder-traversal/submissions/
 * @date 2022/9/7 10:23
 */
public class C144 {

    public List<Integer> preorderTraversal(TreeNode root) {
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
                    list.add(cur.val);
                    cur = cur.left;
                    continue;
                }
                //从最右子节点跳上来的情况 mostSubRight.right == cur
                mostSubRight.right = null;
            } else {
                list.add(cur.val);
            }
            cur = cur.right;
        }
        return list;
    }
}

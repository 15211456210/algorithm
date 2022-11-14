package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C145
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-tree-postorder-traversal/submissions/
 * @date 2022/9/7 10:24
 */
public class C145 {

    public List<Integer> postorderTraversal(TreeNode root) {
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
                addNode(cur.left, list);
            }
            cur = cur.right;
        }
        addNode(root, list);
        return list;
    }

    /**
     * 将cur左子树的最右节点倒序加入
     * 先反转链表，添加元素->list ,再反转回来
     *
     * @param cur
     * @param list
     */
    private void addNode(TreeNode cur, ArrayList<Integer> list) {

        TreeNode pre = null;
        while (cur != null) {
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }

        cur = pre;
        pre = null;
        while (cur != null) {
            list.add(cur.val);
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
    }
}

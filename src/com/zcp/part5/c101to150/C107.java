package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZCP
 * @title: C107
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/submissions/
 * @date 2022/9/1 16:00
 */
public class C107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList list = new LinkedList<List<Integer>>();
        if (root == null) {
            return list;
        }

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List s = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                s.add(cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            list.addFirst(s);
        }
        return list;
    }
}

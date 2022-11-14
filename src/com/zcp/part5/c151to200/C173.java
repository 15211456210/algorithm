package com.zcp.part5.c151to200;

import com.zcp.common.ListNode;
import com.zcp.common.TreeNode;

import java.util.LinkedList;

/**
 * @author ZCP
 * @title: C173
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-search-tree-iterator/submissions/
 * @date 2022/9/9 9:53
 */
public class C173 {

    class BSTIterator {

        LinkedList<TreeNode> list = new LinkedList<>();
        int index = 0;

        public BSTIterator(TreeNode root) {
            // 中序遍历
            in(root, list);
        }

        private void in(TreeNode root, LinkedList<TreeNode> list) {
            if (root == null) {
                return;
            }
            in(root.left, list);
            list.addLast(root);
            in(root.right, list);
        }

        public int next() {
            return list.get(index++).val;
        }

        public boolean hasNext() {
            return index < list.size();
        }
    }

}

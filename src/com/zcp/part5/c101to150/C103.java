package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

import java.util.*;

/**
 * @author ZCP
 * @title: C103
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/submissions/
 * @date 2022/9/1 15:56
 */
public class C103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Stack<Integer> stack = new Stack<Integer>();
        boolean isOrder = true;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (isOrder) {
                    list.add(node.val);
                } else {
                    stack.push(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (!isOrder) {
                while (!stack.isEmpty()) {
                    list.add(stack.pop());
                }
            }
            ans.add(list);
            isOrder = !isOrder;
        }
        return ans;
    }
}

package com.zcp.part5.c151to200;

import com.zcp.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ZCP
 * @title: C199
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-tree-right-side-view/submissions/
 * @date 2022/9/15 14:29
 */
public class C199 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode poll = null;
            for (int i = 0; i < size; i++) {
                poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            int view = poll.val;
            list.add(view);
        }
        return list;
    }
}

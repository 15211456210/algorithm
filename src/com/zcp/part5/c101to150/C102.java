package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ZCP
 * @title: C102
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-tree-level-order-traversal/submissions/
 * @date 2022/9/1 15:54
 */
public class C102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curEnd = root;
        TreeNode nextEnd = root;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            list.add(poll.val);
            if (poll.left != null) {
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nextEnd = poll.right;
            }
            if (poll == curEnd) {
                curEnd = nextEnd;
                lists.add(list);
                list = new ArrayList<>();
            }
        }
        return lists;
    }
}

package com.zcp.part2.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/7 15:59
 * @description：按层遍历二叉树 https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @version:
 */
public class TraverseTreeWithLevel {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static List<List<Integer>> levelOrder(TreeNode root) {
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
            if (poll == curEnd){
                curEnd = nextEnd;
                lists.add(list);
                list = new ArrayList<>();
            }
        }
        return lists;
    }


}

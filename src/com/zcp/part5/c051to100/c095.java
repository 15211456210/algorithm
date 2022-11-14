package com.zcp.part5.c051to100;

import com.zcp.common.TreeNode;

import java.util.*;

/**
 * @author ZCP
 * @title: c095
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/unique-binary-search-trees-ii/submissions/
 * @date 2022/9/1 14:43
 */
public class c095 {

    Set<String> cache = new HashSet<>();
    List<TreeNode> list = new ArrayList<>();
    Set<Integer> treeCache = new HashSet<>();

    public List<TreeNode> generateTrees(int n) {
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        cache.clear();
        list.clear();
        fullArray(arr, 0);

        return list;
    }

    public void fullArray(int[] arr, int i) {
        if (i == arr.length) {
            //
            spanningTree(arr);
            return;
        } else {
            for (int j = i; j < arr.length; j++) {
                swap(arr, i, j);
                fullArray(arr, i + 1);
                swap(arr, i, j);
            }

        }
    }

    private void spanningTree(int[] arr) {
        TreeNode head = new TreeNode(arr[0]);
        TreeNode c;
        for (int i = 1; i < arr.length; i++) {
            c = head;
            while (true) {
                if (c.val > arr[i]) {
                    if (c.left == null) {
                        c.left = new TreeNode(arr[i]);
                        break;
                    } else {
                        c = c.left;
                    }
                } else {
                    if (c.right == null) {
                        c.right = new TreeNode(arr[i]);
                        break;
                    } else {
                        c = c.right;
                    }
                }
            }

        }

        StringBuilder pos = new StringBuilder();
        StringBuilder in = new StringBuilder();


        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);

        // 先序（根左右）
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            pos.append("#" + pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }

        treeCache.clear();
        stack.push(head);
        treeCache.add(head.val);
        // 中序（左根右）
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (treeCache.contains(pop.val) || (pop.left == null && pop.right == null)) {
                in.append("#" + pop.val);
            } else {
                treeCache.add(pop.val);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                stack.push(pop);
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }
        }
        String seq = pos.append(in).toString();
        if (!cache.contains(seq)) {
            cache.add(seq);
            list.add(head);
        }

    }


    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}

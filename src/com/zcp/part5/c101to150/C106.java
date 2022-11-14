package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C106
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/
 * @date 2022/9/1 15:59
 */
public class C106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0
                || postorder.length == 0 || inorder.length != postorder.length) {
            return null;
        }
        int size = inorder.length;
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            indexMap.put(inorder[i], i);
        }
        return process(inorder, postorder, 0, size - 1, 0, size - 1, indexMap);

    }

    public TreeNode process(int[] inorder, int[] postorder, int inl, int inr, int postl, int postr, Map<Integer, Integer> indexMap) {
        if (inl > inr) {
            return null;
        }
        TreeNode head = new TreeNode(postorder[postr]);

        if (inl == inr) {
            return head;
        }

        int headIndex = indexMap.get(head.val);
        int leftNum = headIndex - inl;
        head.left = process(inorder, postorder, inl, inl + leftNum - 1, postl, postl + leftNum - 1, indexMap);
        head.right = process(inorder, postorder, headIndex + 1, inr, postl + leftNum, postr - 1, indexMap);
        return head;
    }
}

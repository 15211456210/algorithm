package com.zcp.part4.day05;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @description: https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @projectName:algorithm
 * @see:com.zcp.part4.day05
 * @author:ZCP
 * @createTime:2022/1/5
 * @version:1.0
 */
public class BuildTree {

    // 不用提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    int maxNodeValue = 3000;

    /**
     * 思路：
     * coding，左右指针 ，桶缓存
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || (inorder.length | preorder.length) == 0 || inorder.length != preorder.length) {
            return null;
        }
        int N = preorder.length;
        int[] bucket = new int[maxNodeValue << 1 + 1];
        Arrays.fill(bucket, -1);
        for (int i = 0; i < N; i++) {
            bucket[inorder[i] + maxNodeValue] = i;
        }
        return process2(preorder, inorder, 0, N - 1, 0, N - 1, bucket);

    }

    /**
     * preorder[L1...R1] ,inorder[L2...R2]范围上 构建二叉树并且返回头节点
     *
     * @param preorder
     * @param inorder
     * @param L1
     * @param R1
     * @param L2
     * @param R2
     * @return
     */
    private TreeNode process2(int[] preorder, int[] inorder, int L1, int R1, int L2, int R2, int[] bucket) {
        if (L1 > R1 || L2 > R2) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[L1]);
        int inOrderIndex = bucket[preorder[L1] + maxNodeValue];
        int lSize = inOrderIndex == -1 ? R2 + 1 : inOrderIndex - L2;
        root.left = process2(preorder, inorder, L1 + 1, L1 + lSize, L2, L2 + lSize - 1, bucket);
        root.right = process2(preorder, inorder, L1 + lSize + 1, R1, L2 + lSize + 1, R2, bucket);
        return root;
    }


    /**
     * 思路：
     * coding，左右指针
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || (inorder.length | preorder.length) == 0 || inorder.length != preorder.length) {
            return null;
        }
        int N = preorder.length;
        return process(preorder, inorder, 0, N - 1, 0, N - 1);

    }

    /**
     * preorder[L1...R1] ,inorder[L2...R2]范围上 构建二叉树并且返回头节点
     *
     * @param preorder
     * @param inorder
     * @param L1
     * @param R1
     * @param L2
     * @param R2
     * @return
     */
    private TreeNode process(int[] preorder, int[] inorder, int L1, int R1, int L2, int R2) {
        if (L1 > R1 || L2 > R2) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[L1]);
        int inOrderIndex = L2;
        for (; inOrderIndex <= R2; inOrderIndex++) {
            if (inorder[inOrderIndex] == preorder[L1]) {
                break;
            }
        }
        int lSize = inOrderIndex - L2;
        root.left = process(preorder, inorder, L1 + 1, L1 + lSize, L2, L2 + lSize - 1);
        root.right = process(preorder, inorder, L1 + lSize + 1, R1, L2 + lSize + 1, R2);
        return root;
    }


}

package com.zcp.part4.day05;

import java.util.Arrays;
import java.util.Stack;

// 本题测试链接 : https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
public class Code01_ConstructBinarySearchTreeFromPreorderTraversal {

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


    /**
     * 优化思路：
     * 递归+单调栈
     *
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder5(int[] preorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        int N = preorder.length;
        int[] firstBigThan = new int[N];
        Arrays.fill(firstBigThan, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && preorder[stack.peek()] < preorder[i]) {
                firstBigThan[stack.pop()] = i;
            }
            stack.push(i);
        }
        return process5(preorder, 0, N - 1, firstBigThan);
    }

    /**
     * preorder[L..R]的范围构建搜索二叉树并返回头节点
     *
     * @param preorder
     * @param L
     * @param R
     * @return
     */
    private TreeNode process5(int[] preorder, int L, int R, int[] firstBigThan) {
        if (L > R) {
            //两种情况都会走进这个if，1）上一步无右子树，2）上一步无左子树
            return null;
        }
        TreeNode root = new TreeNode(preorder[L]);
        root.left = process5(preorder, L + 1, firstBigThan[L] == -1 ? R : firstBigThan[L] - 1, firstBigThan);
        root.right = process5(preorder, firstBigThan[L] == -1 ? R + 1 : firstBigThan[L], R, firstBigThan);
        return root;
    }

    /**
     * 思路：
     * 递归
     *
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder4(int[] preorder) {
        if (preorder == null || preorder.length < 1) {
            return null;
        }
        return process4(preorder, 0, preorder.length - 1);
    }

    /**
     * preorder[L..R]的范围构建搜索二叉树并返回头节点
     *
     * @param preorder
     * @param L
     * @param R
     * @return
     */
    private TreeNode process4(int[] preorder, int L, int R) {
        if (L > R) {
            //两种情况都会走进这个if，1）上一步无右子树，2）上一步无左子树
            return null;
        }
        TreeNode root = new TreeNode(preorder[L]);
        int i = L + 1;
        //找到值>当前头节点的下标（因为是先序遍历 该下标左边对应左子树所有节点，右边对应右子树所有节点）
        for (; i <= R; i++) {
            if (preorder[i] > preorder[L]) {
                break;
            }
        }
        root.left = process4(preorder, L + 1, i - 1);
        root.right = process4(preorder, i, R);
        return root;
    }


    // 提交下面的方法
    public static TreeNode bstFromPreorder1(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        return process1(pre, 0, pre.length - 1);
    }

    public static TreeNode process1(int[] pre, int L, int R) {
        if (L > R) {
            return null;
        }
        int firstBig = L + 1;
        for (; firstBig <= R; firstBig++) {
            if (pre[firstBig] > pre[L]) {
                break;
            }
        }
        TreeNode head = new TreeNode(pre[L]);
        head.left = process1(pre, L + 1, firstBig - 1);
        head.right = process1(pre, firstBig, R);
        return head;
    }

    // 已经是时间复杂度最优的方法了，但是常数项还能优化
    public static TreeNode bstFromPreorder2(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        int N = pre.length;
        int[] nearBig = new int[N];
        for (int i = 0; i < N; i++) {
            nearBig[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && pre[stack.peek()] < pre[i]) {
                nearBig[stack.pop()] = i;
            }
            stack.push(i);
        }
        return process2(pre, 0, N - 1, nearBig);
    }

    public static TreeNode process2(int[] pre, int L, int R, int[] nearBig) {
        if (L > R) {
            return null;
        }
        int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
        TreeNode head = new TreeNode(pre[L]);
        head.left = process2(pre, L + 1, firstBig - 1, nearBig);
        head.right = process2(pre, firstBig, R, nearBig);
        return head;
    }

    // 最优解
    public static TreeNode bstFromPreorder3(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        int N = pre.length;
        int[] nearBig = new int[N];
        for (int i = 0; i < N; i++) {
            nearBig[i] = -1;
        }
        int[] stack = new int[N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            while (size != 0 && pre[stack[size - 1]] < pre[i]) {
                nearBig[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        return process3(pre, 0, N - 1, nearBig);
    }

    public static TreeNode process3(int[] pre, int L, int R, int[] nearBig) {
        if (L > R) {
            return null;
        }
        int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
        TreeNode head = new TreeNode(pre[L]);
        head.left = process3(pre, L + 1, firstBig - 1, nearBig);
        head.right = process3(pre, firstBig, R, nearBig);
        return head;
    }

}

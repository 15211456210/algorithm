package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C110
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/balanced-binary-tree/submissions/
 * @date 2022/9/1 16:16
 */
public class C110 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isBalance(root)[0] == 1;
    }

    /**
     * int[0]:是否是平衡树
     * int[1]:高度
     *
     * @param root
     * @return
     */
    public int[] isBalance(TreeNode root) {
        if (root == null) {
            return new int[]{1, 0};
        }

        int[] left = isBalance(root.left);
        int[] right = isBalance(root.right);

        int[] result = new int[2];
        //左是平衡树 + 右是平衡树 + 左高度-右高度绝对值<2
        result[0] = (left[0] == 1 && right[0] == 1 && Math.abs(left[1] - right[1]) < 2) == true ? 1 : 0;
        //左，右子树的最大高度+1
        result[1] = Math.max(left[1], right[1]) + 1;
        return result;
    }
}

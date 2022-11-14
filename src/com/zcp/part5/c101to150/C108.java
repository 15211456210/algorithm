package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C108
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/submissions/
 * @date 2022/9/1 16:01
 */
public class C108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    /**
     * nums[l...r] 范围生成BST
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public TreeNode process(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return new TreeNode(nums[l]);
        }

        int mid = l + (r - l) / 2;
        TreeNode head = new TreeNode(nums[mid]);
        head.left = process(nums, l, mid - 1);
        head.right = process(nums, mid + 1, r);
        return head;
    }

}

package com.zcp.part5.c101to150;

import com.zcp.common.ListNode;
import com.zcp.common.TreeNode;

import java.util.ArrayList;

/**
 * @author ZCP
 * @title: C109
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/submissions/
 * @date 2022/9/1 16:10
 */
public class C109 {

    public TreeNode sortedListToBST(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        return process(list.toArray(new Integer[0]), 0, list.size() - 1);

    }

    /**
     * nums[l...r] 范围生成BST
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    public TreeNode process(Integer[] nums, int l, int r) {
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

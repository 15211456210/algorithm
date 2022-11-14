package com.zcp.part5.c301to350;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C324
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/wiggle-sort-ii/description/
 * @date 2022/10/15 16:52
 */
public class C324 {

    /**
     * 思路：
     * 完美洗牌问题
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int len = nums.length;
        //随便来个冒泡排序一下
        Arrays.sort(nums);
        int[] help = new int[len];
        System.arraycopy(nums, 0, help, 0, len);
        int left = ((len + 1) / 2) - 1, right = len - 1;
        boolean flag = true;
        for (int i = 0; i < len; i++) {
            nums[i] = help[flag ? left-- : right--];
            flag = !flag;
        }

    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}

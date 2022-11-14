package com.zcp.part5.c201to250;

import java.util.LinkedList;

/**
 * @author ZCP
 * @title: C239
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/sliding-window-maximum/submissions/
 * @date 2022/9/22 9:29
 */
public class C239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 1) {
            return nums;
        }
        int len = nums.length;
        int[] ans = new int[len - k + 1];
        int index = 0;
        LinkedList<Integer> dQueue = new LinkedList<>();
        for (int R = 0; R < nums.length; R++) {
            //添加元素前先把比当前位置小的值从尾巴弹出
            while (!dQueue.isEmpty() && nums[dQueue.peekLast()] <= nums[R]) {
                dQueue.pollLast();
            }
            dQueue.addLast(R);
            if (R < k - 1) {
                continue;
            }

            Integer maxIndex = dQueue.peekFirst();
            ans[index++] = nums[maxIndex];
            if (maxIndex == R - k + 1) {
                //头元素要过期了
                dQueue.pollFirst();
            }
        }
        return ans;
    }
}

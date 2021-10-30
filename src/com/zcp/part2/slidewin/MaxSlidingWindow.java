package com.zcp.part2.slidewin;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/28
 * @description：[[滑动窗口最大值]] https://leetcode-cn.com/problems/sliding-window-maximum/
 * @version:
 */
public class MaxSlidingWindow {

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


    public static void main(String[] args) {
        MaxSlidingWindow window = new MaxSlidingWindow();
        int[] ints = window.maxSlidingWindow(new int[]{1, -1}, 1);
        System.out.println(Arrays.toString(ints));
    }

}

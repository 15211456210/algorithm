package com.zcp.part5.c401to450;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * @author ZCP
 * @title: C414
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/third-maximum-number/description/
 * @date 2022/11/23 9:53
 */
public class C414 {
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> set = new HashSet<>();
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            if (priorityQueue.size() < 3) {
                priorityQueue.offer(nums[i]);
                continue;
            }

            while (!priorityQueue.isEmpty() && priorityQueue.peek() < nums[i]) {
                stack.push(priorityQueue.poll());
            }
            if (priorityQueue.size() < 3) {
                priorityQueue.offer(nums[i]);
                while (priorityQueue.size() < 3) {
                    priorityQueue.offer(stack.pop());
                }
            }
            stack.clear();
        }

        return priorityQueue.size() < 3 ? max : priorityQueue.poll();

    }


}

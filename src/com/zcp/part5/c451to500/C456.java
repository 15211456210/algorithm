package com.zcp.part5.c451to500;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @author ZCP
 * @title: C456
 * @projectName algorithm
 * @description: https://leetcode.com/problems/132-pattern/
 * @date 2023/2/4 13:49
 */
public class C456 {

    class Solution {
        public boolean find132pattern(int[] nums) {
            int n = nums.length;
            int[] preMin = new int[n];
            int min = nums[0];
            preMin[0] = Integer.MAX_VALUE;
            Stack<Integer> minStack = new Stack<>();
            minStack.push(0);
            for (int i = 1; i < n; i++) {
                preMin[i] = min;
                min = Math.min(min, nums[i]);
                while (!minStack.isEmpty() && nums[minStack.peek()] <= nums[i]) {
                    minStack.pop();
                }
                if (!minStack.isEmpty() && preMin[minStack.peek()] < nums[i]) {
                    return true;
                }
                minStack.push(i);
            }
            return false;
        }
    }
}

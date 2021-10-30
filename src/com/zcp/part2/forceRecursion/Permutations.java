package com.zcp.part2.forceRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/17
 * @description：数组全排列问题 https://leetcode-cn.com/problems/permutations/
 * @version:
 */
public class Permutations {


    public static ArrayList<List<Integer>> permute(int[] nums) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return ans;
        }

        process(nums, 0, ans);
        return ans;
    }

    public static void process(int[] nums, int index, ArrayList<List<Integer>> ans) {
        if (index == nums.length) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(nums[i]);
            }
            ans.add(list);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);//index后面的值依次和index值交换
            process(nums, index + 1, ans);//向下递归
            swap(nums, i, index);//回来需要回复现场，否则可能会有一些情况遍历不到
        }
    }

    private static void swap(int[] nums, int i, int index) {
        int tmp = nums[i];
        nums[i] = nums[index];
        nums[index] = tmp;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2};
        ArrayList<List<Integer>> permute = permute(ints);

        System.out.println();
    }
}

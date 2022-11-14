package com.zcp.part5.c001to050;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c047
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/permutations-ii/submissions/
 * @date 2022/8/28 15:54
 */
public class c047 {

    public List<List<Integer>> permuteUnique(int[] nums) {
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
        boolean[] mask = new boolean[21];//用来标记和index位置交换过的值
        for (int i = index; i < nums.length; i++) {
            if (mask[nums[i] + 10]) {
                //如果这个位置的值和之前交换的值重复了，就跳过（****剪枝****）
                continue;
            }
            mask[nums[i] + 10] = true;
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
}

package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C330
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/patching-array/description/
 * @date 2022/10/17 16:31
 */
public class C330 {


    /**
     * 思路：
     * 满足1-i所有数都能凑出来的情况，往下跳
     *
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        if (nums == null || nums.length < 0) {
            return 0;
        }
        //数组下标
        int index = 0;
        //表示当前能涵盖的所有连续数组的范围0...range连续不断
        long range = 0;
        //添加次数
        int addCnt = 0;
        while (range < n) {
            if (index < nums.length && (range >= nums[index] || range + 1 == nums[index])) {
                range += nums[index++];
            } else {
                range += range + 1;
                addCnt++;
            }
        }
        return addCnt;
    }
}

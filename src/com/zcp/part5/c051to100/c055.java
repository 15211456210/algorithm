package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c055
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/jump-game/submissions/
 * @date 2022/8/28 16:34
 */
public class c055 {

    public boolean canJump(int[] nums) {
        int len = nums.length;
        int[] help = new int[len];
        int max = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(i + nums[i], max);
            help[i] = max;
        }
        int pos = 0;
        while (pos < len && pos != help[pos]) {
            pos = help[pos];
        }
        return pos >= (len - 1);
    }
}

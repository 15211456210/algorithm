package com.zcp.part5.c251to300;

/**
 * @author ZCP
 * @title: C287
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/find-the-duplicate-number/submissions/
 * @date 2022/9/26 16:43
 */
public class C287 {

    public int findDuplicate(int[] nums) {
        int l = 1;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int sCnt = 0;
            for (int i = 0; i < nums.length; i++) {
                sCnt = sCnt + (nums[i] <= mid ? 1 : 0);
            }
            if (sCnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }
        int sCnt = 0;
        for (int i = 0; i < nums.length; i++) {
            sCnt = sCnt + (nums[i] <= l ? 1 : 0);
        }
        return sCnt > l ? l : l + 1;
    }
}

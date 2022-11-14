package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C238
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/product-of-array-except-self/submissions/
 * @date 2022/9/22 9:29
 */
public class C238 {

    public int[] productExceptSelf(int[] nums) {
        int[] preMult = new int[nums.length];
        int[] postMult = new int[nums.length];

        preMult[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preMult[i] = preMult[i - 1] * nums[i];
        }
        postMult[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            postMult[i] = postMult[i + 1] * nums[i];
        }

        int[] ans = new int[nums.length];

        ans[0] = postMult[1];
        ans[nums.length - 1] = preMult[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
            ans[i] = preMult[i - 1] * postMult[i + 1];
        }
        return ans;

    }
}

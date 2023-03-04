package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C477
 * @projectName algorithm
 * @description: https://leetcode.com/problems/total-hamming-distance/
 * @date 2023/2/20 17:16
 */
public class C477 {

    public int totalHammingDistance(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < 30; ++i) {
            int c = 0;
            for (int val : nums) {
                c += (val >> i) & 1;
            }
            ans += c * (n - c);
        }
        return ans;
    }

    /**
     * ttl
     *
     * @param nums
     * @return
     */
//    public int totalHammingDistance(int[] nums) {
//        int len = nums.length;
//        int ans = 0;
//        for (int i = 0; i < len; i++) {
//            for (int j = i + 1; j < len; j++) {
//                ans += hammingDistance(nums[i], nums[j]);
//            }
//        }
//        return ans;
//    }
    public int hammingDistance(int i, int j) {
        int o = i ^ j;
        int ans = 0;
        for (int k = 31; k >= 0; k--) {
            ans += ((o >>> k) & 1);
        }

        return ans;
    }

}

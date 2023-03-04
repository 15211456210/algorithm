package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C453
 * @projectName algorithm
 * @description: https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
 * @date 2023/2/3 11:07
 */
public class C453 {

    public int minMoves(int[] nums) {
        int min = nums[0];
        int ans =0;
        for(int i = 1;i< nums.length;i++){
            if(min <= nums[i]){
                ans += nums[i] - min;
            }else{
                ans += i * (min - nums[i]);
                min = nums[i];
            }
        }
        return ans;

    }
//
//    public int minMoves(int[] nums) {
//
//        int min = Integer.MAX_VALUE;
//
//        for (int num : nums) {
//            min = Math.min(min, num);
//        }
//
//        int ans = 0;
//        for (int num : nums) {
//            ans += Math.max(0, num - min);
//        }
//        return ans;
//
//    }
}

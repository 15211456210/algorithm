package com.zcp.part3.sibianxingbudengshi;

/**
 * @description: 给定一个数组，将数组分为左右两半，要求左右两半累加和的最小值尽量大，返回较小累加和中 最大的 值
 * 例子
 * [2,3,5,2] --> [2,3],[5,2]  --> 5
 * @projectName:algorithm
 * @see:com.zcp.part3.sibianxingbudengshi
 * @author:ZCP
 * @createTime:2021/11/29
 * @version:1.0
 */
public class MaxSubArrSum {


    public int solution(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int maxAns = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = preSum[i + 1] - preSum[0];
            int right = preSum[nums.length] - left;
            maxAns = Math.max(maxAns, Math.min(left, right));
        }
        return maxAns;
    }

    public static void main(String[] args) {
        MaxSubArrSum sum = new MaxSubArrSum();
        System.out.println(sum.solution(new int[]{2, 3, 5, 2, 5, 5}));
    }


}

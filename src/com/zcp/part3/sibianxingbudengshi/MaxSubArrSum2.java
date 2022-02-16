package com.zcp.part3.sibianxingbudengshi;

import java.util.Arrays;

/**
 * @description:给定一个数组，将数组分为左右两半，要求左右两半累加和的最小值尽量大，返回每个长度范围内 较小累加和中 最大的 值
 * <p>
 * * 例子
 * * [2,3,5,2] --> [0,2,5,5]
 * 解释：
 * [2] --> 0
 * [2,3] --> 2
 * [2,3,5] --> 5
 * [2,3,5,2] --> 5
 * * @projectName:algorithm
 * @see:com.zcp.part3.sibianxingbudengshi
 * @author:ZCP
 * @createTime:2021/11/29
 * @version:1.0
 */
public class MaxSubArrSum2 {

    public int[] solution(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int[] ans = new int[nums.length];
        int splitIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            int sum = preSum[i + 1] - preSum[0];
            int left = preSum[splitIndex + 1] - preSum[0];
            int right = sum - left;

            while (true) {
                ans[i] = Math.min(left, right);
                //下一个位置
                left = preSum[splitIndex + 2] - preSum[0];
                right = sum - left;
                if (left > right) {
                    break;
                } else {
                    splitIndex++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxSubArrSum2 sum2 = new MaxSubArrSum2();
        System.out.println(Arrays.toString(sum2.solution(new int[]{2, 3, 5, 2})));
    }


}

package com.zcp.part3.subarr;

/**
 * @description: 整数数组子数组累加和小于等于K的最大长度
 * 输入一个数组，有正有负，有零，和K
 * 返回所有子数组和<=K的最大子数组长度
 * @projectName:algorithm
 * @see:com.zcp.part3.subarr
 * @author:ZCP
 * @createTime:2021/11/25
 * @version:1.0
 */
public class MaxSubArrayLen2 {


    public int maxSubArrayLen2(int[] nums, int k) {
        if (nums == null && nums.length < 1) {
            return 0;
        }

        int[] minSum = new int[nums.length];//nums[i]开始，最小累加和
        int[] minSumEnd = new int[nums.length];//minSum[i]对应结束位置下标

        for (int i = minSum.length - 1; i >= 0; i--) {
            if (i < minSum.length - 1 && minSum[i + 1] <= 0) {
                minSum[i] = nums[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = nums[i];
                minSumEnd[i] = i;
            }
        }

        int maxLen = 0;
        int endIndex = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            while (endIndex < minSum.length && sum + minSum[endIndex] <= k) {
                sum += minSum[endIndex];
                endIndex = minSumEnd[endIndex] + 1;
                maxLen = Math.max(maxLen, endIndex - i);
            }
            if (i == endIndex) {
                endIndex++;
            } else {
                sum -= nums[i];
            }
        }
        return maxLen;
    }

    private static int[] generateArray(int length, int range) {
        int len = (int) (Math.random() * length) + 1;
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
        }
        return arr;
    }

    /**
     * 验证函数
     *
     * @param nums
     * @param k
     * @return
     */
    public int checkFun(int[] nums, int k) {
        if (nums == null && nums.length < 1) {
            return 0;
        }
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum <= k) {
                    maxLen = Math.max(j - i + 1, maxLen);
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int size = 1000;
        int range = 100;
        System.out.println("测试开始");
        MaxSubArrayLen2 arrayLen2 = new MaxSubArrayLen2();
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * range);
            int[] array = generateArray(size, range);
            int r1 = arrayLen2.maxSubArrayLen2(array, k);
            int r2 = arrayLen2.checkFun(array, k);
            if (r1 != r2) {
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");

    }


}

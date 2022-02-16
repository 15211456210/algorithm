package com.zcp.part4.day14;

// 测试链接：https://leetcode.com/problems/first-missing-positive/
public class Code06_MissingNumber {

    /**
     * 思路：
     * 同一个数组中分区
     * 将数组分为3部分
     * [0...L-1] 固定区域，其中任意点位置值都满足 nums[i] = i+1
     * [L...R-1] 待校验位置
     * [R...N-1] 垃圾区域，其中任何一个点的值都不会影响最终结果，其中3种类型的数会进入垃圾区
     * p1：<=0的数
     * p2：>R的数
     * p3：nums[L] == nums[nums[L] - 1]的数
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 1;
        }
        int len = nums.length;
        int L = 0;
        int R = len;

        while (L < R) {
            if (nums[L] == L + 1) {
                //
                L++;
            } else if (nums[L] <= 0 || nums[L] > R || nums[L] == nums[nums[L] - 1]) {
                swap(nums, L, --R);
            } else {
                swap(nums, L, nums[L] - 1);
            }
        }
        return L + 1;
    }


    public static int firstMissingPositive(int[] arr) {
        // l是盯着的位置
        // 0 ~ L-1有效区
        int L = 0;
        int R = arr.length;
        while (L != R) {
            if (arr[L] == L + 1) {
                L++;
            } else if (arr[L] <= L || arr[L] > R || arr[arr[L] - 1] == arr[L]) { // 垃圾的情况
                swap(arr, L, --R);
            } else {
                swap(arr, L, arr[L] - 1);
            }
        }
        return L + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}

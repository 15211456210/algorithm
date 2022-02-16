package com.zcp.part4.day01;

import java.util.Arrays;

/**
 * @description: [[一根长度为K的绳子，最多能盖住几个点]]
 * 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点 绳子的边缘点碰到X轴上的点，也算盖住
 * @projectName:algorithm
 * @see:com.zcp.part4.day01
 * @author:ZCP
 * @createTime:2021/12/23
 * @version:1.0
 */
public class Code01_CordCoverMaxPoint {


    /**
     * 思路：
     * 左右两指针，一个记录开始位置，一个记录结束点位置
     * @param arr
     * @param k
     * @return
     */
    public static int maxPoint(int[] arr, int k) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int N = arr.length;
        int l = 0, r = 0;
        int max = 0;
        while (r < N) {
            while (r < N && arr[r] - arr[l] <= k) {
                r++;
            }
            max = Math.max(max, r - l);
            l++;
        }
        return max;
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint(arr, L);
            int ans2 = test(arr, L);
            if (ans1 != ans2) {
                System.out.println("oops!");
                break;
            }
        }

    }

}

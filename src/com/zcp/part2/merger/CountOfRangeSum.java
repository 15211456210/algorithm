package com.zcp.part2.merger;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * <p>
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-range-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 思路：
 * 1.求出前缀和数组presum
 * 2.根据sum[i,j] = presum[j]-presum[i-1]的结论 将 目标范围转化为 另外的指标
 * 3.结合merge过程求出满足范围的个数
 */
public class CountOfRangeSum {

    public static int solution(int[] arr, int lower, int upper) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        //求出前缀和数组
        int[] presum = new int[arr.length];
        presum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            presum[i] = presum[i - 1] + arr[i];
        }
        return rangeCount(presum, 0, arr.length - 1, lower, upper);

    }

    /**
     * 求Arr[L...R]中符合条件的子区间个数
     *
     * @param presum
     * @param L
     * @param R
     * @param lower
     * @param upper
     * @return
     */
    private static int rangeCount(int[] presum, int L, int R, int lower, int upper) {
        if (L == R) {
            return presum[L] <= upper && presum[L] >= lower ? 1 : 0;
        }

        int M = L + ((R - L) >> 1);
        int leftCount = rangeCount(presum, L, M, lower, upper);
        int rightCount = rangeCount(presum, M + 1, R, lower, upper);

        //求出符合条件的个数

        int pR = M + 1;
        int windowL = L;//左侧窗口左边界
        int windowR = L;//左侧窗口右边界
        int count = 0;
        while (windowL <= M && pR <= R) {
            int rangeL = presum[pR] - upper;
            int rangeR = presum[pR] - lower;
            while (windowR <= M && presum[windowR] <= rangeR) {
                windowR++;
            }
            //移动左半边 窗口左边界
            while (windowL <= windowR && presum[windowL] < rangeL) {
                windowL++;
            }
            count += Math.max(0, windowR - windowL);
            pR++;
        }

        //常规的merge排序过程
        int[] tmp = new int[R - L + 1];
        int pl = L;
        int pr = M + 1;
        int index = 0;
        while (pl <= M && pr <= R) {
            tmp[index++] = presum[pl] < presum[pr] ? presum[pl++] : presum[pr++];
        }
        while (pl <= M) {
            tmp[index++] = presum[pl++];
        }
        while (pr <= R) {
            tmp[index++] = presum[pr++];
        }

        index = L;
        //拷贝顺序
        for (int i = 0; i < tmp.length; i++) {
            presum[L++] = tmp[i];
        }
        return leftCount + rightCount + count;
    }

    public static int solution2(int[] arr, int lower, int upper) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                count += sum >= lower && sum <= upper ? 1 : 0;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int length = 500;
        int range = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] array = generateArray(length, range);
            int[] array1 = copyArray(array);
            int[] array2 = copyArray(array);
            int lower = (int) (Math.random() * range) - (int) (Math.random() * range);
            int upper = range - 1;
            do {
                upper = (int) (Math.random() * range) - (int) (Math.random() * range);
            } while (lower > upper);
            if (solution(array, lower, upper) != solution2(array1, lower, upper)) {
                System.out.println("出错了");
                System.out.println(Arrays.toString(array2));
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int[] generateArray(int length, int range) {
        int len = (int) (Math.random() * length);
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
        }
        return arr;
    }

    private static int[] copyArray(int[] arr) {
        int len = arr.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }


}

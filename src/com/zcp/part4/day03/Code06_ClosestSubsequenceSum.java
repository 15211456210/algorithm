package com.zcp.part4.day03;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

// 本题测试链接 : https://leetcode.com/problems/closest-subsequence-sum/
// 本题数据量描述:
// 1 <= nums.length <= 40
// -10^7 <= nums[i] <= 10^7
// -10^9 <= goal <= 10^9
// 通过这个数据量描述可知，需要用到分治，因为数组长度不大
// 而值很大，用动态规划的话，表会爆
public class Code06_ClosestSubsequenceSum {


    /**
     * 思路分治
     * @param nums
     * @param goal
     * @return
     */
    public int minAbsDifference3(int[] nums, int goal) {

        if (nums == null || nums.length < 1) {
            return Integer.MAX_VALUE;
        }

        int N = nums.length >> 1;
        int M = nums.length - N;
        int[] left = new int[N];
        for (int i = 0; i < N; i++) {
            left[i] = nums[i];
        }
        int[] right = new int[M];
        for (int i = 0; i < M; i++) {
            right[i] = nums[N + i];
        }

        TreeSet<Integer> lSet = new TreeSet<>();
        TreeSet<Integer> rSet = new TreeSet<>();

        process3(left, 0, 0, lSet);
        process3(right, 0, 0, rSet);
        int p = Integer.MAX_VALUE;
        for (Integer le : lSet) {
            int target = goal - le;
            if (rSet.floor(target) != null) {
                p = Math.min(p, goal - (rSet.floor(target) + le));
            }
            if (rSet.ceiling(target) != null) {
                p = Math.min(p, rSet.ceiling(target) + le - goal);
            }
        }
        return p;
    }

    private void process3(int[] arr, int index, int sum, TreeSet<Integer> set) {
        if (arr.length == index) {
            set.add(sum);
            return;
        }
        process3(arr, index + 1, sum, set);
        process3(arr, index + 1, sum + arr[index], set);
    }


    int[] lSet = new int[2 << 20];
    int[] rSet = new int[2 << 20];
    int pIndex = 0;

    public int minAbsDifference2(int[] nums, int goal) {

        if (nums == null || nums.length < 1) {
            return Integer.MAX_VALUE;
        }

        int N = nums.length >> 1;
        int M = nums.length - N;
        int[] left = new int[N];
        for (int i = 0; i < N; i++) {
            left[i] = nums[i];
        }
        int[] right = new int[M];
        for (int i = 0; i < M; i++) {
            right[i] = nums[N + i];
        }

        pIndex = 0;
        process2(left, 0, 0, lSet);
        int lCnt = pIndex;
        pIndex = 0;
        process2(right, 0, 0, rSet);
        int rCnt = pIndex;

        Arrays.sort(lSet, 0, lCnt);
        Arrays.sort(rSet, 0, rCnt);

        int ans = Integer.MAX_VALUE;
        int rp = rCnt - 1;
        for (int i = 0; i < lCnt; i++) {
            int rest = goal - lSet[i];
            while (rp > 0 && Math.abs(rSet[rp] - rest) >= Math.abs(rSet[rp - 1] - rest)) {
                rp--;
            }
            ans = Math.min(ans, Math.abs(rSet[rp] - rest));
        }
        return ans;
    }

    private void process2(int[] arr, int index, int sum, int[] set) {
        if (arr.length == index) {
            set[pIndex++] = sum;
            return;
        }
        process2(arr, index + 1, sum, set);
        process2(arr, index + 1, sum + arr[index], set);
    }


    public static int[] l = new int[1 << 20];
    public static int[] r = new int[1 << 20];

    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        int le = process(nums, 0, nums.length >> 1, 0, 0, l);
        int re = process(nums, nums.length >> 1, nums.length, 0, 0, r);
        Arrays.sort(l, 0, le);
        Arrays.sort(r, 0, re--);
        int ans = Math.abs(goal);
        for (int i = 0; i < le; i++) {
            int rest = goal - l[i];
            while (re > 0 && Math.abs(rest - r[re - 1]) <= Math.abs(rest - r[re])) {
                re--;
            }
            ans = Math.min(ans, Math.abs(rest - r[re]));
        }
        return ans;
    }

    public static int process(int[] nums, int index, int end, int sum, int fill, int[] arr) {
        if (index == end) {
            arr[fill++] = sum;
        } else {
            fill = process(nums, index + 1, end, sum, fill, arr);
            fill = process(nums, index + 1, end, sum + nums[index], fill, arr);
        }
        return fill;
    }

}

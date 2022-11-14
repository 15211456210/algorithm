package com.zcp.part5.c301to350;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author ZCP
 * @title: C313
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/super-ugly-number/
 * @date 2022/9/29 15:00
 */
public class C313 {

    /**
     * https://www.bilibili.com/video/BV1Ma41167B3/?spm_id_from=333.337.search-card.all.click&vd_source=fce0cecd181c9411592aeb2fd440b717
     *
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int[] points = new int[len];
        int[] ans = new int[n];
        int[] nums = Arrays.copyOf(primes, len);
        ans[0] = 1;
        int cIndex = 1;
        while (cIndex < n) {
            int min = nums[0];
            for (int i = 0; i < len; i++) {
                if (nums[i] < min) {
                    min = nums[i];
                }
            }
            ans[cIndex++] = min;
            for (int i = 0; i < len; i++) {
                if (min == nums[i]) {
                    points[i]++;
                    if (ans[points[i]] * primes[i] > 0) {
                        nums[i] = ans[points[i]] * primes[i];
                    } else {
                        nums[i] = Integer.MAX_VALUE;
                    }
                }
            }
        }

        return ans[n - 1];

    }

//    /**
//     * TTL
//     *
//     * @param n
//     * @param primes
//     * @return
//     */
//    public int nthSuperUglyNumber(int n, int[] primes) {
//
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
//
//        priorityQueue.offer(1);
//        Integer poll;
//        Integer next;
//        for (int i = 0; i < n; i++) {
//            poll = priorityQueue.poll();
//            while (!priorityQueue.isEmpty() && priorityQueue.peek().equals(poll)) {
//                priorityQueue.poll();
//            }
//            if (i == n - 1) {
//                return (int) ((long) poll);
//            }
//
//            for (int j = 0; j < primes.length; j++) {
//                if (Integer.MAX_VALUE / poll >= primes[j]) {
//                    priorityQueue.offer(poll * primes[j]);
//                }
//            }
//        }
//        return 0;
//    }
}

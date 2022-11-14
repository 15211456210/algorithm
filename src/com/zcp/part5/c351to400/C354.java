package com.zcp.part5.c351to400;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C354
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/russian-doll-envelopes/submissions/
 * @date 2022/10/20 16:21
 */
public class C354 {

    public class Envelope implements Comparable<Envelope> {
        int w;
        int h;

        public Envelope(int w, int h) {
            this.w = w;
            this.h = h;
        }

        @Override
        public int compareTo(Envelope o) {
            return this.w == o.w ? o.h - this.h : this.w - o.w;
        }
    }

    /**
     * 优化版本
     * O(NlogN)解决最长递增子序列问题
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        int len = envelopes.length;
        Envelope[] envelopesList = new Envelope[len];
        for (int i = 0; i < len; i++) {
            envelopesList[i] = new Envelope(envelopes[i][0], envelopes[i][1]);
        }
        Arrays.sort(envelopesList);

        int maxLen = 1;
        //minEnd[i]表示：当前时刻，长度为（i+1）的递增自序列中最小的结尾值
        int[] minEnd = new int[len];
        Arrays.fill(minEnd, Integer.MAX_VALUE);
        //先放入数组一个元素 表示当前长度为1的递增子序列 最小结尾的值是nums[0]
        minEnd[0] = envelopesList[0].h;
        //minEnd中的分界线，左边表示有用的值，右边表示未初始化的值，后续二分查找只在左半部分中进行
        int splitIndex = 0;
        for (int i = 1; i < len; i++) {
            int left = 0;
            int right = splitIndex;
            int mid = left + (right - left) / 2;
            //二分查找 minEnd中 小于nums[i]离它最近的位置
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (minEnd[mid] >= envelopesList[i].h) {
                    right = mid - 1;
                } else if (minEnd[mid] < envelopesList[i].h) {
                    left = mid + 1;
                }
            }
            //计算以nums[i]当前元素结尾的最长递增子序列
            int curMaxLen = 0;
            if (minEnd[mid] < envelopesList[i].h) {
                curMaxLen = mid + 2;
                minEnd[curMaxLen - 1] = Math.min(minEnd[curMaxLen - 1], envelopesList[i].h);
            } else if (minEnd[mid] >= envelopesList[i].h) {
                if (mid == 0) {
                    curMaxLen = 1;
                    minEnd[0] = Math.min(minEnd[0], envelopesList[i].h);
                } else {
                    //mid>0
                    curMaxLen = mid + 1;
                    minEnd[curMaxLen - 1] = Math.min(minEnd[curMaxLen - 1], envelopesList[i].h);
                }
            }
            splitIndex = Math.max(splitIndex, curMaxLen - 1);
            maxLen = Math.max(maxLen, curMaxLen);
        }
        return maxLen;
    }
}

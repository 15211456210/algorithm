package com.zcp.part4.day09;

import java.util.Arrays;
import java.util.Comparator;

// 本题测试链接 : https://leetcode.com/problems/russian-doll-envelopes/
public class Code04_EnvelopesProblem {

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
     * @param envelopes
     * @return
     */
    public int maxEnvelopes3(int[][] envelopes) {
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

    /**
     * 思路：
     * 排序‘最长递增子序列问题
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        int maxLen = 0;
        int len = envelopes.length;
        Envelope[] envelopesList = new Envelope[len];
        for (int i = 0; i < len; i++) {
            envelopesList[i] = new Envelope(envelopes[i][0], envelopes[i][1]);
        }
        Arrays.sort(envelopesList);

        int[] maxSubLen = new int[len];
        for (int i = 0; i < envelopesList.length; i++) {
            int curMaxLen = 1;
            for (int j = 0; j < i; j++) {
                if (envelopesList[j].h < envelopesList[i].h) {
                    curMaxLen = Math.max(curMaxLen, maxSubLen[j] + 1);
                }
            }
            maxSubLen[i] = curMaxLen;
            maxLen = Math.max(maxLen, curMaxLen);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{{3, 2}, {2, 1}, {2, 5}};
        new Code04_EnvelopesProblem().maxEnvelopes2(test);


    }


//    public static int maxEnvelopes(int[][] matrix) {
//        Envelope[] arr = sort(matrix);
//        int[] ends = new int[matrix.length];
//        ends[0] = arr[0].h;
//        int right = 0;
//        int l = 0;
//        int r = 0;
//        int m = 0;
//        for (int i = 1; i < arr.length; i++) {
//            l = 0;
//            r = right;
//            while (l <= r) {
//                m = (l + r) / 2;
//                if (arr[i].h > ends[m]) {
//                    l = m + 1;
//                } else {
//                    r = m - 1;
//                }
//            }
//            right = Math.max(right, l);
//            ends[l] = arr[i].h;
//        }
//        return right + 1;
//    }

//    public static class Envelope {
//        public int l;
//        public int h;
//
//        public Envelope(int weight, int hight) {
//            l = weight;
//            h = hight;
//        }
//    }
//
//    public static class EnvelopeComparator implements Comparator<Envelope> {
//        @Override
//        public int compare(Envelope o1, Envelope o2) {
//            return o1.l != o2.l ? o1.l - o2.l : o2.h - o1.h;
//        }
//    }
//
//    public static Envelope[] sort(int[][] matrix) {
//        Envelope[] res = new Envelope[matrix.length];
//        for (int i = 0; i < matrix.length; i++) {
//            res[i] = new Envelope(matrix[i][0], matrix[i][1]);
//        }
//        Arrays.sort(res, new EnvelopeComparator());
//        return res;
//    }

}

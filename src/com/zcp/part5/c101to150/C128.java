package com.zcp.part5.c101to150;

import java.util.HashMap;

/**
 * @author ZCP
 * @title: C128
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-consecutive-sequence/submissions/
 * @date 2022/9/5 15:48
 */
public class C128 {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length < 0) {
            return 0;
        }
        HashMap<Integer, Integer> headMap = new HashMap<>();
        HashMap<Integer, Integer> tailMap = new HashMap<>();
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int cLen = 1;
            int cNum = nums[i];
            if (headMap.containsKey(cNum) || tailMap.containsKey(cNum)) {
                continue;
            }
            headMap.putIfAbsent(cNum, 1);
            tailMap.putIfAbsent(cNum, 1);
            Integer nextCount = headMap.get(cNum + 1);
            Integer preCount = tailMap.get(cNum - 1);
            if (nextCount != null) {
                //后面有数可以接上
                headMap.remove(cNum + 1);
                tailMap.remove(cNum);

                cLen += nextCount;
                tailMap.put(cNum + cLen - 1, cLen);
                headMap.put(cNum, cLen);
            }
            if (preCount != null) {
                //前面有数可以接上
                tailMap.remove(cNum - 1);
                headMap.remove(cNum);
                //计算头尾的数
                int tailNum = cNum + cLen - 1;
                int headNum = cNum - preCount;
                //更新长度
                cLen += preCount;
                tailMap.put(tailNum, cLen);
                headMap.put(headNum, cLen);

            }
            maxLen = Math.max(maxLen, cLen);
        }
        return maxLen;
    }
}

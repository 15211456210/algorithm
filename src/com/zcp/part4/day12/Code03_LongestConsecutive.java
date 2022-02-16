package com.zcp.part4.day12;

import com.zcp.part4.day05.Hash;

import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/longest-consecutive-sequence/
public class Code03_LongestConsecutive {


    /**
     * 头尾节点 + 双map
     *
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length < 0) {
            return 0;
        }
        HashMap<Integer, Integer> headMap = new HashMap<>();
        HashMap<Integer, Integer> tailMap = new HashMap<>();
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int cLen = 1;
            int cNum = nums[i];
            if(headMap.containsKey(cNum) || tailMap.containsKey(cNum)){
                continue;
            }
            headMap.putIfAbsent(cNum,1);
            tailMap.putIfAbsent(cNum,1);
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
            maxLen = Math.max(maxLen,cLen);
        }
        return maxLen;
    }

    public static int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
                int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                int posLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                int all = preLen + posLen + 1;
                map.put(num - preLen, all);
                map.put(num + posLen, all);
                len = Math.max(len, all);
            }
        }
        return len;
    }

}

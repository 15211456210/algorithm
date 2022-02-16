package com.zcp.part4.day03;

import java.util.ArrayList;
import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/freedom-trail/
public class Code07_FreedomTrail {


    /**
     * 思路：
     * 动态规划，预处理结构
     *
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps2(String ring, String key) {
        if (ring == null || ring.length() < 1 || key == null || key.length() < 1) {
            return 0;
        }
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        initRingMap(ring, map);//生成预处理结构，存放ring中每种字符出现的位置
        char[] keys = key.toCharArray();
        int[][] dp = new int[ring.length()][key.length() + 1];
        return process2(keys, 0, 0, map, ring.length(), dp);
    }

    /**
     * 当前ring指针在rIndex位置，当前需要处理keys[kIndex]字符，后续任意选择，返回需要的最小代价
     * @param keys
     * @param rIndex
     * @param kIndex
     * @param map
     * @param ringLen
     * @param dp
     * @return
     */
    private int process2(char[] keys, int rIndex, int kIndex, HashMap<Character, ArrayList<Integer>> map, int ringLen, int[][] dp) {
        if (dp[rIndex][kIndex] != 0) {
            return dp[rIndex][kIndex];
        }
        if (keys.length == kIndex) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (Integer nextPosition : map.get(keys[kIndex])) {
            //ring中存在多个匹配的字符   每种可能的情况都试一遍 取最小代价
            ans = Math.min(ans, getShortDistance(rIndex, nextPosition, ringLen) + 1 + process2(keys, nextPosition, kIndex + 1, map, ringLen, dp));
        }
        dp[rIndex][kIndex] = ans;
        return ans;
    }

    /**
     * 根据当前指针位置，目标位置，和ring长度 ringLen计算 rIndex,nextPosition之间最小代价（顺时针、逆时针）
     * @param rIndex
     * @param nextPosition
     * @param ringLen
     * @return
     */
    private int getShortDistance(int rIndex, Integer nextPosition, int ringLen) {
        return Math.min(Math.abs(rIndex - nextPosition), Math.min(rIndex + ringLen - nextPosition, nextPosition + ringLen - rIndex));
    }

    private void initRingMap(String ring, HashMap<Character, ArrayList<Integer>> map) {
        char[] chars = ring.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], new ArrayList<>());
            }
            map.get(chars[i]).add(i);
        }
    }


    public static int findRotateSteps(String r, String k) {
        char[] ring = r.toCharArray();
        int N = ring.length;
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(ring[i])) {
                map.put(ring[i], new ArrayList<>());
            }
            map.get(ring[i]).add(i);
        }
        char[] str = k.toCharArray();
        int M = str.length;
        int[][] dp = new int[N][M + 1];
        // hashmap
        // dp[][] == -1 : 表示没算过！
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = -1;
            }
        }
        return process(0, 0, str, map, N, dp);
    }

    // 电话里：指针指着的上一个按键preButton
    // 目标里：此时要搞定哪个字符？keyIndex
    // map : key 一种字符 value: 哪些位置拥有这个字符
    // N: 电话大小
    // f(0, 0, aim, map, N)
    public static int process(int preButton, int index, char[] str, HashMap<Character, ArrayList<Integer>> map, int N,
                              int[][] dp) {
        if (dp[preButton][index] != -1) {
            return dp[preButton][index];
        }
        int ans = Integer.MAX_VALUE;
        if (index == str.length) {
            ans = 0;
        } else {
            // 还有字符需要搞定呢！
            char cur = str[index];
            ArrayList<Integer> nextPositions = map.get(cur);
            for (int next : nextPositions) {
                int cost = dial(preButton, next, N) + 1 + process(next, index + 1, str, map, N, dp);
                ans = Math.min(ans, cost);
            }
        }
        dp[preButton][index] = ans;
        return ans;
    }

    public static int dial(int i1, int i2, int size) {
        return Math.min(Math.abs(i1 - i2), Math.min(i1, i2) + size - Math.max(i1, i2));
    }

}

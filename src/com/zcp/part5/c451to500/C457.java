package com.zcp.part5.c451to500;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ZCP
 * @title: C457
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/circular-array-loop/
 * @date 2023/2/6 14:47
 */
public class C457 {

    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        boolean isZ = true;
        Map<Integer,Integer> set = new HashMap<>();
        Set<Integer> path = new HashSet<>();
        for (int i = 0; i < len; i++) {
            int pos = i;
            set.clear();
            path.clear();
            int seq = 0;
            do {
                path.add(pos);
                if (nums[pos] > 0 != isZ) {
                    set.clear();
                }
                isZ = nums[pos] > 0;
                set.put(pos,seq++);
                pos = getNextPos(pos, nums[pos], len);
            } while (!path.contains(pos));
            if (set.containsKey(pos) && seq - set.get(pos) > 1) {
                return true;
            }
        }
        return false;
    }

    public int getNextPos(int pos, int move, int len) {
        move = move % len;
        return (pos + move + len) % len;
    }

    public static void main(String[] args) {
        System.out.println(new C457().circularArrayLoop(new int[]{-1,-2,-3,-4,-5}));
    }
}

package com.zcp.part5.c401to450;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ZCP
 * @title: C401
 * @projectName algorithm
 * @description: https://leetcode.com/problems/binary-watch/
 * @date 2022/11/14 17:02
 */
public class C401 {

    int[] h = new int[]{8, 4, 2, 1};
    int[] m = new int[]{32, 16, 8, 4, 2, 1};

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        dfs(0, turnedOn, set);

        for (int n : set) {
            int min = 0;
            int hour = 0;
            for (int i = 0; i < 6; ++i) {
                min += (n & (1 << i)) != 0 ? m[i] : 0;
            }
            n = n >> 6;
            for (int i = 0; i < 4; i++) {
                hour += (n & (1 << i)) != 0 ? h[i] : 0;
            }
            if (hour < 12 && min < 60){
                ans.add("" + hour + ":" + (min < 10 ? "0" : "") + min);
            }

        }
        return ans;
    }

    private void dfs(int selected, int remain, Set<Integer> ans) {
        if (remain == 0) {
            if (!ans.contains(selected)) {
                ans.add(selected);
            }
            return;
        }

        for (int i = 0; i < 10; ++i) {
            if (((1 << i) & (selected)) == 0) {
                dfs(selected | (1 << i), remain - 1, ans);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new C401().readBinaryWatch(2));
    }

}

package com.zcp.part5.c451to500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author ZCP
 * @title: C455
 * @projectName algorithm
 * @description: https://leetcode.com/problems/assign-cookies/
 * @date 2023/2/4 10:27
 */
public class C455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int left = 0, right = 0;
        int ans = 0;
        while (left < g.length && right < s.length) {
            while (right < s.length && s[right] < g[left]) {
                right++;
            }
            if (right < s.length) {
                left++;
                right++;
                ans++;
            }

        }
        return ans;
    }
}

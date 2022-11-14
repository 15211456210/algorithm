package com.zcp.part5.c101to150;

import java.util.HashMap;

/**
 * @author ZCP
 * @title: C149
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/max-points-on-a-line/submissions/
 * @date 2022/9/7 10:56
 */
public class C149 {

    public int maxPoints(int[][] points) {
        int ans = 0;

        int n = points.length;
        HashMap<Double, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.clear();
            int x = 0, y = 0, k = 0;
            for (int two = 0; two < n; two++) {
                if (two == i) {
                    continue;
                }
                if (points[i][0] == points[two][0]) {
                    y++;
                }
                if (points[i][1] == points[two][1]) {
                    x++;
                }
                double key = (double) (points[i][1] - points[two][1]) / (double) (points[i][0] - points[two][0]);
                Integer v = map.getOrDefault(key, 0);
                if (++v > k) {
                    k = v;
                }
                map.put(key, v);
            }
            ans = Math.max(ans, Math.max(x, Math.max(y, k)));
        }
        return ans + 1;
    }


}

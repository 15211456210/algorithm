package com.zcp.part5.c401to450;

import java.util.HashMap;

/**
 * @author ZCP
 * @title: C447
 * @projectName algorithm
 * @description: https://leetcode.com/problems/number-of-boomerangs/
 * @date 2023/2/2 9:57
 */
public class C447 {


    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        int length = points.length;
        if (length < 3) {
            return ans;
        }
        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.clear();
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    continue;
                }
                long distance = distance(points[i][0], points[i][1], points[j][0], points[j][1]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            for (Integer value : map.values()) {
                ans += A(value, 2);
            }

        }
        return ans;
    }

    /**
     * A(n,m)
     *
     * @param n
     * @param m
     * @return
     */
    private long A(int n, int m) {
        if (n < m) {
            return 0;
        }
        long ans = 1;
        for (int i = 0; i < m; i++) {
            ans *= n--;
        }
        return ans;
    }


//    /**
//     * ttl
//     * @param points
//     * @return
//     */
//    public int numberOfBoomerangs(int[][] points) {
//        int ans = 0;
//        int length = points.length;
//        if (length < 3) {
//            return ans;
//        }
//        for (int i = 0; i < length; i++) {
//            for (int j = 0; j < length; j++) {
//                for (int k = 0; k < length; k++) {
//                    if (i == j || i == k || j == k) {
//                        continue;
//                    }
//                    if (distance(points[i][0], points[i][1], points[j][0], points[j][1]) == distance(points[i][0], points[i][1], points[k][0], points[k][1])) {
//                        ans++;
//                    }
//                }
//            }
//        }
//        return ans;
//    }

    private long distance(int... x) {
        return (x[0] - x[2]) * (x[0] - x[2]) + (x[1] - x[3]) * (x[1] - x[3]);
    }
}

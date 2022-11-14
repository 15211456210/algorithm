package com.zcp.part4.day22;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author ZCP
 * @title: TallestBillboard
 * @projectName algorithm
 * @description: https://leetcode-cn.com/problems/tallest-billboard/
 * @date 2022/3/16 22:34
 */
public class TallestBillboard {

    /**
     * 思路：
     * 使用map存储信息
     *
     * @param rods
     * @return
     */

    public int tallestBillboard(int[] rods) {
        if (rods == null || rods.length == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, 0);

        for (int i = 0; i < rods.length; i++) {
            HashMap<Integer, Integer> newMap = new HashMap<Integer, Integer>();
            Set<Map.Entry<Integer, Integer>> entrys = map.entrySet();
            Iterator<Map.Entry<Integer, Integer>> iters = entrys.iterator();
            while (iters.hasNext()) {
                Map.Entry<Integer, Integer> entry = iters.next();
                int small = entry.getValue();
                int big = entry.getKey() + small;

                //p1
                int newValue = small;
                int newKey = big + rods[i] - newValue;
                if (!newMap.containsKey(newKey) || newMap.get(newKey) < newValue) {
                    newMap.put(newKey, newValue);
                }


                //p2
                newValue = Math.min(small + rods[i], big);
                newKey = Math.abs(small + rods[i] - big);
                if (!newMap.containsKey(newKey) || newMap.get(newKey) < newValue) {
                    newMap.put(newKey, newValue);
                }

            }
            entrys = map.entrySet();
            iters = entrys.iterator();
            while (iters.hasNext()) {
                Map.Entry<Integer, Integer> entry = iters.next();
                int oldKey = entry.getKey();
                int oldValue = entry.getValue();

                if (!newMap.containsKey(oldKey) || newMap.get(oldKey) < oldValue) {
                    newMap.put(oldKey, oldValue);
                }
            }
            map = newMap;
        }
        return map.containsKey(0) ? map.get(0) : 0;

    }


}

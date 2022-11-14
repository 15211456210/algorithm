package com.zcp.part5.c351to400;

import java.util.*;

/**
 * @author ZCP
 * @title: C380
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/insert-delete-getrandom-o1/description/
 * @date 2022/10/29 11:23
 */
public class C380 {


    class RandomizedSet {
        private Map<Integer, Integer> map;
        private List<Integer> list;

        public RandomizedSet() {
            this.map = new HashMap<>();
            this.list = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Integer removeIndex = map.get(val);
            int size = list.size();
            int last = list.get(size - 1);
            list.set(removeIndex, last);
            map.put(last, removeIndex);
            list.remove(size - 1);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get((int) (Math.random() * list.size()));
        }
    }
}

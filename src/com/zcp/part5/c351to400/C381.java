package com.zcp.part5.c351to400;

import java.util.*;

/**
 * @author ZCP
 * @title: C381
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/insert-delete-getrandom-o1-duplicates-allowed/description/
 * @date 2022/10/29 15:35
 */
public class C381 {

    class RandomizedCollection {

        private Map<Integer, TreeSet<Integer>> indexes;
        private List<Integer> nums;

        public RandomizedCollection() {
            this.indexes = new HashMap<>();
            this.nums = new ArrayList<>();
        }

        public boolean insert(int val) {
            TreeSet<Integer> set = indexes.getOrDefault(val, new TreeSet<>((n1, n2) -> n2 - n1));
            int idx = nums.size();
            set.add(idx);
            indexes.put(val, set);
            nums.add(val);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            if (!indexes.containsKey(val)) {
                return false;
            }
            TreeSet<Integer> set = indexes.get(val);
            int removeIndex = set.first();
            int size = nums.size();
            int lastNum = nums.get(size - 1);
            nums.set(removeIndex, lastNum);
            nums.remove(size - 1);
            TreeSet<Integer> lastNumIdxList = indexes.get(lastNum);

            lastNumIdxList.pollFirst();
            lastNumIdxList.add(removeIndex);
            set.pollFirst();
            if (set.size() == 0) {
                indexes.remove(val);
            }
            return true;
        }

        public int getRandom() {
            return nums.get((int) (Math.random() * (nums.size())));
        }
    }
}

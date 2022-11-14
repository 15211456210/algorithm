package com.zcp.part5.c351to400;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZCP
 * @title: C398
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/random-pick-index/description/
 * @date 2022/11/12 11:13
 */
public class C398 {

    class Solution {
        class Node {
            List<Integer> list;
            int index;

            public Node() {
                list = new ArrayList<>();
            }

            public int get() {
                index = (index + 1 == list.size()) ? 0 : index + 1;
                return list.get(index);
            }
        }

        HashMap<Integer, Node> map;

        public Solution(int[] nums) {
            map = new HashMap<Integer, Node>();
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], new Node());
                }
                Node node = map.get(nums[i]);
                node.list.add(i);
            }
        }

        public int pick(int target) {
            return map.get(target).get();
        }
    }

}

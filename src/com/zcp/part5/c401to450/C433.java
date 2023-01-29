package com.zcp.part5.c401to450;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author ZCP
 * @title: C433
 * @projectName algorithm
 * @description: https://leetcode.com/problems/minimum-genetic-mutation/
 * @date 2023/1/28 13:43
 */
public class C433 {

    public int minMutation(String startGene, String endGene, String[] bank) {
        HashMap<String, Set<String>> map = genMap(startGene, bank);
        HashSet<String> visited = new HashSet<>();
        LinkedList<String> queue = new LinkedList<>();
        visited.add(startGene);
        queue.offer(startGene);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (endGene.equals(poll)) {
                    return level;
                }
                Set<String> next = map.getOrDefault(poll, new HashSet<>());
                for (String s : next) {
                    if (visited.contains(s)) {
                        continue;
                    }
                    visited.add(s);
                    queue.offer(s);
                }
            }
            level++;
        }
        return -1;
    }

    private HashMap<String, Set<String>> genMap(String startGene, String[] bank) {
        HashMap<String, Set<String>> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        for (String next : bank) {
            int diffCount = 0;
            for (int i = 0; i < 8; i++) {
                diffCount += startGene.charAt(i) == next.charAt(i) ? 0 : 1;
            }
            if (diffCount == 1) {
                set.add(next);
            }
        }
        map.put(startGene, set);
        for (String s : bank) {
            set = new HashSet<>();
            for (String next : bank) {
                int diffCount = 0;
                for (int i = 0; i < 8; i++) {
                    diffCount += s.charAt(i) == next.charAt(i) ? 0 : 1;
                }
                if (diffCount == 1) {
                    set.add(next);
                }
            }
            map.put(s, set);
        }

        return map;
    }
}

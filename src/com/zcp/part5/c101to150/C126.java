package com.zcp.part5.c101to150;

import java.util.*;

/**
 * @author ZCP
 * @title: C126
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/word-ladder-ii/submissions/
 * @date 2022/9/2 11:03
 */
public class C126 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return res;
        }
        dict.remove(beginWord);

        Map<String, Integer> steps = new HashMap<>();
        steps.put(beginWord, 0);
        Map<String, Set<String>> from = new HashMap<>();
        boolean found = bfs(beginWord, endWord, dict, steps, from);

        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            dfs(from, path, beginWord, endWord, res);
        }
        return res;
    }


    private boolean bfs(String beginWord, String endWord, Set<String> dict, Map<String, Integer> steps, Map<String, Set<String>> from) {
        int wordLen = beginWord.length();
        int step = 0;
        boolean found = false;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] charArray = currWord.toCharArray();
                for (int j = 0; j < wordLen; j++) {
                    char origin = charArray[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        charArray[j] = c;
                        String nextWord = String.valueOf(charArray);
                        if (steps.containsKey(nextWord) && steps.get(nextWord) == step) {
                            from.get(nextWord).add(currWord);
                        }

                        if (!dict.contains(nextWord)) {
                            continue;
                        }
                        dict.remove(nextWord);
                        queue.offer(nextWord);
                        from.putIfAbsent(nextWord, new HashSet<>());
                        from.get(nextWord).add(currWord);
                        steps.put(nextWord, step);
                        if (nextWord.equals(endWord)) {
                            found = true;
                        }
                    }
                    charArray[j] = origin;
                }
            }
            if (found) {
                break;
            }
        }
        return found;
    }

    private void dfs(Map<String, Set<String>> from, Deque<String> path, String beginWord, String cur, List<List<String>> res) {
        if (cur.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String precursor : from.get(cur)) {
            path.addFirst(precursor);
            dfs(from, path, beginWord, precursor, res);
            path.removeFirst();
        }
    }

    /**
     * "hit"
     * "cog"
     * ["hot","dot","dog","lot","log","cog"]
     * <p>
     * <p>
     * "lost"
     * "miss"
     * ["most","mist","miss","lost","fist","fish"]
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        list.add("most");
        list.add("mist");
        list.add("miss");
        list.add("lost");
        list.add("fist");
        list.add("fish");
        System.out.println(new C126().findLadders("lost", "miss", list));
    }
}

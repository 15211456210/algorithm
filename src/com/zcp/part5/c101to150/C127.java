package com.zcp.part5.c101to150;

import java.util.*;

/**
 * @author ZCP
 * @title: C127
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/word-ladder/
 * @date 2022/9/5 11:22
 */
public class C127 {

    static Queue<String> bQueue = new LinkedList<>();
    static Queue<String> eQueue = new LinkedList<>();


    static Map<String, Integer> bExist = new HashMap<>();
    static Map<String, Integer> eExist = new HashMap<>();

    static Set<String> visited = new HashSet<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }

        set.remove(beginWord);

        bQueue.clear();
        visited.clear();
        bQueue.offer(beginWord);
        visited.add(beginWord);
        int step = 0;
        while (!bQueue.isEmpty()) {
            step++;
            int size = bQueue.size();

            for (int i = 0; i < size; i++) {
                String poll = bQueue.poll();
                char[] charArray = poll.toCharArray();
                for (int m = 0; m < charArray.length; m++) {
                    char orgin = charArray[m];
                    for (char w = 'a'; w <= 'z'; ++w) {
                        charArray[m] = w;
                        String newWord = new String(charArray);
                        if (newWord.equals(poll)) {
                            continue;
                        }
                        if (set.contains(newWord)) {
                            if (newWord.equals(endWord)) {
                                return step + 1;
                            }

                            if (!visited.contains(newWord)) {
                                visited.add(newWord);
                                bQueue.offer(newWord);
                            }
                        }
                    }
                    charArray[m] = orgin;
                }

            }

        }

        return 0;

    }

//    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        if (!wordList.contains(endWord)) {
//            return 0;
//        }
//        bQueue.clear();
//        eQueue.clear();
//
//        bExist.clear();
//        eExist.clear();
//
//
//        int deep = 1;
//
//        bQueue.offer(beginWord);
//        eQueue.offer(endWord);
//
//        bExist.put(beginWord, 1);
//        eExist.put(endWord, 1);
//
//
//        int size;
//        while (!bQueue.isEmpty() || !eQueue.isEmpty()) {
//            size = bQueue.size();
//            for (int i = 0; i < size; i++) {
//                String poll = bQueue.poll();
//                deep = bExist.get(poll);
//                char[] chars = poll.toCharArray();
//                for (int n = 0; n < poll.length(); n++) {
//                    char orgin = poll.charAt(n);
//                    for (char c = 'a'; c <= 'z'; c++) {
//                        chars[n] = c;
//                        String newWord = String.valueOf(chars);
//                        if (wordList.contains(newWord) && !bExist.containsKey(newWord)) {
//                            bQueue.offer(newWord);
//                            bExist.put(newWord, deep + 1);
//                            if (eExist.containsKey(newWord)) {
//                                return bExist.get(newWord) + eExist.get(newWord) - 1;
//                            }
//                        }
//
//                    }
//                    chars[n] = orgin;
//                }
//            }
//
//            size = eQueue.size();
//            for (int i = 0; i < size; i++) {
//                String poll = eQueue.poll();
//                deep = eExist.get(poll);
//                char[] chars = poll.toCharArray();
//                for (int n = 0; n < poll.length(); n++) {
//                    char orgin = poll.charAt(n);
//                    for (char c = 'a'; c <= 'z'; c++) {
//                        chars[n] = c;
//                        String newWord = String.valueOf(chars);
//                        if (wordList.contains(newWord) && !eExist.containsKey(newWord)) {
//                            eQueue.offer(newWord);
//                            eExist.put(newWord, deep + 1);
//                            if (bExist.containsKey(newWord)) {
//                                return bExist.get(newWord) + eExist.get(newWord) - 1;
//                            }
//                        }
//
//                    }
//                    chars[n] = orgin;
//                }
//            }
//
//
//        }
//        return 0;
//
//    }

    /**
     * "hot"
     * "dog"
     * ["hot","dog"]
     * <p>
     * "hit"
     * "cog"
     * ["hot","dot","dog","lot","log","cog"]
     * <p>
     * "hot"
     * "dog"
     * ["hot","dog","dot"]
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("hot");
        strings.add("dog");
        strings.add("dot");
//        strings.add("lot");
//        strings.add("log");
//        strings.add("cog");
        int i = new C127().ladderLength("hot", "dog", strings);

        System.out.println(i);


    }
}

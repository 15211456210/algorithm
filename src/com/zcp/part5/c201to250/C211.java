package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C211
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/design-add-and-search-words-data-structure/submissions/
 * @date 2022/9/16 9:38
 */
public class C211 {


    class WordDictionary {

        Node tree;

        class Node {
            boolean isEnd;
            Node[] next;

            public Node() {
                next = new Node[26];
            }
        }

        public WordDictionary() {
            this.tree = new Node();
        }

        public void addWord(String word) {
            Node cur = tree;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (cur.next[idx] == null) {
                    cur.next[idx] = new Node();
                }
                cur = cur.next[idx];
            }
            cur.isEnd = true;
        }

        public boolean search(String word) {
            return search(word, 0, tree);
        }

        public boolean search(String word, int index, Node cur) {
            if (index == word.length()) {
                return cur == null ? false : cur.isEnd;
            }
            if (cur == null) {
                return false;
            }
            boolean find = false;
            if (word.charAt(index) == '.') {
                for (int n = 0; n < 26; n++) {
                    if (cur.next[n] != null) {
                        find |= search(word, index + 1, cur.next[n]);
                    }
                }
            } else {
                find |= search(word, index + 1, cur.next[word.charAt(index) - 'a']);
            }

            return find;
        }
    }


}

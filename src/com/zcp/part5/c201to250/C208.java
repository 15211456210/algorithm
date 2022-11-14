package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C208
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/implement-trie-prefix-tree/submissions/
 * @date 2022/9/16 9:18
 */
public class C208 {

    class Trie {

        class Node {
            Node[] next;
            boolean end;

            public Node() {
                this.next = new Node[26];
            }
        }

        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            char[] cs = word.toCharArray();
            Node cur = root;
            for (int i = 0; i < cs.length; i++) {
                if (cur.next[cs[i] - 'a'] == null) {
                    cur.next[cs[i] - 'a'] = new Node();
                }
                cur = cur.next[cs[i] - 'a'];
            }
            cur.end = true;
        }

        public boolean search(String word) {
            char[] cs = word.toCharArray();
            Node cur = root;

            for (int i = 0; i < cs.length; i++) {
                if (cur.next[cs[i] - 'a'] == null) {
                    return false;
                }
                cur = cur.next[cs[i] - 'a'];
            }
            return cur.end;
        }

        public boolean startsWith(String prefix) {
            char[] cs = prefix.toCharArray();
            Node cur = root;

            for (int i = 0; i < cs.length; i++) {
                if (cur.next[cs[i] - 'a'] == null) {
                    return false;
                }
                cur = cur.next[cs[i] - 'a'];
            }
            return true;
        }
    }
}

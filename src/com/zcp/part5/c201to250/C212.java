package com.zcp.part5.c201to250;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ZCP
 * @title: C212
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/word-search-ii/submissions/
 * @date 2022/9/17 16:20
 */
public class C212 {


    class Trie {
        boolean isEnd;
        Trie[] nexts;

        public Trie() {
            this.nexts = new Trie[26];
        }

    }


    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        buildTrie(trie, words);
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                process(board, i, j, trie, ans, "");
            }
        }
        return new ArrayList<>(ans);
    }

    public void process(char[][] board, int i, int j, Trie trie, Set<String> ans, String path) {
        if (trie.isEnd) {
            ans.add(path);
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#') {
            return;
        }
        char c = board[i][j];
        if (trie.nexts[c - 'a'] != null) {
            board[i][j] = '#';
            process(board, i + 1, j, trie.nexts[c - 'a'], ans, path + c);
            process(board, i, j + 1, trie.nexts[c - 'a'], ans, path + c);
            process(board, i - 1, j, trie.nexts[c - 'a'], ans, path + c);
            process(board, i, j - 1, trie.nexts[c - 'a'], ans, path + c);
            board[i][j] = c;
        }
    }

    private void buildTrie(Trie trie, String[] words) {
        for (String word : words) {
            Trie cur = trie;
            for (int i = 0; i < word.length(); i++) {
                int idx = word.charAt(i) - 'a';
                if (cur.nexts[idx] == null) {
                    cur.nexts[idx] = new Trie();
                }
                cur = cur.nexts[idx];
            }
            cur.isEnd = true;
        }
    }

    public static void main(String[] args) {
        List<String> words = new C212().findWords(new char[][]{{'a'}}, new String[]{"a"});

        System.out.println(words);

    }
}

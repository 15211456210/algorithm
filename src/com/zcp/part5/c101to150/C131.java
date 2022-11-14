package com.zcp.part5.c101to150;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C131
 * @projectName algorithm
 * @description: https://leetcode.com/problems/palindrome-partitioning/
 * @date 2022/9/5 19:32
 */
public class C131 {

    public List<List<String>> partition(String s) {
        ArrayList<List<String>> ans = new ArrayList<>();

        process(s, 0, new ArrayList<>(), ans);
        return ans;
    }


    public void process(String s, int i, List<String> path, List<List<String>> ans) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int end = i + 1; end <= s.length(); end++) {
            String substring = s.substring(i, end);
            if (isPalindrome(substring)) {
                path.add(substring);
                process(s, end, path, ans);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {

        if (s == null || s.length() <= 1) {
            return true;
        }
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}

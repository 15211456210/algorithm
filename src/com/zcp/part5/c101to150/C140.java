package com.zcp.part5.c101to150;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ZCP
 * @title: C140
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/word-break-ii/submissions/
 * @date 2022/9/7 9:36
 */
public class C140 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        ArrayList<String> ans = new ArrayList<>();
        process(s, 0, set, "", ans);
        return ans;
    }

    public void process(String s, int i, Set<String> set, String path, List<String> ans) {
        if (i == s.length()  ) {
            if (path.length() > 0){
                ans.add(path.substring(0, path.length() - 1));
            }
            return;
        }

        for (int end = i + 1; end <= s.length(); end++) {
            String sub = s.substring(i, end);
            if (set.contains(sub)) {
                path += sub + " ";
                process(s, end, set, path, ans);
                path = path.substring(0, path.length() - sub.length() - 1);
            }
        }

    }
}

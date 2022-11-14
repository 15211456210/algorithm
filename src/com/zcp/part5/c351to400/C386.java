package com.zcp.part5.c351to400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C386
 * @projectName algorithm
 * @description: https://leetcode.com/problems/lexicographical-numbers/submissions/
 * @date 2022/11/2 10:34
 */
public class C386 {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs("" + i, n, ans);
        }
        return ans;
    }

    private void dfs(String path, int n, List<Integer> ans) {
        int num = Integer.valueOf(path);
        if (num > n) {
            return;
        }
        ans.add(num);
        for (int i = 0; i <= 9; ++i) {
            dfs(path + i, n, ans);
        }
    }

}

package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZCP
 * @title: c051
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/n-queens/submissions/
 * @date 2022/8/28 16:30
 */
public class c051 {

    public List<List<String>> solveNQueens(int n) {
        ArrayList<List<String>> ans = new ArrayList<>();
        if (n < 1) {
            return ans;
        }
        process(n, 0, new ArrayList<String>(), ans);
        return ans;
    }

    public void process(int n, int index, List<String> list, List<List<String>> ans) {
        if (index == n) {
            ans.add(Arrays.asList(list.toArray(new String[0])));
        }
        for (int i = 0; i < n; i++) {
            if (check(i, index, list)) {
                char[] c = new char[n];
                Arrays.fill(c, '.');
                //在该点防止皇后
                c[i] = 'Q';
                list.add(String.valueOf(c));
                process(n, index + 1, list, ans);
                //分支走完后需要恢复现场
                list.remove(index);
            }
        }
    }

    /**
     * 判断当前[index,i]位置是否可以放置皇后
     *
     * @param i
     * @param index
     * @param list
     * @return
     */
    private boolean check(int i, int index, List<String> list) {
        for (int k = 0; k < list.size(); k++) {
            String s = list.get(k);
            int qCol = s.indexOf('Q');
            if (qCol == i || (Math.abs(index - k) == Math.abs(i - qCol))) {
                //列、斜对角线任何一处冲突都算false
                return false;
            }
        }
        return true;
    }
}

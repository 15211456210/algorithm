package com.zcp.part5.c101to150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZCP
 * @title: C118
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/pascals-triangle/submissions/
 * @date 2022/9/1 16:36
 */
public class C118 {

    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> ans = new ArrayList<>(numRows);
        ans.add(Arrays.asList(1));
        int first;
        int second;
        for (int i = 2; i <= numRows; i++) {
            List<Integer> preNums = ans.get(i - 2);
            ArrayList<Integer> curNums = new ArrayList<>(i);
            for (int j = 0; j < i; j++) {
                first = j - 1 >= 0 ? preNums.get(j - 1) : 0;
                second = j < preNums.size() ? preNums.get(j) : 0;
                curNums.add(first + second);
            }
            ans.add(curNums);
        }
        return ans;

    }
}

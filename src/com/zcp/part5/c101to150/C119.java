package com.zcp.part5.c101to150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZCP
 * @title: C119
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/pascals-triangle-ii/submissions/
 * @date 2022/9/1 17:00
 */
public class C119 {

    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> ans = new ArrayList<>(rowIndex + 1);
        ans.add(1);
        if (rowIndex == 0) {
            return ans;
        }
        int first;
        int second;
        for (int i = 1; i <= rowIndex; i++) {
            ArrayList<Integer> curNums = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                first = j - 1 >= 0 ? ans.get(j - 1) : 0;
                second = j < ans.size() ? ans.get(j) : 0;
                curNums.add(first + second);
            }
            ans = curNums;

        }
        return ans;
    }

}

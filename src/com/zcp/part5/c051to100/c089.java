package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c089
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/gray-code/submissions/
 * @date 2022/8/31 9:29
 */
public class c089 {

    public List<Integer> grayCode(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        if (n == 0) {
            return list;
        }
        list.add(1);

        int len;
        for (int i = 1; i < n; i++) {
            len = list.size();
            for (int k = len - 1; k >= 0; k--) {
                list.add(list.get(k) | (1 << i));
            }
        }
        return list;
    }
}

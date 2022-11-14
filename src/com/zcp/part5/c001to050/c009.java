package com.zcp.part5.c001to050;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c009
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/palindrome-number/
 * @date 2022/8/25 13:30
 */
public class c009 {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        while (x > 0) {
            list.add(x % 10);
            x /= 10;
        }
        for (int i = 0, j = list.size() - 1; i <= j; ) {
            if (!list.get(i++).equals(list.get(j--))) {
                return false;
            }
        }
        return true;
    }
}

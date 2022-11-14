package com.zcp.part5.c001to050;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author ZCP
 * @title: c017
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * @date 2022/8/25 15:32
 */
public class c017 {

    private String[] cards = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        HashSet<String> set = new HashSet<>();
        set.add("");

        char[] chars = digits.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String card = cards[chars[i] - '0'];//"abc"
            HashSet<String> newSet = new HashSet<>();
            for (int j = 0; j < card.length(); j++) {
                for (String oldStr : set) {
                    newSet.add(oldStr + card.charAt(j));
                }
            }
            set = newSet;
        }

        return new ArrayList<String>(set);
    }
}

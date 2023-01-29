package com.zcp.part5.c401to450;

import java.util.HashMap;

/**
 * @author ZCP
 * @title: C420
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/strong-password-checker/
 * @date 2022/11/25 8:54
 */
public class C420 {
    HashMap<String, Integer> dp = new HashMap<>();

    public int strongPasswordChecker(String password) {
        if (dp.containsKey(password)) {
            return dp.get(password);
        }
        int lenCost = lenCost(password);
        int charTypeCost = charTypeCost(password);
        int[] dupIdx = dupCost(password);
        if ((lenCost | charTypeCost | dupIdx[0]) == 0) {
            return 0;
        }
        if (lenCost > 0) {
            if (password.length() < 6) {
                dp.put(password, Math.max(lenCost, charTypeCost));
                return Math.max(lenCost, charTypeCost);
            }
            if (password.length() > 20) {
                if (dupIdx[0] == 0) {
                    dp.put(password, lenCost + charTypeCost);
                    return lenCost + charTypeCost;
                } else {
                    int minCost = 50;
                    for (int i = 0; i < dupIdx[0]; i++) {
                        minCost = Math.min(minCost, strongPasswordChecker(password.substring(0, dupIdx[i + 1]).concat(password.substring(dupIdx[i + 1] + 1))));
                    }

                    dp.put(password, 1 + minCost);
                    return 1 + minCost;
                }

            }
        }
        dp.put(password, Math.max(dupIdx[0], charTypeCost));
        return dp.get(password);

    }

    // 密码重复字符修正代价
    public int[] dupCost(String password) {
        int count = 0;
        char pre = 0;
        int[] dupIdx = new int[50];
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            count = pre == c ? count + 1 : 1;
            if (count == 3) {
                dupIdx[0]++;
                dupIdx[dupIdx[0]] = i;
                count = 0;
            }
            pre = c;
        }
        return dupIdx;
    }

    // 密码种类修正代价
    public int charTypeCost(String password) {
        int small = 1, big = 1, num = 1;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (small > 0 && c >= 'a' && c <= 'z') {
                small--;
            }
            if (big > 0 && c >= 'A' && c <= 'Z') {
                big--;
            }
            if (num > 0 && c >= '0' && c <= '9') {
                num--;
            }
        }
        return small + big + num;
    }

    // 长度修正代价
    public int lenCost(String password) {
        int len = password.length();
        return Math.max(0, Math.max(6 - len, len - 20));
    }
}

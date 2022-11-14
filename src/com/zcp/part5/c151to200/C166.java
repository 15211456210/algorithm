package com.zcp.part5.c151to200;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author ZCP
 * @title: C166
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/fraction-to-recurring-decimal/submissions/
 * @date 2022/9/8 16:56
 */
public class C166 {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        boolean isNegative = ((numerator >>> 31) ^ (denominator >>> 31)) != 0;
        long lnumerator = numerator > 0 ? (long) numerator : -(long) numerator;
        long ldenominator = denominator > 0 ? (long) denominator : -(long) denominator;

        long res = lnumerator / ldenominator;
        long mod = lnumerator % ldenominator;
        LinkedList<String> ans = new LinkedList<>();
        ans.addLast(res + "");
        if (mod != 0) {
            ans.addLast(".");
        }
        int idx = 2;
        HashMap<Long, Integer> map = new HashMap<>();

        while (mod != 0) {
            if (map.containsKey(mod)) {
                Integer begin = map.get(mod);
                ans.add(begin, "(");
                ans.addLast(")");
                break;
            }
            map.put(mod, idx);
            ++idx;
            mod *= 10;
            ans.addLast(mod / ldenominator + "");
            mod = mod % denominator;

        }
        if (isNegative) {
            ans.addFirst("-");
        }
        StringBuilder sb = new StringBuilder();
        for (String num : ans) {
            sb.append(num);
        }
        return sb.toString();
    }
}

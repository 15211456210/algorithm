package com.zcp.part5.c201to250;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C233
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/number-of-digit-one/
 * @date 2022/9/20 14:33
 */
public class C233 {


    public int countDigitOne(int n) {
        int ans = 0;

        int k = 1;
        int m = 10;
        for (int i = 0; i < 10; i++) {
            ans += (n / m) * k;
            if (((n % m) / k) == 1) {
                ans += (n % m) % k + 1;
            } else if ((n % m) / k > 1) {
                ans += k;
            }
            k *= 10;
            m *= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        new C233().countDigitOne(321);


    }
}

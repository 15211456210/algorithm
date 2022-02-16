package com.zcp.part4.day02;

/**
 * 012.172.阶乘后的零 [E]
 * #数学技巧
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 */
public class Code07_TrailingZeroes {

    /**
     * 根据对数器找规律
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int ans = 0;
        int mod = 5;
        while (n / mod > 0) {
            ans += n / mod;
            mod *= 5;
        }
        return ans;
    }
}

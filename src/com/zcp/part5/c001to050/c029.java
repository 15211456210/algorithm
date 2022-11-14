package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c029
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/divide-two-integers/
 * @date 2022/8/25 15:50
 */
public class c029 {

    /**
     * 25/3 = 8
     * <p>
     * 25>3
     * 25>2*3
     * 25>4*3
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        // 是否是负数
        boolean isItNegative = ((dividend >>> 31) ^ (divisor >>> 31)) == 1;
        long ans = 0;
        long dividends = dividend;
        long divisors = divisor;
        dividends = Math.abs(dividends);
        divisors = Math.abs(divisors);
        while (dividends >= divisors) {
            long n = 1;
            while (dividends >= (n * 2) * divisors) {
                n *= 2;
            }
            ans += n;
            dividends -= n * divisors;
        }

        if (!isItNegative && ans >= 2147483648L) {
            return 2147483647;
        }
        return isItNegative ? (int) -ans : (int) ans;

    }

    public static void main(String[] args) {
        System.out.println(new c029().divide(-1010369383, -2147483648));
    }


}

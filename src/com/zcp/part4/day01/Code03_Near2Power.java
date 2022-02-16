package com.zcp.part4.day01;

/**
 * [[返回离非负整数num最近的2的某次方]]
 * 给定一个非负整数num，如何不用循环语句，返回>=num，并且离num最近的，2的某次方
 */
public class Code03_Near2Power {

    /**
     * 思路：
     * 将二进制位设置成1 最后+1就是结果
     *
     * @param n
     * @return
     */
    public static final int tableSizeFor(int n) {
        n--;
        n = n >> 1 | n;
        n = n >> 2 | n;
        n = n >> 4 | n;
        n = n >> 8 | n;
        n = n >> 16 | n;//为什么16停了，因为int类型最高32位
        return n+1;
    }

    public static void main(String[] args) {
        int cap = 0;
        System.out.println(tableSizeFor(cap));
    }

}
package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C458
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/poor-pigs/
 * @date 2023/2/6 18:47
 */
public class C458 {


    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return (int) Math.ceil(Math.log(buckets) / Math.log(minutesToTest / minutesToDie + 1) - 1e-5);
    }

    public static void main(String[] args) {
        System.out.println(new C458().poorPigs(125, 1, 4));
    }
}

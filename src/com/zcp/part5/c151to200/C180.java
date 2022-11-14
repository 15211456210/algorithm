package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C180
 * @projectName algorithm
 * @description: https://leetcode.com/problems/consecutive-numbers/submissions/
 * @date 2022/9/10 12:47
 */
public class C180 {

    /**
     *select
     * distinct(Logs1.Num) as "ConsecutiveNums"
     *
     * from
     * Logs Logs1,
     * Logs Logs2,
     * Logs Logs3
     * where Logs1.id = Logs2.id -1
     * and Logs2.id = Logs3.id -1
     * and Logs1.Num = Logs2.Num
     * and Logs2.Num = Logs3.Num
     *
     */
}

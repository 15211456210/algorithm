package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C197
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/rising-temperature/submissions/
 * @date 2022/9/15 14:28
 */
public class C197 {

    /**
     *select
     * w1.id
     * from Weather w1,Weather w2
     * where w1.recordDate = date_add(w2.recordDate, INTERVAL 1 day) and w1.Temperature > w2.Temperature
     *
     */
}

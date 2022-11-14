package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C181
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/employees-earning-more-than-their-managers/submissions/
 * @date 2022/9/10 12:50
 */
public class C181 {

    /**
     *
     * select
     * e1.name  as "Employee"
     * from Employee e1
     * left join Employee e2 on e1.managerId = e2.id
     * where e1.salary  > e2.salary
     *
     */
}

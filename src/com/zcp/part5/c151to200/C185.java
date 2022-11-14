package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C185
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/department-top-three-salaries/submissions/
 * @date 2022/9/13 16:14
 */
public class C185 {

    /**
     * select
     * dep.name as "Department" ,
     * emp.name as "Employee",
     * emp.salary as "Salary"
     * from Employee emp
     * left join Department dep on dep.id = emp.departmentId
     * where (
     *     select count(distinct(salary))
     *     from Employee
     *     where
     *     departmentId = emp.departmentId
     *     and salary >= emp.salary
     *     ) <= 3
     */
}

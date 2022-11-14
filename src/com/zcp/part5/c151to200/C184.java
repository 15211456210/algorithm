package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C184
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/department-highest-salary/solution/
 * @date 2022/9/13 15:59
 */
public class C184 {

    /**
     * select
     * dep.name as "Department",
     * emp.name as "Employee",
     * emp.salary as "Salary"
     * from Department dep
     * left join Employee emp on emp.departmentId = dep.id
     * where emp.salary = (
     *     select max(salary)
     *     from Employee
     *     where departmentId = dep.id
     * )
     */
}

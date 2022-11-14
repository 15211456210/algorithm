package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C177
 * @projectName algorithm
 * @description: https://leetcode.com/problems/nth-highest-salary/submissions/
 * @date 2022/9/10 12:20
 */
public class C177 {

    /**
     *
     *
     * CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
     * BEGIN
     *     SET N := N-1;
     *   RETURN (
     *       # Write your MySQL query statement below.
     *       SELECT
     *             salary
     *       FROM
     *             employee
     *       GROUP BY
     *             salary
     *       ORDER BY
     *             salary DESC
     *       LIMIT N, 1
     *   );
     * END
     */
}

package com.kamarkaka.leetcode;

/***
 * 177. Nth Highest Salary
 * Table: Employee
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | id          | int  |
 * | salary      | int  |
 * +-------------+------+
 * id is the primary key (column with unique values) for this table.
 * Each row of this table contains information about the salary of an employee.
 * Write a solution to find the nth highest salary from the Employee table. If there is no nth highest salary, return
 * null.
 * The result format is in the following example.
 * Example 1:
 * Input:
 * Employee table:
 * +----+--------+
 * | id | salary |
 * +----+--------+
 * | 1  | 100    |
 * | 2  | 200    |
 * | 3  | 300    |
 * +----+--------+
 * n = 2
 * Output:
 * +------------------------+
 * | getNthHighestSalary(2) |
 * +------------------------+
 * | 200                    |
 * +------------------------+
 * Example 2:
 * Input:
 * Employee table:
 * +----+--------+
 * | id | salary |
 * +----+--------+
 * | 1  | 100    |
 * +----+--------+
 * n = 2
 * Output:
 * +------------------------+
 * | getNthHighestSalary(2) |
 * +------------------------+
 * | null                   |
 * +------------------------+
 */
public class LC0177 {
    /*
    CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
    BEGIN
      # For some reason, you can't use something like OFFSET N - 1. So, I am declaring a variable M and instead
      # assigning its value to N - 1 if N is not 0
      DECLARE M INT;

      # Variable M is used in the offset clause. So, its value can't be negative
      IF N = 0 THEN SET M = 0;
      ELSE SET M = N - 1;
      END IF;

      RETURN (
        # Write your MySQL query statement below.
        SELECT(
          IFNULL(
            (
              SELECT DISTINCT Salary
              FROM Employee
              ORDER BY Salary DESC
              LIMIT 1 OFFSET M ), # Show 1 one row after offsetting m rows
            NULL)
        )
      );
      END
     */
}

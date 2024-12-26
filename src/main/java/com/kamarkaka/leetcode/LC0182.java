package com.kamarkaka.leetcode;

/***
 * 182. Duplicate Emails
 * Table: Person
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | id          | int     |
 * | email       | varchar |
 * +-------------+---------+
 * id is the primary key (column with unique values) for this table.
 * Each row of this table contains an email. The emails will not contain uppercase letters.
 * Write a solution to report all the duplicate emails. Note that it's guaranteed that the email field is not NULL.
 * Return the result table in any order.
 * The result format is in the following example.
 * Example 1:
 * Input:
 * Person table:
 * +----+---------+
 * | id | email   |
 * +----+---------+
 * | 1  | a@b.com |
 * | 2  | c@d.com |
 * | 3  | a@b.com |
 * +----+---------+
 * Output:
 * +---------+
 * | Email   |
 * +---------+
 * | a@b.com |
 * +---------+
 * Explanation: a@b.com is repeated two times.
 */
public class LC0182 {
    /*
    # Write your MySQL query statement below
    select Email from Person group by Email having count(Email) > 1
     */
}

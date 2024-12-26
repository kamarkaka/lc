package com.kamarkaka.leetcode;

import java.util.HashSet;
import java.util.Set;

/***
 * 202. Happy Number
 * Write an algorithm to determine if a number n is happy.
 * A happy number is a number defined by the following process:
 *   Starting with any positive integer, replace the number by the sum of the squares of its digits.
 *   Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not
 *   include 1.
 *   Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * Example 1:
 *   Input: n = 19
 *   Output: true
 *   Explanation:
 *   12 + 92 = 82
 *   82 + 22 = 68
 *   62 + 82 = 100
 *   12 + 02 + 02 = 1
 * Example 2:
 *   Input: n = 2
 *   Output: false
 * Constraints:
 *   1 <= n <= 2^31 - 1
 */
public class LC0202 {
    public boolean isHappy(int n) {
        int sum, num = n;
        Set<Integer> set = new HashSet<>();

        while (true) {
            sum = 0;

            while (num > 0) {
                int digit = num % 10;
                sum += digit * digit;
                num /= 10;
            }

            num = sum;
            if (num == 1) return true;
            if (set.contains(num)) return false;
            else set.add(num);
        }
    }
}

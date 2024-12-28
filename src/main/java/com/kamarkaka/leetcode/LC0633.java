package com.kamarkaka.leetcode;

/***
 * 633. Sum of Square Numbers
 * Given a non-negative integer c, decide whether there're two integers a and b such that a^2 + b^2 = c.
 * Example 1:
 *   Input: c = 5
 *   Output: true
 *   Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 *   Input: c = 3
 *   Output: false
 * Constraints:
 *   0 <= c <= 2^31 - 1
 */
public class LC0633 {
    public boolean judgeSquareSum(int c) {
        int sc = (int) Math.sqrt(c);

        for (int b = 0; b <= sc; b++) {
            int a = (int) Math.sqrt(c - b * b);
            if (a * a + b * b == c) return true;
        }

        return false;
    }
}

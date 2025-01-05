package com.kamarkaka.leetcode;

/***
 * 371. Sum of Two Integers
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 * Example 1:
 *   Input: a = 1, b = 2
 *   Output: 3
 * Example 2:
 *   Input: a = 2, b = 3
 *   Output: 5
 * Constraints:
 *   -1000 <= a, b <= 1000
 */
public class LC0371 {
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            int sum = a ^ b;
            int carry = (a & b) << 1;

            a = sum;
            b = carry;
        }

        return a;
    }
}

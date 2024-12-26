package com.kamarkaka.leetcode;

/***
 * 342. Power of Four
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 * Example 1:
 *   Input: n = 16
 *   Output: true
 * Example 2:
 *   Input: n = 5
 *   Output: false
 * Example 3:
 *   Input: n = 1
 *   Output: true
 * Constraints:
 *   -2^31 <= n <= 2^31 - 1
 * Follow up: Could you solve it without loops/recursion?
 */
public class LC0342 {
    public boolean isPowerOfFour(int n) {
        if (n == 1) return true;
        if (n < 4) return false;

        while (n > 1) {
            if (n % 4 != 0) return false;
            n = n / 4;
        }

        return true;
    }
}

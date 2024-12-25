package com.kamarkaka.leetcode;

/***
 * 7. Reverse Integer
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the
 * signed 32-bit integer range [-231, 231 - 1], then return 0.
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * Example 1:
 *   Input: x = 123
 *   Output: 321
 * Example 2:
 *   Input: x = -123
 *   Output: -321
 * Example 3:
 *   Input: x = 120
 *   Output: 21
 * Constraints:
 *   -2^31 <= x <= 2^31 - 1
 */
public class LC0007 {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            if (result > 0 && result > (Integer.MAX_VALUE - digit) / 10) return 0;
            if (result < 0 && result < (Integer.MIN_VALUE - digit) / 10) return 0;

            result = 10 * result + digit;
        }
        return result;
    }
}

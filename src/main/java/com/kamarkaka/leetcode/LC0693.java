package com.kamarkaka.leetcode;

/***
 * 693. Binary Number with Alternating Bits
 * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have
 * different values.
 * Example 1:
 *   Input: n = 5
 *   Output: true
 *   Explanation: The binary representation of 5 is: 101
 * Example 2:
 *   Input: n = 7
 *   Output: false
 *   Explanation: The binary representation of 7 is: 111.
 * Example 3:
 *   Input: n = 11
 *   Output: false
 *   Explanation: The binary representation of 11 is: 1011.
 * Constraints:
 *   1 <= n <= 2^31 - 1
 */
public class LC0693 {
    public boolean hasAlternatingBits(int n) {
        int prevBit = n % 2;

        while (n > 0) {
            n /= 2;
            if (n % 2 == prevBit) return false;
            else prevBit = n % 2;
        }

        return true;
    }
}

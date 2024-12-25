package com.kamarkaka.leetcode;

/***
 * 1318. Minimum Flips to Make a OR b Equal to c
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make
 * ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.
 * Example 1:
 *   Input: a = 2, b = 6, c = 5
 *   Output: 3
 *   Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
 * Example 2:
 *   Input: a = 4, b = 2, c = 7
 *   Output: 1
 * Example 3:
 *   Input: a = 1, b = 2, c = 3
 *   Output: 0
 * Constraints:
 *   1 <= a <= 10^9
 *   1 <= b <= 10^9
 *   1 <= c <= 10^9
 */
public class LC1318 {
    public int minFlips(int a, int b, int c) {
        int res = 0;
        while (a > 0 || b > 0) {
            if (((a & 1) | (b & 1)) != (c & 1)) {
                // 00 -> 1: 1
                // 01 -> 0: 1
                // 10 -> 0: 1
                // 11 -> 0: 2
                if (((a & 1) & (b & 1)) == 1) {
                    res += 2;
                } else {
                    res += 1;
                }
            }

            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
        }

        while (c > 0) {
            if ((c & 1) == 1) res++;
            c = c >> 1;
        }

        return res;

        // 0101
        // 0010
        // 1000
    }
}
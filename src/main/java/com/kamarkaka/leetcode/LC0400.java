package com.kamarkaka.leetcode;

/***
 * 400. Nth Digit
 * Given an integer n, return the nth digit of the infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].
 * Example 1:
 *   Input: n = 3
 *   Output: 3
 * Example 2:
 *   Input: n = 11
 *   Output: 0
 *   Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the
 *   number 10.
 * Constraints:
 *   1 <= n <= 2^31 - 1
 */
public class LC0400 {
    public int findNthDigit(int n) {
        if (n < 10) return n;

        int numOfDigits = 1;
        long multiplier = 1;

        while (n > numOfDigits * 9 * multiplier) {
            n -= numOfDigits * 9 * multiplier;
            numOfDigits++;
            multiplier *= 10;
        }

        int num = (int) multiplier + (n - 1) / numOfDigits;
        int index2 = numOfDigits - (n - 1) % numOfDigits - 1;
        int digit = num % 10;
        for (int i = 0; i < index2; i++) {
            num /= 10;
            digit = num % 10;
        }
        return digit;
    }
}

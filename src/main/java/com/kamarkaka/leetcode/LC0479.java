package com.kamarkaka.leetcode;

/***
 * 479. Largest Palindrome Product
 * Given an integer n, return the largest palindromic integer that can be represented as the product of two n-digits
 * integers. Since the answer can be very large, return it modulo 1337.
 * Example 1:
 *   Input: n = 2
 *   Output: 987
 *   Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
 * Example 2:
 *   Input: n = 1
 *   Output: 9
 * Constraints:
 *   1 <= n <= 8
 */
public class LC0479 {
    public int largestPalindrome(int n) {
        if (n == 1) return 9;

        long min = (long) Math.pow(10, n - 1);
        long max = (long) Math.pow(10, n) - 1;

        for (long i = max; i >= min; i--) {
            long product = makeParlindrome(i);

            for (long x = max; x * x >= product; x--) {
                if (product % x == 0) return (int) (product % 1337);
            }
        }

        return 0;
    }

    public long makeParlindrome(long num) {
        long res = num;
        while (num != 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }
}

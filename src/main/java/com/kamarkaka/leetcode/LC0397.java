package com.kamarkaka.leetcode;

/***
 * 397. Integer Replacement
 * Given a positive integer n, you can apply one of the following operations:
 *   If n is even, replace n with n / 2.
 *   If n is odd, replace n with either n + 1 or n - 1.
 * Return the minimum number of operations needed for n to become 1.
 * Example 1:
 *   Input: n = 8
 *   Output: 3
 *   Explanation: 8 -> 4 -> 2 -> 1
 * Example 2:
 *   Input: n = 7
 *   Output: 4
 *   Explanation: 7 -> 8 -> 4 -> 2 -> 1
 *   or 7 -> 6 -> 3 -> 2 -> 1
 * Example 3:
 *   Input: n = 4
 *   Output: 2
 * Constraints:
 *   1 <= n <= 2^31 - 1
 */
public class LC0397 {
    public int integerReplacement(int n) {
        long i = n;
        int cnt = 0;
        while (i > 3) {
            if (i % 2 == 0) {
                i /= 2;
            } else {
                if ((i + 1) % 4 == 0)
                    i++;
                else if ((i - 1) % 4 == 0)
                    i--;
                else
                    i--;
            }
            cnt++;
        }
        cnt += i-1;
        return cnt;
    }
}

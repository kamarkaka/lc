package com.kamarkaka.leetcode;

/***
 * 50. Pow(x, n)
 * Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
 * Example 1:
 *   Input: x = 2.00000, n = 10
 *   Output: 1024.00000
 * Example 2:
 *   Input: x = 2.10000, n = 3
 *   Output: 9.26100
 * Example 3:
 *   Input: x = 2.00000, n = -2
 *   Output: 0.25000
 *   Explanation: 2^-2 = 1/(2^2) = 1/4 = 0.25
 * Constraints:
 *   -100.0 < x < 100.0
 *   -2^31 <= n <= 2^31-1
 *   n is an integer.
 *   Either x is not zero or n > 0.
 *   -10^4 <= x^n <= 10^4
 */
public class LC0050 {
   public double myPow(double x, int n) {
      if (n == 0) return 1;
      if (x == 0) return 0;

      if (n < 0) {
         n = -n;
         x = 1.0d / x;
      }

      double ans = 1;
      while (n > 0) {
         if (n % 2 == 1) {
            ans *= x;
            n--;
         }
         n /= 2;
         x *= x;
      }
      return ans;
   }
}

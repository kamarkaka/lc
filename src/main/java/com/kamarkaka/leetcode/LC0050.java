package com.kamarkaka.leetcode;

/***
 * 50. Pow(x, n)
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * Example 1:
 *    Input: x = 2.00000, n = 10
 *    Output: 1024.00000
 *
 * Example 2:
 *    Input: x = 2.10000, n = 3
 *    Output: 9.26100
 *
 * Example 3:
 *    Input: x = 2.00000, n = -2
 *    Output: 0.25000
 *    Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 * Constraints:
 *    -100.0 < x < 100.0
 *    -2^31 <= n <= 2^31-1
 *    -10^4 <= xn <= 10^4
 */
public class LC0050 {
   public double myPow(double x, int n) {
      long N = n;
      if (N < 0) {
         x = 1 / x;
         N = -N;
      }
      double ans = 1;
      double current_product = x;
      for (long i = N; i > 0; i /= 2) {
         if ((i % 2) == 1) {
            ans = ans * current_product;
         }
         current_product = current_product * current_product;
      }
      return ans;
   }
}

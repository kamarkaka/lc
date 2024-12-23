package com.kamarkaka.leetcode;

/***
 * 343. Integer Break
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 * Return the maximum product you can get.
 *
 * Example 1:
 *    Input: n = 2
 *    Output: 1
 *    Explanation: 2 = 1 + 1, 1 × 1 = 1.
 *
 * Example 2:
 *    Input: n = 10
 *    Output: 36
 *    Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 *
 * Constraints:
 *    2 <= n <= 58
 */
public class LC0343 {
   public int integerBreak(int n) {
      int[] dp = new int[n+1];

      dp[0] = 0;
      if (n > 1) dp[1] = 0;

      for (int num = 2; num <= n; num++) {
         int product = 1;
         for (int num1 = 1; num1 <= num / 2; num1++) {
            int num2 = num - num1;
            product = Math.max(product, Math.max(num1, dp[num1]) * Math.max(num2, dp[num2]));
         }
         dp[num] = product;
      }
      return dp[n];
   }

   public static void run() {
      LC0343 solution = new LC0343();
      System.out.println(solution.integerBreak(2));
   }
}

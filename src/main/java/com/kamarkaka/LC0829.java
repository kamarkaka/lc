package com.kamarkaka;

/***
 * 829. Consecutive Numbers Sum
 * Given an integer n, return the number of ways you can write n as the sum of consecutive positive integers.
 *
 * Example 1:
 *    Input: n = 5
 *    Output: 2
 *    Explanation: 5 = 2 + 3
 *
 * Example 2:
 *    Input: n = 9
 *    Output: 3
 *    Explanation: 9 = 4 + 5 = 2 + 3 + 4
 *
 * Example 3:
 *    Input: n = 15
 *    Output: 4
 *    Explanation: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 * Constraints:
 *    1 <= n <= 10^9
 */
public class LC0829 {
   public int consecutiveNumbersSum(int n) {
      int count = 1;
      for (int i = 2; i < n; i++) {
         int tmp = n - i * (i + 1) / 2;
         if (tmp < 0) break;
         else if (tmp % i == 0) count++;
      }

      return count;
   }

   public static void run() {
      LC0829 solution = new LC0829();
      System.out.println(solution.consecutiveNumbersSum(5));
      System.out.println(solution.consecutiveNumbersSum(9));
      System.out.println(solution.consecutiveNumbersSum(15));
   }
}

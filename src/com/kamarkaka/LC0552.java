package com.kamarkaka;

/***
 * 552. Student Attendance Record II
 * An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
 *   'A': Absent.
 *   'L': Late.
 *   'P': Present.
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 *   The student was absent ('A') for strictly fewer than 2 days total.
 *   The student was never late ('L') for 3 or more consecutive days.
 * Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
 *
 * Example 1:
 *   Input: n = 2
 *   Output: 8
 *   Explanation: There are 8 records with length 2 that are eligible for an award:
 *     "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 *     Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
 *
 * Example 2:
 *   Input: n = 1
 *   Output: 3
 *
 * Example 3:
 *   Input: n = 10101
 *   Output: 183236316
 *
 * Constraints:
 *   1 <= n <= 10^5
 */
public class LC0552 {
   public int checkRecord(int n) {
      if (n == 1) return 3;
      long[][] dp = new long[2][3];

      dp[0][0] = 2;
      dp[0][1] = 1;
      dp[0][2] = 1;
      dp[1][0] = 3;
      dp[1][1] = 1;
      dp[1][2] = 0;

      for (int i = 2; i < n; i++) {
         long[][] newDP = new long[2][3];
         newDP[0][0] = (dp[0][0] + dp[0][1] + dp[0][2]) % 1_000_000_007;
         newDP[0][1] = dp[0][0];
         newDP[0][2] = dp[0][1];
         newDP[1][0] = (dp[0][0] + dp[0][1] + dp[0][2] + dp[1][0] + dp[1][1] + dp[1][2]) % 1_000_000_007;
         newDP[1][1] = dp[1][0];
         newDP[1][2] = dp[1][1];
         dp = newDP;
      }

      int res = 0;
      for (int i = 0; i < 2; i++) {
         for (int j = 0; j < 3; j++) {
            res += dp[i][j];
         }
      }
      return (int) (res % 1_000_000_007);
   }
}

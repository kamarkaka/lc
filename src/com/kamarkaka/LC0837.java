package com.kamarkaka;

/***
 * 837. New 21 Game
 * Alice plays the following game, loosely based on the card game "21".
 * Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.
 * Alice stops drawing numbers when she gets k or more points.
 * Return the probability that Alice has n or fewer points.
 * Answers within 10-5 of the actual answer are considered accepted.
 *
 * Example 1:
 *   Input: n = 10, k = 1, maxPts = 10
 *   Output: 1.00000
 *   Explanation: Alice gets a single card, then stops.
 *
 * Example 2:
 *   Input: n = 6, k = 1, maxPts = 10
 *   Output: 0.60000
 *   Explanation: Alice gets a single card, then stops.
 *   In 6 out of 10 possibilities, she is at or below 6 points.
 *
 * Example 3:
 *   Input: n = 21, k = 17, maxPts = 10
 *   Output: 0.73278
 *
 * Constraints:
 *   0 <= k <= n <= 10^4
 *   1 <= maxPts <= 10^4
 */
public class LC0837 {
   public double new21Game(int n, int k, int maxPts) {
      if (n >= k + maxPts || k == 0) return 1.0;

      double[] dp = new double[n+1];
      dp[0] = 1.0;

      double res = 0.0;
      double runningSum = dp[0];

      for (int i = 1; i <= n; i++) {
         // at this point, i assume that i have with me the sum of the previous w elements
         dp[i] = runningSum / maxPts;

         // if the element we are trying to add is less than k, only then we will add
         if (i < k) {
            runningSum += dp[i];
         } else {
            // in case the current i is greater than k we will add it to our answer because at the end we need the sum of dp values from k to n
            res += dp[i];
         }

         if (i - maxPts >= 0) {
            // adjust the tail end
            runningSum -= dp[i-maxPts];
         }
      }

      return res;
   }

   public static void run() {
      LC0837 solution = new LC0837();
      System.out.println(solution.new21Game(10, 4, 10));
   }
}

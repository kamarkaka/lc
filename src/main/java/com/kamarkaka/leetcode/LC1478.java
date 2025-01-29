package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 1478. Allocate Mailboxes
 * Given the array houses where houses[i] is the location of the ith house along a street and an integer k, allocate k
 * mailboxes in the street.
 * Return the minimum total distance between each house and its nearest mailbox.
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * Example 1:
 *   Input: houses = [1,4,8,10,20], k = 3
 *   Output: 5
 *   Explanation: Allocate mailboxes in position 3, 9 and 20.
 *   Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5
 * Example 2:
 *   Input: houses = [2,3,5,12,18], k = 2
 *   Output: 9
 *   Explanation: Allocate mailboxes in position 3 and 14.
 *   Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
 * Constraints:
 *   1 <= k <= houses.length <= 100
 *   1 <= houses[i] <= 10^4
 *   All the integers of houses are unique.
 */
public class LC1478 {
   int MAX = 10000000;

   public int minDistance(int[] houses, int k) {
      int n = houses.length;
      Arrays.sort(houses);
      int[][] dp = new int[n][k];
      for (int i = 0; i < n; i++) {
         Arrays.fill(dp[i], -1);
      }
      return solve(houses, k, 0, 0, dp);
   }

   private int solve(int[] houses, int k, int pos, int curK, int[][] dp) {
      if (pos == houses.length) {
         if (curK == k) {
            return 0;
         }
         return MAX;
      }
      if (curK == k) return MAX;
      if (dp[pos][curK] != -1) return dp[pos][curK];

      int result = MAX;
      for (int i = pos; i < houses.length; i++) {
         int median = houses[(i + pos) / 2];
         int cost = 0;
         for (int j = pos; j <= i; j++) {
            cost += Math.abs(median - houses[j]);
         }

         result = Math.min(result, cost + solve(houses, k, i + 1, curK + 1,dp));
      }
      dp[pos][curK] = result;
      return result;
   }
}


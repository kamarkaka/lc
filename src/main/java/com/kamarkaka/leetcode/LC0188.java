package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 188. Best Time to Buy and Sell Stock IV
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *    Input: k = 2, prices = [2,4,1]
 *    Output: 2
 *    Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 *    Input: k = 2, prices = [3,2,6,5,0,3]
 *    Output: 7
 *    Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 * Constraints:
 *    0 <= k <= 100
 *    0 <= prices.length <= 1000
 *    0 <= prices[i] <= 1000
 */
public class LC0188 {
   public int maxProfit(int k, int[] prices) {
      int n = prices.length;

      if (n <= 0 || k <= 0) return 0;

      List<int[]> transactions = new ArrayList<>();
      int start = 0, end = 0;
      for (int i = 1; i < n; i++) {
         if (prices[i] >= prices[i - 1]) {
            end = i;
         } else {
            if (end > start) {
               transactions.add(new int[] {start, end});
            }
            start = i;
         }
      }

      if (end > start) {
         transactions.add(new int[] {start, end});
      }

      while (transactions.size() > k) {
         int deleteIndex = 0;
         int minDeleteLoss = Integer.MAX_VALUE;
         for (int i = 0; i < transactions.size(); i++) {
            int[] t = transactions.get(i);
            int profitLoss = prices[t[1]] - prices[t[0]];
            if (profitLoss < minDeleteLoss) {
               minDeleteLoss = profitLoss;
               deleteIndex = i;
            }
         }

         int mergeIndex = 0;
         int minMergeLoss = Integer.MAX_VALUE;
         for (int i = 1; i < transactions.size(); i++) {
            int[] t1 = transactions.get(i - 1);
            int[] t2 = transactions.get(i);
            int profitLoss = prices[t1[1]] - prices[t2[0]];
            if (profitLoss < minMergeLoss) {
               minMergeLoss = profitLoss;
               mergeIndex = i;
            }
         }

         if (minDeleteLoss <= minMergeLoss) {
            transactions.remove(deleteIndex);
         } else {
            int[] t1 = transactions.get(mergeIndex - 1);
            int[] t2 = transactions.get(mergeIndex);
            t1[1] = t2[1];
            transactions.remove(mergeIndex);
         }
      }

      int res = 0;
      for (int[] t : transactions) {
         res += prices[t[1]] - prices[t[0]];
      }
      return res;
   }

   public int maxProfit2(int k, int[] prices) {
      int n = prices.length;

      if (n <= 0 || k <= 0) return 0;

      if (2 * k > n) {
         int res = 0;
         for (int i = 1; i < n; i++) {
            res += Math.max(0, prices[i] - prices[i - 1]);
         }
         return res;
      }

      int[][][] dp = new int[n][k + 1][2];
      for (int i = 0; i < n; i++) {
         for (int j = 0; j <= k; j++) {
            dp[i][j][0] = Integer.MIN_VALUE;
            dp[i][j][1] = Integer.MIN_VALUE;
         }
      }

      dp[0][0][0] = 0;
      dp[0][1][1] = -prices[0];

      for (int i = 1; i < n; i++) {
         for (int j = 0; j <= k; j++) {
            dp[i][j][0] = Math.max(
                  dp[i - 1][j][0],
                  dp[i - 1][j][1] + prices[i]);

            if (j > 0) {
               dp[i][j][1] = Math.max(
                  dp[i - 1][j][1],
                  dp[i - 1][j - 1][0] - prices[i]
               );
            }
         }
      }

      int res = 0;
      for (int i = 0; i <= k; i++) {
         res = Math.max(res, dp[n - 1][i][0]);
      }
      return res;
   }
}

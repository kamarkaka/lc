package com.kamarkaka;

import java.util.ArrayList;
import java.util.List;

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

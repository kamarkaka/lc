package com.kamarkaka.google;

import java.util.Arrays;

public class KnapsackDP {
   public int KnapsackDP(int[] weights, int[] values, int n, int maxWeight) {
      if (n <= 0 || maxWeight <= 0) return 0;

      int[][] dp = new int[n+1][maxWeight+1];
      Arrays.fill(dp[0], 0);

      for (int i = 1; i <= n; i++) {
         for (int j = 1; j <= maxWeight; j++) {
            if (weights[i-1] > j) {
               dp[i][j] = dp[i-1][j];
            } else {
               dp[i][j] = Math.max(
                  dp[i-1][j],
                  dp[i-1][j - weights[i-1]] + values[i - 1]
               );
            }
         }
      }

      return dp[n][maxWeight];
   }
}

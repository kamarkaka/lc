package com.kamarkaka;

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

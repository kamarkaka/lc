package com.kamarkaka.indeed;

public class DiceSum {
   public double getPossibility(int dice, int target) {
      if (dice <= 0 || target < dice || target > 6 * dice) return 0.0;

      int total = (int) Math.pow(6, dice);
      int[] dp1 = new int[target+1];
      int[] dp2 = new int[target+1];
      for (int i = 1; i <= Math.min(6, target); i++) {
         dp1[i] = 1;
      }

      for (int i = 2; i <= dice; i++) {
         for (int j = i; j <= target; j++) {
            for (int k = 1; k <= 6; k++) {
               if (j - k >= i - 1) dp2[j] += dp1[j-k];
            }
         }

         dp1 = dp2;
         dp2 = new int[target+1];
      }

      return (double) dp1[target] / total;
   }

   public static void run() {
      DiceSum sol = new DiceSum();
      System.out.println(sol.getPossibility(3, 9));
   }
}

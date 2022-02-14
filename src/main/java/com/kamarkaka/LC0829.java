package com.kamarkaka;

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

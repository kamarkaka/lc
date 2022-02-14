package main.java.com.kamarkaka;

import java.util.Arrays;

public class LC0322 {
   public int coinChange(int[] coins, int amount) {
      int[] res = new int[amount + 1];
      Arrays.fill(res, amount + 1);

      res[0] = 0;
      for (int i = 1; i <= amount; i++) {

         for (int coin : coins) {
            if (i >= coin) {
               res[i] = Math.min(res[i], res[i - coin] + 1);
            }
         }
      }

      return res[amount] > amount ? -1 : res[amount];
   }
}

package com.kamarkaka;

import java.util.Arrays;

/***
 * 322. Coin Change
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 *    Input: coins = [1,2,5], amount = 11
 *    Output: 3
 *    Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 *    Input: coins = [2], amount = 3
 *    Output: -1
 *
 * Example 3:
 *    Input: coins = [1], amount = 0
 *    Output: 0
 *
 * Constraints:
 *    1 <= coins.length <= 12
 *    1 <= coins[i] <= 2^31 - 1
 *    0 <= amount <= 10^4
 */
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

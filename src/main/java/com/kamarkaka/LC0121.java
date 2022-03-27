package com.kamarkaka;

/***
 * 121. Best Time to Buy and Sell Stock
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * Example 1:
 *    Input: prices = [7,1,5,3,6,4]
 *    Output: 5
 *    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *       Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Example 2:
 *    Input: prices = [7,6,4,3,1]
 *    Output: 0
 *    Explanation: In this case, no transactions are done and the max profit = 0.
 *
 * Constraints:
 *    1 <= prices.length <= 10^5
 *    0 <= prices[i] <= 10^4
 */
public class LC0121 {
   public int maxProfit(int[] prices) {
      int min = Integer.MAX_VALUE;
      int max = 0;

      for (int i = 0; i < prices.length; i++) {
         if (prices[i] < min)
            min = prices[i];
         else if (prices[i] - min > max)
            max = prices[i] - min;
      }

      return max;
   }

   public static void run() {
      LC0121 solution = new LC0121();
      System.out.println(solution.maxProfit(new int[] {7,1,5,3,6,4}));
      System.out.println(solution.maxProfit(new int[] {7,6,4,3,1}));
      System.out.println(solution.maxProfit(new int[] {5,4,-1,7,8}));
      System.out.println(solution.maxProfit(new int[] {-2,1}));
      System.out.println(solution.maxProfit(new int[] {-2,-1}));
   }
}

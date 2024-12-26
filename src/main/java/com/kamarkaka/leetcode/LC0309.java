package com.kamarkaka.leetcode;

/***
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell
 * one share of the stock multiple times) with the following restrictions:
 *   After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy
 * again).
 * Example 1:
 *   Input: prices = [1,2,3,0,2]
 *   Output: 3
 *   Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *   Input: prices = [1]
 *   Output: 0
 * Constraints:
 *   1 <= prices.length <= 5000
 *   0 <= prices[i] <= 1000
 */
public class LC0309 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int[] dpBuy = new int[prices.length];
        int[] dpSell = new int[prices.length];

        dpBuy[0] = -prices[0];
        dpBuy[1] = Math.max(-prices[0], -prices[1]);
        dpSell[0] = 0;
        dpSell[1] = Math.max(0, prices[1] - prices[0]);

        for (int i = 2; i < prices.length; i++) {
            dpBuy[i] = Math.max(dpBuy[i - 1], dpSell[i - 2] - prices[i]);
            dpSell[i] = Math.max(dpSell[i - 1], dpBuy[i - 1] + prices[i]);
        }

        int maxProfit = Math.max(dpBuy[prices.length - 1], dpSell[prices.length - 1]);
        return Math.max(maxProfit, 0);
    }
}

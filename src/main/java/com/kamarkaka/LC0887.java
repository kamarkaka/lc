package com.kamarkaka;

/***
 * 887. Super Egg Drop
 * You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
 * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.
 * Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.
 * Return the minimum number of moves that you need to determine with certainty what the value of f is.
 *
 * Example 1:
 *    Input: k = 1, n = 2
 *    Output: 2
 *    Explanation:
 *       Drop the egg from floor 1. If it breaks, we know that f = 0.
 *       Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
 *       If it does not break, then we know f = 2.
 *       Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
 *
 * Example 2:
 *    Input: k = 2, n = 6
 *    Output: 3
 *
 * Example 3:
 *    Input: k = 3, n = 14
 *    Output: 4
 *
 * Constraints:
 *    1 <= k <= 100
 *    1 <= n <= 10^4
 */
public class LC0887 {
   public int superEggDrop(int k, int n) {
      int step = 0;
      for (int floor = 0; floor <= n; floor++) {
         step = Math.max(step, solve(k, n, floor));
      }
      return step;
   }

   private int solve(int eggCount, int total, int floor) {
      int lo = 1, hi = total;

      int step = 0;
      while (eggCount > 0 && hi > lo) {
         int mid = lo + (hi - lo) / 2;
         if (floor > mid) {
            lo = mid + 1;
         } else {
            eggCount--;
            hi = mid - 1;
         }
         step++;
      }

      return step;
   }
}

package com.kamarkaka;

import java.util.Arrays;

/***
 * 1552. Magnetic Force Between Two Balls
 * In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
 * Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
 * Given the integer array position and the integer m. Return the required force.
 *
 * Example 1:
 *    Input: position = [1,2,3,4,7], m = 3
 *    Output: 3
 *    Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
 *
 * Example 2:
 *    Input: position = [5,4,3,2,1,1000000000], m = 2
 *    Output: 999999999
 *    Explanation: We can use baskets 1 and 1000000000.
 *
 * Constraints:
 *    n == position.length
 *    2 <= n <= 10^5
 *    1 <= position[i] <= 10^9
 *    All integers in position are distinct.
 *    2 <= m <= position.length
 */
public class LC1552 {
   public int maxDistance(int[] position, int m) {
      Arrays.sort(position);
      int n = position.length - 1;
      int start = 0, end = position[n];

      int res = -1;
      while (start <= end) {
         int mid = start + (end - start) / 2;
         if (isPossibleSolution(position, m, mid)) {
            res = mid;
            start = mid + 1;
         } else {
            end = mid - 1;
         }

      }
      return res;
   }

   private boolean isPossibleSolution(int[] position, int m, int mid) {
      int ballsCount = 1;
      int lastPos = position[0];
      for (int i = 0; i < position.length; i++) {
         if (position[i] - lastPos >= mid) {
            ballsCount++;
            if (ballsCount == m) return true;
            lastPos = position[i];
         }
      }
      return false;
   }
}

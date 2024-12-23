package com.kamarkaka.leetcode;

import com.kamarkaka.common.Pair;

import java.math.BigInteger;
import java.util.HashMap;

/***
 * 149. Max Points on a Line
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
 *
 * Example 1:
 *    Input: points = [[1,1],[2,2],[3,3]]
 *    Output: 3
 *
 * Example 2:
 *    Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 *    Output: 4
 *
 * Constraints:
 *    1 <= points.length <= 300
 *    points[i].length == 2
 *    -10^4 <= xi, yi <= 10^4
 *    All the points are unique.
 */
public class LC0149 {
   int[][] points;
   int n;
   HashMap<Pair<Integer, Integer>, Integer> lines = new HashMap<>();
   int horizontalLines;

   /*
    * To avoid the precision issue with float/double numbers, using a pair of co-prime numbers to
    * represent the slope.
    */
   private Pair<Integer, Integer> getSlope(int x1, int y1, int x2, int y2) {
      int deltaX = x1 - x2, deltaY = y1 - y2;
      if (deltaX == 0)
         return new Pair<>(0, 0);
      else if (deltaY == 0)
         return new Pair<>(Integer.MAX_VALUE, Integer.MAX_VALUE);
      else if (deltaX < 0) {
         deltaX = -deltaX;
         deltaY = -deltaY;
      }
      Integer gcd = BigInteger.valueOf(deltaX).gcd(BigInteger.valueOf(deltaY)).intValue();
      return new Pair<>(deltaX / gcd, deltaY / gcd);
   }

   /*
    * Add a line passing through i and j points.
    * Update max number of points on a line containing point i.
    */
   public int addLine(int i, int j, int count) {
      // rewrite points as coordinates
      int x1 = points[i][0], y1 = points[i][1];
      int x2 = points[j][0], y2 = points[j][1];

      if (x1 == x2 && y1 == y2) {
      } else if (y1 == y2) {
         // add a horisontal line : y = const
         horizontalLines++;
         count = Math.max(horizontalLines, count);
      } else {
         // add a line: x = slope * y + c
         // only slope is needed for a hash-map
         // since we always start from the same point
         Pair<Integer, Integer> slope = getSlope(x1, y1, x2, y2);
         lines.put(slope, lines.getOrDefault(slope, 1) + 1);
         count = Math.max(lines.get(slope), count);
      }
      return count;
   }

   /*
    * Compute the max number of points for a line containing point i.
    */
   public int getMaxPoints(int i) {
      // init lines passing through point i
      lines.clear();
      horizontalLines = 1;
      // One starts with just one point on a line : point i.
      int count = 1;

      // Compute lines passing through point i (fixed) and point j.
      // Update in a loop the number of points on a line.
      for (int j = i + 1; j < n; j++) {
         count = addLine(i, j, count);
      }
      return count;
   }


   public int maxPoints(int[][] points) {
      this.points = points;
      this.n = points.length;

      if (n < 3) return n;

      int maxCount = 1;
      // Compute in a loop a max number of points on a line containing point i.
      for (int i = 0; i < n - 1; i++) {
         maxCount = Math.max(maxCount, getMaxPoints(i));
      }

      return maxCount;
   }
}

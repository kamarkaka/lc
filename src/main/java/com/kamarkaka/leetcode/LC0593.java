package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 593. Valid Square
 * Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.
 * The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
 * A valid square has four equal sides with positive length and four equal angles (90-degree angles).
 *
 * Example 1:
 *    Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 *    Output: true
 *
 * Example 2:
 *    Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 *    Output: false
 *
 * Example 3:
 *    Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 *    Output: true
 *
 * Constraints:
 *    p1.length == p2.length == p3.length == p4.length == 2
 *    -10^4 <= xi, yi <= 10^4
 */
public class LC0593 {
   public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
      int[] distances = new int[3];
      distances[0] = getEuclideanDistance(p1, p2);
      distances[1] = getEuclideanDistance(p1, p3);
      distances[2] = getEuclideanDistance(p1, p4);

      if (distances[0] == distances[1] && distances[2] == 2 * distances[0]) {
         if (!Arrays.equals(p2, p3) && getEuclideanDistance(p4, p2) == getEuclideanDistance(p4, p3)) return true;
      } else if (distances[0] == distances[2] && distances[1] == 2 * distances[0]) {
         if (!Arrays.equals(p2, p4) && getEuclideanDistance(p3, p2) == getEuclideanDistance(p3, p4)) return true;
      } else if (distances[1] == distances[2] && distances[0] == 2 * distances[1]) {
         if (!Arrays.equals(p3, p4) && getEuclideanDistance(p2, p3) == getEuclideanDistance(p2, p4)) return true;
      }

      return false;
   }

   public int getEuclideanDistance(int[] p1, int[] p2) {
      int dx = p2[0] - p1[0], dy = p2[1] - p1[1];
      return dx * dx + dy * dy;
   }
}

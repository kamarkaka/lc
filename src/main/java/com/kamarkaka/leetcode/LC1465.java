package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
 * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
 *    horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
 *    verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.
 *
 * Example 1:
 *    Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
 *    Output: 4
 *    Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
 *
 * Example 2:
 *    Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
 *    Output: 6
 *    Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
 *
 * Example 3:
 *    Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
 *    Output: 9
 *
 * Constraints:
 *    2 <= h, w <= 10^9
 *    1 <= horizontalCuts.length <= min(h - 1, 10^5)
 *    1 <= verticalCuts.length <= min(w - 1, 10^5)
 *    1 <= horizontalCuts[i] < h
 *    1 <= verticalCuts[i] < w
 *    All the elements in horizontalCuts are distinct.
 *    All the elements in verticalCuts are distinct.
 */
public class LC1465 {
   public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
      int[] vSpaces = new int[horizontalCuts.length + 1];
      int[] hSpaces = new int[verticalCuts.length + 1];

      Arrays.sort(horizontalCuts);
      for (int i = 0; i < horizontalCuts.length; i++) {
         if (i == 0) {
            vSpaces[i] = horizontalCuts[i];
         } else {
            vSpaces[i] = horizontalCuts[i] - horizontalCuts[i - 1];
         }
      }
      vSpaces[vSpaces.length - 1] = h - horizontalCuts[horizontalCuts.length - 1];

      Arrays.sort(verticalCuts);
      for (int i = 0; i < verticalCuts.length; i++) {
         if (i == 0) {
            hSpaces[i] = verticalCuts[i];
         } else {
            hSpaces[i] = verticalCuts[i] - verticalCuts[i - 1];
         }
      }
      hSpaces[hSpaces.length - 1] = w - verticalCuts[verticalCuts.length - 1];

      Arrays.sort(vSpaces);
      Arrays.sort(hSpaces);

      long max = (long) vSpaces[vSpaces.length - 1] * hSpaces[hSpaces.length - 1];
      return (int) (max % 1_000_000_007);
   }
}

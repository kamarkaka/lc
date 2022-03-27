package com.kamarkaka;

/***
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 * Notice that you may not slant the container.
 *
 * Example 1:
 *   Input: height = [1,8,6,2,5,4,8,3,7]
 *   Output: 49
 *   Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Example 2:
 *   Input: height = [1,1]
 *   Output: 1
 *
 * Example 3:
 *   Input: height = [4,3,2,1,4]
 *   Output: 16
 *
 * Example 4:
 *   Input: height = [1,2,1]
 *   Output: 2
 *
 * Constraints:
 *   n == height.length
 *   2 <= n <= 10^5
 *   0 <= height[i] <= 10^4
 */
public class LC0011 {
   public int maxArea(int[] height) {
      if (height == null || height.length == 1) return 0;

      int startIndex = 0, endIndex = height.length - 1;
      int res = Math.min(height[startIndex], height[endIndex]) * (endIndex - startIndex);

      while (startIndex < endIndex) {
         if (height[startIndex] <= height[endIndex]) {
            startIndex++;
         } else {
            endIndex--;
         }

         res = Math.max(res, Math.min(height[startIndex], height[endIndex]) * (endIndex - startIndex));
      }

      return res;
   }

   public static void run() {
      LC0011 solution = new LC0011();
      System.out.println(solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
      System.out.println(solution.maxArea(new int[]{1,1}));
      System.out.println(solution.maxArea(new int[]{4,3,2,1,4}));
      System.out.println(solution.maxArea(new int[]{1,2,1}));
   }
}

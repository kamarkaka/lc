package com.kamarkaka.leetcode;

/***
 * 546. Remove Boxes
 * You are given several boxes with different colors represented by different positive numbers.
 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
 * Return the maximum points you can get.
 *
 * Example 1:
 *    Input: boxes = [1,3,2,2,2,3,4,3,1]
 *    Output: 23
 *    Explanation:
 *       [1, 3, 2, 2, 2, 3, 4, 3, 1]
 *          ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 *          ----> [1, 3, 3, 3, 1] (1*1=1 points)
 *          ----> [1, 1] (3*3=9 points)
 *          ----> [] (2*2=4 points)
 *
 * Example 2:
 *    Input: boxes = [1,1,1]
 *    Output: 9
 *
 * Example 3:
 *    Input: boxes = [1]
 *    Output: 1
 *
 * Constraints:
 *    1 <= boxes.length <= 100
 *    1 <= boxes[i] <= 100
 */
public class LC0546 {
   public int removeBoxes(int[] boxes) {
      int[][][] dp = new int[100][100][100];
      return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
   }

   private int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
      if (l > r) return 0;

      while (r > l && boxes[r] == boxes[r - 1]) {
         r--;
         k++;
      }

      if (dp[l][r][k] != 0) return dp[l][r][k];

      dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
      for (int i = l; i < r; i++) {
         if (boxes[i] != boxes[r]) continue;
         dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
      }
      return dp[l][r][k];
   }
}

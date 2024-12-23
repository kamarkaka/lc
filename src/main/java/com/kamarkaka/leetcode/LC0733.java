package com.kamarkaka.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 733. Flood Fill
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and newColor. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with newColor.
 * Return the modified image after performing the flood fill.
 *
 * Example 1:
 *    Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor = 2
 *    Output: [[2,2,2],[2,2,0],[2,0,1]]
 *    Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 *       Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 *
 * Example 2:
 *    Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
 *    Output: [[2,2,2],[2,2,2]]
 *
 * Constraints:
 *    m == image.length
 *    n == image[i].length
 *    1 <= m, n <= 50
 *    0 <= image[i][j], newColor < 216
 *    0 <= sr < m
 *    0 <= sc < n
 */
public class LC0733 {
   public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
      int m = image.length, n = image[0].length;
      int oldColor = image[sr][sc];
      Queue<int[]> queue = new LinkedList<>();
      queue.add(new int[]{sr, sc});

      while (!queue.isEmpty()) {
         int[] s = queue.poll();
         if (image[s[0]][s[1]] == newColor) continue;

         if (s[0] - 1 >= 0 && image[s[0] - 1][s[1]] == oldColor)
            queue.add(new int[]{s[0] - 1, s[1]});

         if (s[0] + 1 < m && image[s[0] + 1][s[1]] == oldColor)
            queue.add(new int[]{s[0] + 1, s[1]});

         if (s[1] - 1 >= 0 && image[s[0]][s[1] - 1] == oldColor)
            queue.add(new int[]{s[0], s[1] - 1});

         if (s[1] + 1 < n && image[s[0]][s[1] + 1] == oldColor)
            queue.add(new int[]{s[0], s[1] + 1});

         image[s[0]][s[1]] = newColor;
      }

      return image;
   }
}

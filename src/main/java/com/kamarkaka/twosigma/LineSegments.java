package com.kamarkaka.twosigma;

import java.util.Arrays;
import java.util.Comparator;

/***
 * 给很多线段 求多少种取法能取出两个段落彼此不overlap
 * Example:
 *    Input: {{1,3},{1,2},{4,6},{5,7}}
 *    Output: 4
 *    Explanation:
 *       {{1,3},{4,6}}
 *       {{1,3},{5,7}}
 *       {{1,2},{4,6}}
 *       {{1,2},{5,7}}
 */
public class LineSegments {
   public int pickTwoCount(int[][] lines) {
      int count = 0;
      int len = lines.length;
      Arrays.sort(lines, Comparator.comparingInt(l -> l[0]));

      for (int[] line : lines) {
         int end = line[1];
         int idx = Arrays.binarySearch(lines, new int[] {end, -1}, Comparator.comparingInt(l -> l[0]));
         if (idx >= 0) {
            count += len - idx - 1;
         } else {
            idx = -idx -1;
            count += len - idx;
         }
      }

      return count;
   }

   public static void run() {
      LineSegments sol = new LineSegments();
      System.out.println(sol.pickTwoCount(new int[][] {{1,3},{1,2},{4,6},{5,7}}));
   }
}

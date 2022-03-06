package com.kamarkaka;

import java.util.*;

/***
 * 850. Rectangle Area II
 * You are given a 2D array of axis-aligned rectangles. Each rectangle[i] = [xi1, yi1, xi2, yi2] denotes the ith rectangle where (xi1, yi1) are the coordinates of the bottom-left corner, and (xi2, yi2) are the coordinates of the top-right corner.
 * Calculate the total area covered by all rectangles in the plane. Any area covered by two or more rectangles should only be counted once.
 * Return the total area. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 *    Input: rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 *    Output: 6
 *    Explanation: A total area of 6 is covered by all three rectangales, as illustrated in the picture.
 *       From (1,1) to (2,2), the green and red rectangles overlap.
 *       From (1,0) to (2,3), all three rectangles overlap.
 *
 * Example 2:
 *    Input: rectangles = [[0,0,1000000000,1000000000]]
 *    Output: 49
 *    Explanation: The answer is 10^18 modulo (10^9 + 7), which is 49.
 *
 * Constraints:
 *    1 <= rectangles.length <= 200
 *    rectanges[i].length == 4
 *    0 <= xi1, yi1, xi2, yi2 <= 10^9
 */
public class LC0850 {
   private static final int OPEN = 1;
   private static final int CLOSE = -1;

   public int rectangleArea(int[][] rectangles) {
      int[][] events = new int[rectangles.length * 2][];
      Set<Integer> xVals = new HashSet<>();
      int t = 0;

      for (int[] rec: rectangles) {
         if ((rec[0] < rec[2]) && (rec[1] < rec[3])) {
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
            xVals.add(rec[0]);
            xVals.add(rec[2]);
         }
      }

      Arrays.sort(events, 0, t, Comparator.comparingInt(a -> a[0]));

      Integer[] X = xVals.toArray(new Integer[0]);
      Arrays.sort(X);
      Map<Integer, Integer> Xi = new HashMap<>();
      for (int i = 0; i < X.length; ++i) {
         Xi.put(X[i], i);
      }

      Node active = new Node(0, X.length - 1, X);
      long ans = 0;
      long cur_x_sum = 0;
      int cur_y = events[0][0];

      for (int[] event: events) {
         if (event == null) break;
         int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
         ans += cur_x_sum * (y - cur_y);

         cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
         cur_y = y;
      }

      ans %= 1_000_000_007;
      return (int) ans;
   }

   private class Node {
      int start, end;
      Integer[] X;
      Node left, right;
      int count;
      long total;

      public Node(int start, int end, Integer[] X) {
         this.start = start;
         this.end = end;
         this.X = X;
         left = null;
         right = null;
         count = 0;
         total = 0;
      }

      public long update(int i, int j, int val) {
         if (i >= j) return 0;
         if (start == i && end == j) {
            count += val;
         } else {
            getLeft().update(i, Math.min(getRangeMid(), j), val);
            getRight().update(Math.max(getRangeMid(), i), j, val);
         }

         if (count > 0) total = X[end] - X[start];
         else total = getLeft().total + getRight().total;

         return total;
      }

      private int getRangeMid() {
         return start + (end - start) / 2;
      }

      private Node getLeft() {
         if (left == null) left = new Node(start, getRangeMid(), X);
         return left;
      }

      private Node getRight() {
         if (right == null) right = new Node(getRangeMid(), end, X);
         return right;
      }
   }

   public static void run() {
      LC0850 sol = new LC0850();
      System.out.println(sol.rectangleArea(new int[][] {
         {0,0,2,2},
         {1,0,2,3},
         {1,0,3,1},
      }));
   }
}

package com.kamarkaka;

import java.util.ArrayList;
import java.util.List;

/***
 * 1931. Painting a Grid With Three Different Colors
 * You are given two integers m and n. Consider an m x n grid where each cell is initially white. You can paint each cell red, green, or blue. All cells must be painted.
 * Return the number of ways to color the grid with no two adjacent cells having the same color. Since the answer can be very large, return it modulo 109 + 7.
 *
 * Example 1:
 *    Input: m = 1, n = 1
 *    Output: 3
 *    Explanation: The three possible colorings are shown in the image above.
 *
 * Example 2:
 *    Input: m = 1, n = 2
 *    Output: 6
 *    Explanation: The six possible colorings are shown in the image above.
 *
 * Example 3:
 *    Input: m = 5, n = 5
 *    Output: 580986
 *
 * Constraints:
 *    1 <= m <= 5
 *    1 <= n <= 1000
 */
public class LC1931 {
   public int colorTheGrid(int m, int n) {
      List<String> list = new ArrayList<>();
      traverse(m, list, "");

      int mod = 1_000_000_007, s = list.size();
      int[][] arr = new int[s][s];
      for (int i = 0; i < s; i++) {
         for (int j = 0; j < s; j++) {
            arr[i][j] = compute(list.get(i), list.get(j));
         }
      }

      int[] dp = new int[s];
      for (int i = 0; i < s; i++) {
         dp[i] = 1;
      }

      for (int i = 2; i <= n; i++) {
         int[] pres = new int[s];
         for (int j = 0; j < s; j++) {
            for (int k = 0; k < s; k++) {
               if (arr[j][k] == 1) pres[j] = (pres[j] % mod + dp[k] % mod) % mod;
            }
         }
         dp = pres;
      }

      int res = 0;
      for (int i = 0; i < s; i++) {
         res = (res % mod + dp[i] % mod) % mod;
      }
      return res;
   }

   private void traverse(int m, List<String> list, String s) {
      int len = s.length();
      if (len > 1 && s.charAt(len - 1) == s.charAt(len - 2)) return;
      if (m == 0) {
         list.add(s);
         return;
      }

      traverse(m - 1, list, s + "r");
      traverse(m - 1, list, s + "g");
      traverse(m - 1, list, s + "b");
      return;
   }

   private int compute(String s, String t) {
      for (int i = 0; i < s.length(); i++) {
         if (s.charAt(i) == t.charAt(i)) return 0;
      }
      return 1;
   }

   public static void run() {
      LC1931 sol = new LC1931();
      System.out.println(sol.colorTheGrid(5,5));
   }
}

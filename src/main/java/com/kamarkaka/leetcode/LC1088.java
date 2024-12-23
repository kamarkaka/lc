package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 1088. Confusing Number II
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 * We can rotate digits of a number by 180 degrees to form new digits.
 *   When 0, 1, 6, 8, and 9 are rotated 180 degrees, they become 0, 1, 9, 8, and 6 respectively.
 *   When 2, 3, 4, 5, and 7 are rotated 180 degrees, they become invalid.
 * Note that after rotating a number, we can ignore leading zeros.
 *   For example, after rotating 8000, we have 0008 which is considered as just 8.
 * Given an integer n, return the number of confusing numbers in the inclusive range [1, n].
 *
 * Example 1:
 *   Input: n = 20
 *   Output: 6
 *   Explanation: The confusing numbers are [6,9,10,16,18,19].
 *   6 converts to 9.
 *   9 converts to 6.
 *   10 converts to 01 which is just 1.
 *   16 converts to 91.
 *   18 converts to 81.
 *   19 converts to 61.
 *
 * Example 2:
 *   Input: n = 100
 *   Output: 19
 *   Explanation: The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 *
 * Constraints:
 *   1 <= n <= 10^9
 */
public class LC1088 {
   Map<Integer, Integer> map;
   public int confusingNumberII(int n) {
      map = new HashMap<>();
      map.put(0, 0);
      map.put(1, 1);
      map.put(6, 9);
      map.put(8, 8);
      map.put(9, 6);

      int res = 0;
      for (int i = 2; i <= n; i++) {
         int num = rotate(i);
         if (num > 0 && i != num) {
            res++;
         }
      }
      return res;
   }

   private int rotate(int n) {
      int res = 0;

      while (n > 0) {
         int digit = n % 10;
         if (!map.containsKey(digit)) return -1;

         res = 10 * res + map.get(digit);
         n /= 10;
      }

      return res;
   }

   static char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
   public int confusingNumberII2(int N) {
      String num = Integer.toString(N);
      int res = findTotal(num);
      for (int len = 1; len <= num.length(); len++) {
         char[] curr = new char[len];
         res -= dfs(curr, num, 0, len - 1);
      }
      return res;
   }

   // count the # of numbers from "01689" that is less than N
   private int findTotal(String s) {
      if (s.length() == 0) return 1;
      char first = s.charAt(0);
      int res = count(first) * (int) (Math.pow(5, s.length() - 1));
      if (first == '0' || first == '1' || first == '6' || first == '8' || first == '9') {
         res += findTotal(s.substring(1));
      }
      return res;
   }

   // count the # of Strobogrammatic numbers
   private int dfs(char[] curr, String num, int left, int right) {
      int res = 0;
      if (left > right) {
         String s = new String(curr);
         if (s.length() < num.length() || s.compareTo(num) <= 0) {
            res += 1;
         }
      } else {
         for (char[] p : pairs) {
            curr[left] = p[0];
            curr[right] = p[1];
            if ((curr[0] == '0' && curr.length > 1) || (left == right && p[0] != p[1])) continue;
            res += dfs(curr, num, left + 1, right - 1);
         }
      }
      return res;
   }

   // a helper function that counts the # of chars in "01689" less than given 'c'
   private int count(Character c) {
      int res = 0;
      for (char[] p : pairs) {
         if (p[0] < c) res += 1;
      }
      return res;
   }

   public static void run() {
      LC1088 solution = new LC1088();
      System.out.println(solution.confusingNumberII2(9));
      System.out.println(solution.confusingNumberII2(99));
      System.out.println(solution.confusingNumberII2(999));
      System.out.println(solution.confusingNumberII2(9999));
      System.out.println(solution.confusingNumberII2(99999));
   }
}


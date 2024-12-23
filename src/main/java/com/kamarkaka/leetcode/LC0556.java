package com.kamarkaka.leetcode;

/***
 * 556. Next Greater Element III
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 *
 * Example 1:
 *    Input: n = 12
 *    Output: 21
 *
 * Example 2:
 *    Input: n = 21
 *    Output: -1
 *
 * Constraints:
 *    1 <= n <= 2^31 - 1
 */
public class LC0556 {
   public int nextGreaterElement(int n) {
      char[] a = ("" + n).toCharArray();

      // finding first decreasing element
      int i = a.length - 2;
      while (i >= 0 && a[i + 1] <= a[i]) {
         i--;
      }
      if (i < 0) return -1;

      // finding next element just larger than the found one
      int j = a.length - 1;
      while (j >= 0 && a[j] <= a[i]) {
         j--;
      }

      // swap the two
      swap(a, i, j);

      // reverse the rest
      reverse(a, i + 1);
      try {
         return Integer.parseInt(new String(a));
      } catch (Exception e) {
         return -1;
      }
   }

   private void reverse(char[] a, int start) {
      int i = start, j = a.length - 1;
      while (i < j) {
         swap(a, i, j);
         i++;
         j--;
      }
   }

   private void swap(char[] a, int i, int j) {
      char temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }
}

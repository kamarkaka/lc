package com.kamarkaka.leetcode;

/***
 * 777. Swap Adjacent in LR String
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 * Example 1:
 *    Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 *    Output: true
 *    Explanation: We can transform start to end following these steps:
 *       RXXLRXRXL ->
 *       XRXLRXRXL ->
 *       XRLXRXRXL ->
 *       XRLXXRRXL ->
 *       XRLXXRRLX
 *
 * Example 2:
 *    Input: start = "X", end = "L"
 *    Output: false
 *
 * Constraints:
 *    1 <= start.length <= 10^4
 *    start.length == end.length
 *    Both start and end will only consist of characters in 'L', 'R', and 'X'.
 */
public class LC0777 {
   public boolean canTransform(String start, String end) {
      if (start == null && end == null) return true;
      if (start == null || end == null) return false;
      if (start.length() != end.length()) return false;

      int idx = 0;
      for (int i = 0; i < start.length(); i++) {
         if (start.charAt(i) == 'X') continue;

         while (idx < end.length() && end.charAt(idx) == 'X') idx++;
         if (idx == end.length()) return false;
         if (end.charAt(idx) != start.charAt(i)) return false;
         if ((start.charAt(i) == 'R' && idx < i) || (start.charAt(i) == 'L' && idx > i)) return false;

         idx++;
      }

      for (int i = idx; i < end.length(); i++) {
         if (end.charAt(i) == 'R' || end.charAt(i) == 'L') return false;
      }

      return true;
   }
}

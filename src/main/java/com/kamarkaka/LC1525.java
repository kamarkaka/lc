package com.kamarkaka;

/***
 * 1525. Number of Good Ways to Split a String
 * You are given a string s.
 * A split is called good if you can split s into two non-empty strings sleft and sright where their concatenation is equal to s (i.e., sleft + sright = s) and the number of distinct letters in sleft and sright is the same.
 * Return the number of good splits you can make in s.
 *
 * Example 1:
 *    Input: s = "aacaba"
 *    Output: 2
 *    Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
 *       ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
 *       ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
 *       ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
 *       ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
 *       ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 *
 * Example 2:
 *    Input: s = "abcd"
 *    Output: 1
 *    Explanation: Split the string as follows ("ab", "cd").
 *
 * Constraints:
 *    1 <= s.length <= 10^5
 *    s consists of only lowercase English letters.
 */
public class LC1525 {
   public int numSplits(String s) {
      int n = s.length();
      int[] left = new int[n];
      int[] right = new int[n];

      int[] count = new int[26];
      left[0] = 1;
      for (int i = 0; i < n; i++) {
         char c = s.charAt(i);

         if (i == 0) {
            count[c - 'a']++;
            continue;
         }

         left[i] = left[i-1];
         count[c - 'a']++;
         if (count[c - 'a'] == 1) {
            left[i]++;
         }
      }

      count = new int[26];
      right[n-1] = 1;
      for (int i = n - 1; i >= 0; i--) {
         char c = s.charAt(i);

         if (i == n - 1) {
            count[c - 'a']++;
            continue;
         }

         right[i] = right[i+1];
         count[c - 'a']++;
         if (count[c - 'a'] == 1) {
            right[i]++;
         }
      }

      int res = 0;
      for (int i = 0; i < n - 1; i++) {
         if (left[i] == right[i+1]) res++;
      }
      return res;
   }

   public static void run() {
      LC1525 sol = new LC1525();
      System.out.println(sol.numSplits("aacaba"));
   }
}

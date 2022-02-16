package com.kamarkaka;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/***
 * 340. Longest Substring with At Most K Distinct Characters
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
 *
 * Example 1:
 *    Input: s = "eceba", k = 2
 *    Output: 3
 *    Explanation: The substring is "ece" with length 3.
 *
 * Example 2:
 *    Input: s = "aa", k = 1
 *    Output: 2
 *    Explanation: The substring is "aa" with length 2.
 *
 * Constraints:
 *    1 <= s.length <= 5 * 10^4
 *    0 <= k <= 50
 */
public class LC0340 {
   public int lengthOfLongestSubstringKDistinct(String s, int k) {
      int n = s.length();
      if (n * k == 0) return 0;

      int left = 0, right = 0, maxLen = 1;
      Map<Character, Integer> rightmostPosition = new HashMap<>();
      while (right < n) {
         rightmostPosition.put(s.charAt(right), right++);

         if (rightmostPosition.size() == k + 1) {
            int lowestIndex = Collections.min(rightmostPosition.values());
            rightmostPosition.remove(s.charAt(lowestIndex));
            left = lowestIndex + 1;
         }

         maxLen = Math.max(maxLen, right - left);
      }


      return maxLen;
   }
}

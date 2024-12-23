package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 471. Encode String with Shortest Length
 * Given a string s, encode the string such that its encoded length is the shortest.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. k should be a positive integer.
 * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them.
 *
 * Example 1:
 *   Input: s = "aaa"
 *   Output: "aaa"
 *   Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
 *
 * Example 2:
 *   Input: s = "aaaaa"
 *   Output: "5[a]"
 *   Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 *
 * Example 3:
 *   Input: s = "aaaaaaaaaa"
 *   Output: "10[a]"
 *   Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
 *
 * Constraints:
 *   1 <= s.length <= 150
 *   s consists of only lowercase English letters.
 */
public class LC0471 {
   private static final int MIN_LENGTH_TO_TRANSFORM = 5;

   public String encode(String str) {
      return encode(str, new HashMap<>());
   }

   private String encode(String str, Map<String, String> map) {
      if (str.length() < MIN_LENGTH_TO_TRANSFORM) {
         return str;
      }

      if (!map.containsKey(str)) {
         String minStr = str.charAt(0) + encode(str.substring(1), map);

         stepLabel:
         for (int step = 1; step <= str.length() / 2; step++) {
            for (int times = 1; times < str.length() / step; times++) {
               for (int i = 0; i < step; i++) {
                  if (str.charAt(i) != str.charAt(step * times + i)) {
                     continue stepLabel;
                  }
               }

               String candidate = (times + 1) + "[" + encode(str.substring(0, step), map) + "]" + encode(str.substring(step * (times + 1)), map);
               if (candidate.length() < minStr.length()) {
                  minStr = candidate;
               }
            }
         }
         map.put(str, minStr);
      }

      return map.get(str);
   }
}

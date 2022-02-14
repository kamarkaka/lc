package com.kamarkaka;

import java.util.Stack;

/***
 * 394. Decode String
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 *
 * Example 1:
 *   Input: s = "3[a]2[bc]"
 *   Output: "aaabcbc"
 *
 * Example 2:
 *   Input: s = "3[a2[c]]"
 *   Output: "accaccacc"
 *
 * Example 3:
 *   Input: s = "2[abc]3[cd]ef"
 *   Output: "abcabccdcdcdef"
 *
 * Constraints:
 *   1 <= s.length <= 30
 *   s consists of lowercase English letters, digits, and square brackets '[]'.
 *   s is guaranteed to be a valid input.
 *   All the integers in s are in the range [1, 300].
 */
public class LC0394 {
   public String decodeString(String s) {
      Stack<Integer> numStack = new Stack<>();
      Stack<StringBuilder> strStack = new Stack<>();
      StringBuilder sb = new StringBuilder();

      int repeat = 0;
      for (char c : s.toCharArray()) {
         if ('0' <= c && c <= '9') {
            repeat = 10 * repeat + (c - '0');
         } else if (c == '[') {
            numStack.push(repeat);
            repeat = 0;

            strStack.push(new StringBuilder(sb));
            sb = new StringBuilder();
         } else if (c == ']') {
            String str = sb.toString();
            sb = strStack.pop();

            int num = numStack.pop();
            for (int i = 0; i < num; i++) {
               sb.append(str);
            }
         } else {
            sb.append(c);
         }
      }

      return sb.toString();
   }
}

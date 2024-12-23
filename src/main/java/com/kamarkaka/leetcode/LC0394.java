package com.kamarkaka.leetcode;

import java.util.Stack;

/***
 * 394. Decode String
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are
 * well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
 * repeat numbers, k. For example, there will not be input like 3a or 2[4].
 * Example 1:
 *   Input: s = "3[a]2[bc]"
 *   Output: "aaabcbc"
 * Example 2:
 *   Input: s = "3[a2[c]]"
 *   Output: "accaccacc"
 * Example 3:
 *   Input: s = "2[abc]3[cd]ef"
 *   Output: "abcabccdcdcdef"
 * Constraints:
 *   1 <= s.length <= 30
 *   s consists of lowercase English letters, digits, and square brackets '[]'.
 *   s is guaranteed to be a valid input.
 *   All the integers in s are in the range [1, 300].
 */
public class LC0394 {
   public String decodeString(String s) {
      Stack<Integer> freqStack = new Stack<>();
      Stack<StringBuilder> strStack = new Stack<>();
      StringBuilder currStr = new StringBuilder();
      int k = 0;

      for (char ch : s.toCharArray()) {
         if (Character.isDigit(ch)) {
            k = k * 10 + (ch - '0');
         } else if (ch == '[') {
            freqStack.push(k);
            strStack.push(currStr);

            // reset
            k = 0;
            currStr = new StringBuilder();
         } else if (ch == ']') {
            int count = freqStack.pop();
            StringBuilder decodedStr = strStack.pop();
            for (int i = 0; i < count; i++) {
               decodedStr.append(currStr);
            }
            currStr = decodedStr;
         } else {
            currStr.append(ch);
         }
      }

      return currStr.toString();
   }
}

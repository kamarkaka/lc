package com.kamarkaka.leetcode;

/***
 * 344. Reverse String
 * Write a function that reverses a string. The input string is given as an array of characters s.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 * Example 1:
 *   Input: s = ["h","e","l","l","o"]
 *   Output: ["o","l","l","e","h"]
 * Example 2:
 *   Input: s = ["H","a","n","n","a","h"]
 *   Output: ["h","a","n","n","a","H"]
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s[i] is a printable ascii character.
 */
public class LC0344 {
    public String reverseString(String s) {
        if (s == null || s.length() <= 1) return s;

        int start = 0;
        int end = s.length() - 1;

        char[] chars = s.toCharArray();


        while (start < end) {
            char c = chars[start];
            chars[start] = chars[end];
            chars[end] = c;
            start++;
            end--;
        }

        return new String(chars);
    }
}

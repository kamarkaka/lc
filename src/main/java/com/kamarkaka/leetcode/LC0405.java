package com.kamarkaka.leetcode;

/***
 * 405. Convert a Number to Hexadecimal
 * Given a 32-bit integer num, return a string representing its hexadecimal representation. For negative integers, two’s
 * complement method is used.
 * All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in the
 * answer except for the zero itself.
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 * Example 1:
 *   Input: num = 26
 *   Output: "1a"
 * Example 2:
 *   Input: num = -1
 *   Output: "ffffffff"
 * Constraints:
 *   -2^31 <= num <= 2^31 - 1
 */
public class LC0405 {
    public String toHex(int num) {
        String result = "";
        char[] hexMap = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

        for (int i = 0; i < Integer.SIZE / 4; i ++) {
            char c = hexMap[num & 0xf];
            result = c + result;
            num = num >>> 4;
        }

        while (result.length() > 1 && result.charAt(0) == '0') {
            result = result.substring(1);
        }

        return result;
    }
}

package com.kamarkaka.leetcode;

/***
 * 504. Base 7
 * Given an integer num, return a string of its base 7 representation.
 * Example 1:
 *   Input: num = 100
 *   Output: "202"
 * Example 2:
 *   Input: num = -7
 *   Output: "-10"
 * Constraints:
 *   -10^7 <= num <= 10^7
 */
public class LC0504 {
    public String convertToBase7(int num) {
        if (num == 0) return "0";

        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;

        if (num < 0) {
            isNegative = true;
            num = -num;
        }

        while (num != 0) {
            int res = num % 7;
            num /= 7;
            sb.append(res);
        }

        if (isNegative) {
            sb.append("-");
        }

        return sb.reverse().toString();
    }
}

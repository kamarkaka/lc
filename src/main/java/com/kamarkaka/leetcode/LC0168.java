package com.kamarkaka.leetcode;

/***
 * 168. Excel Sheet Column Title
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 * For example:
 *   A -> 1
 *   B -> 2
 *   C -> 3
 *   ...
 *   Z -> 26
 *   AA -> 27
 *   AB -> 28
 *   ...
 * Example 1:
 *   Input: columnNumber = 1
 *   Output: "A"
 * Example 2:
 *   Input: columnNumber = 28
 *   Output: "AB"
 * Example 3:
 *   Input: columnNumber = 701
 *   Output: "ZY"
 * Constraints:
 * 1 <= columnNumber <= 2^31 - 1
 */
public class LC0168 {
    public String convertToTitle(int n) {
        int test = n;
        StringBuilder sb = new StringBuilder();

        while (test > 0) {
            test--;
            int res = test % 26;
            test = test / 26;

            sb.insert(0, (char) ('A' + res));
        }

        return sb.toString();
    }
}

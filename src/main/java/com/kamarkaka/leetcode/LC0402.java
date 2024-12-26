package com.kamarkaka.leetcode;

/***
 * 402. Remove K Digits
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer
 * after removing k digits from num.
 * Example 1:
 *   Input: num = "1432219", k = 3
 *   Output: "1219"
 *   Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 *   Input: num = "10200", k = 1
 *   Output: "200"
 *   Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 *   Input: num = "10", k = 2
 *   Output: "0"
 *   Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 * Constraints:
 *   1 <= k <= num.length <= 10^5
 *   num consists of only digits.
 *   num does not have any leading zeros except for the zero itself.
 */
public class LC0402 {
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0 || k >= num.length()) return "0";
        char[] res = new char[num.length()];
        int pos = 0;
        int len = num.length() - k;
        for (char c : num.toCharArray()) {
            while (pos > 0 && k > 0 && res[pos - 1] > c) { /// k > 0
                pos--;
                k--;
            }
            res[pos++] = c;
        }
        int start = 0;
        while (start < len && res[start] == '0') start++;
        return start == len? "0" : new String(res, start, len - start);
    }
}

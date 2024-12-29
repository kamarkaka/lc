package com.kamarkaka.leetcode;

/***
 * 246. Strobogrammatic Number
 * Given a string num which represents an integer, return true if num is a strobogrammatic number.
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Example 1:
 *   Input: num = "69"
 *   Output: true
 * Example 2:
 *   Input: num = "88"
 *   Output: true
 * Example 3:
 *   Input: num = "962"
 *   Output: false
 * Constraints:
 *   1 <= num.length <= 50
 *   num consists of only digits.
 *   num does not contain any leading zeros except for zero itself.
 */
public class LC0246 {
    public boolean isStrobogrammatic(String num) {
        int pl = 0, pr = num.length() - 1;
        while (pl <= pr) {
            char cl = num.charAt(pl++), cr = num.charAt(pr--);

            if (pl > 0 && cl == '0' && cr == '0') continue;
            if (cl == '1' && cr == '1') continue;
            if (cl == '8' && cr == '8') continue;
            if (cl == '6' && cr == '9') continue;
            if (cl == '9' && cr == '6') continue;
            return false;
        }
        return true;
    }
}

package com.kamarkaka.leetcode;

/***
 * 788. Rotated Digits
 * An integer x is a good if after rotating each digit individually by 180 degrees, we get a valid number that is
 * different from x. Each digit must be rotated - we cannot choose to leave it alone.
 * A number is valid if each digit remains a digit after rotation. For example:
 *   0, 1, and 8 rotate to themselves,
 *   2 and 5 rotate to each other (in this case they are rotated in a different direction, in other words, 2 or 5 gets
 *   mirrored),
 *   6 and 9 rotate to each other, and
 *   the rest of the numbers do not rotate to any other number and become invalid.
 * Given an integer n, return the number of good integers in the range [1, n].
 * Example 1:
 *   Input: n = 10
 *   Output: 4
 *   Explanation: There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 *   Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 * Example 2:
 *   Input: n = 1
 *   Output: 0
 * Example 3:
 *   Input: n = 2
 *   Output: 1
 * Constraints:
 *   1 <= n <= 10^4
 */
public class LC0788 {
    public int rotatedDigits(int N) {
        int count = 0;
        for (int num = 0; num <= N; num++) {
            if (rotate(num)) count++;
        }
        return count;
    }

    private boolean rotate(int num) {
        boolean valid = false;

        while (num > 0) {
            if (num % 10 == 2) valid = true;
            if (num % 10 == 5) valid = true;
            if (num % 10 == 6) valid = true;
            if (num % 10 == 9) valid = true;
            if (num % 10 == 3) return false;
            if (num % 10 == 4) return false;
            if (num % 10 == 7) return false;
            num /= 10;
        }

        return valid;
    }
}

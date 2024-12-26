package com.kamarkaka.leetcode;

/***
 * 367. Valid Perfect Square
 * Given a positive integer num, return true if num is a perfect square or false otherwise.
 * A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer
 * with itself.
 * You must not use any built-in library function, such as sqrt.
 * Example 1:
 *   Input: num = 16
 *   Output: true
 *   Explanation: We return true because 4 * 4 = 16 and 4 is an integer.
 * Example 2:
 *   Input: num = 14
 *   Output: false
 *   Explanation: We return false because 3.742 * 3.742 = 14 and 3.742 is not an integer.
 * Constraints:
 *   1 <= num <= 2^31 - 1
 */
public class LC0367 {
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        if (num < 2) return true;
        int start = 1, end = num / 2;

        while (true) {
            int middle = start + (end - start) / 2;

            if (start == middle) {
                return (middle == num / middle && num % middle == 0) || (end == num / end && num % end == 0);
            }

            if (num / middle == middle) {
                if (num % middle == 0) {
                    return true;
                } else {
                    start = middle;
                }
            } else if (num / middle > middle) {
                start = middle;
            } else {
                end = middle;
            }
        }
    }
}

package com.kamarkaka.leetcode;

/***
 * 374. Guess Number Higher or Lower
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 * You call a pre-defined API int guess(int num), which returns three possible results:
 *   -1: Your guess is higher than the number I picked (i.e. num > pick).
 *   1: Your guess is lower than the number I picked (i.e. num < pick).
 *   0: your guess is equal to the number I picked (i.e. num == pick).
 * Return the number that I picked.
 * Example 1:
 *   Input: n = 10, pick = 6
 *   Output: 6
 * Example 2:
 *   Input: n = 1, pick = 1
 *   Output: 1
 * Example 3:
 *   Input: n = 2, pick = 1
 *   Output: 1
 * Constraints:
 *   1 <= n <= 2^31 - 1
 *   1 <= pick <= n
 */
public class LC0374 {
    public int guessNumber(int n) {
        return guesswork(1, n);
    }

    private int guesswork(int l, int r) {
        if (l == r) return l;
        if (guess(l) == 0) return l;
        if (guess(r) == 0) return r;

        int m = l + (r - l) / 2;
        int res = guess(m);
        if (res == 0) return m;
        if (res == -1) {
            return guesswork(l, m);
        } else {
            return guesswork(m, r);
        }
    }

    private int guess(int num) {
        return -1;
    }
}

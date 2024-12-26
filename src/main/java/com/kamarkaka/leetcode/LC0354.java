package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 354. Russian Doll Envelopes
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of
 * an envelope.
 * One envelope can fit into another if and only if both the width and height of one envelope are greater than the other
 * envelope's width and height.
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 * Note: You cannot rotate an envelope.
 * Example 1:
 *   Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 *   Output: 3
 *   Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * Example 2:
 *   Input: envelopes = [[1,1],[1,1],[1,1]]
 *   Output: 1
 * Constraints:
 *   1 <= envelopes.length <= 10^5
 *   envelopes[i].length == 2
 *   1 <= wi, hi <= 10^5
 */
public class LC0354 {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) return 0;
        Arrays.sort(envelopes, (e1, e2) -> (e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0]));
        int[] dp = new int[envelopes.length];
        int len = 0;
        for (int[] envelope : envelopes) {
            int left = 0, right = len;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (dp[mid] < envelope[1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            dp[left] = envelope[1];
            if (left == len) len++;
        }
        return len;
    }
}

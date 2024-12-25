package com.kamarkaka.leetcode;

import java.util.HashSet;
import java.util.Set;

/***
 * 60. Permutation Sequence
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * Example 1:
 *   Input: n = 3, k = 3
 *   Output: "213"
 * Example 2:
 *   Input: n = 4, k = 9
 *   Output: "2314"
 * Example 3:
 *   Input: n = 3, k = 1
 *   Output: "123"
 * Constraints:
 *   1 <= n <= 9
 *   1 <= k <= n!
 */
public class LC0060 {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        Set<Integer> usedNumberSet = new HashSet<>();

        int product = 1;
        for (int i = 2; i <= n; i++) {
            product *= i;
        }

        for (int i = n; i >= 1; i--) {
            product = product / i;
            int seq = (k - 1) / product + 1;
            int num = 0, j = 0;

            while (j < seq) {
                num++;
                if (!usedNumberSet.contains(num)) j++;
            }

            sb.append(num);
            usedNumberSet.add(num);

            k -= (seq - 1) * product;
        }

        return sb.toString();
    }

}

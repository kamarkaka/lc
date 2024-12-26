package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 254. Factor Combinations
 * Numbers can be regarded as the product of their factors.
 *   For example, 8 = 2 x 2 x 2 = 2 x 4.
 * Given an integer n, return all possible combinations of its factors. You may return the answer in any order.
 * Note that the factors should be in the range [2, n - 1].
 * Example 1:
 *   Input: n = 1
 *   Output: []
 * Example 2:
 *   Input: n = 12
 *   Output: [[2,6],[3,4],[2,2,3]]
 * Example 3:
 *   Input: n = 37
 *   Output: []
 * Constraints:
 *   1 <= n <= 10^7
 */
public class LC0254 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n <= 3) return result;
        helper(n, -1, new ArrayList<>(), result);
        return result;
    }

    public void helper(int n, int lower, List<Integer> list, List<List<Integer>> result) {
        if (lower != -1) {
            list.add(n);
            result.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
        }

        int upper = (int) Math.sqrt(n);
        for (int i = Math.max(2, lower); i <= upper; i++) {
            if (n % i == 0) {
                list.add(i);
                helper(n / i, i, list, result);
                list.remove(list.size() - 1);
            }
        }
    }

}

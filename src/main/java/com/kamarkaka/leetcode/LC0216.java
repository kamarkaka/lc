package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * 216. Combination Sum III
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *   Only numbers 1 through 9 are used.
 *   Each number is used at most once.
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the
 * combinations may be returned in any order.
 * Example 1:
 *   Input: k = 3, n = 7
 *   Output: [[1,2,4]]
 *   Explanation:
 *   1 + 2 + 4 = 7
 *   There are no other valid combinations.
 * Example 2:
 *   Input: k = 3, n = 9
 *   Output: [[1,2,6],[1,3,5],[2,3,4]]
 *   Explanation:
 *   1 + 2 + 6 = 9
 *   1 + 3 + 5 = 9
 *   2 + 3 + 4 = 9
 *   There are no other valid combinations.
 * Example 3:
 *   Input: k = 4, n = 1
 *   Output: []
 *   Explanation: There are no valid combinations.
 *   Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there
 *   are no valid combination.
 * Constraints:
 *   2 <= k <= 9
 *   1 <= n <= 60
 */
public class LC0216 {
    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> arr = new LinkedList<>();
        dfs(k, n, 0, 0, arr);
        return res;
    }

    private void dfs(int k, int n, int lvl, int sum, List<Integer> arr) {
        if (lvl == k - 1) {
            for (int i = 1; i <= 9; i++) {
                if (arr.size() > 0 && arr.getLast() >= i) continue;
                if (sum + i != n) continue;
                arr.addLast(i);

                List<Integer> res1 = new ArrayList<>(arr);
                res.add(res1);

                arr.removeLast();
            }
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (arr.size() > 0 && arr.getLast() >= i) continue;
            if (sum + i >= n) continue;
            arr.addLast(i);
            dfs(k, n, lvl + 1, sum + i, arr);
            arr.removeLast();
        }
    }
}

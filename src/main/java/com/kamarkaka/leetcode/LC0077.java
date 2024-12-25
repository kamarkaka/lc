package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 77. Combinations
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * You may return the answer in any order.
 * Example 1:
 *   Input: n = 4, k = 2
 *   Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 *   Explanation: There are 4 choose 2 = 6 total combinations.
 *   Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 * Example 2:
 *   Input: n = 1, k = 1
 *   Output: [[1]]
 *   Explanation: There is 1 choose 1 = 1 total combination.
 * Constraints:
 *   1 <= n <= 20
 *   1 <= k <= n
 */
public class LC0077 {
    public List<List<Integer>> combine(int n, int k) {
        if (n < 1 || k < 1 || k > n) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    public void dfs(List<List<Integer>> result, ArrayList<Integer> cur, int currIndex, int n, int k) {
        if (k == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for(int i = currIndex; i <= n - k + 1; i++) {
            cur.add(i);
            dfs(result, cur, i + 1, n, k - 1);
            cur.remove(cur.size() - 1);
        }
    }
}

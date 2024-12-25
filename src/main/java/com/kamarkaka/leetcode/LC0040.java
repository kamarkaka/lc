package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 40. Combination Sum II
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in
 * candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 * Example 1:
 *   Input: candidates = [10,1,2,7,6,1,5], target = 8
 *   Output: [
 *     [1,1,6],
 *     [1,2,5],
 *     [1,7],
 *     [2,6]
 *   ]
 * Example 2:
 *   Input: candidates = [2,5,2,1,2], target = 5
 *   Output: [
 *     [1,2,2],
 *     [5]
 *   ]
 * Constraints:
 *   1 <= candidates.length <= 100
 *   1 <= candidates[i] <= 50
 *   1 <= target <= 30
 */
public class LC0040 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(candidates);
        helper(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> list, int[] candidates, int index, int target) {
        if (target == 0) {
            result.add(list);
            return;
        }

        if (index == candidates.length) return;

        for (int i = index; i < candidates.length; i++) {
            if (i == index || candidates[i] != candidates[i - 1]) {
                if (candidates[i] > target) return;

                List<Integer> newList = new ArrayList<>(list);
                newList.add(candidates[i]);
                helper(result, newList, candidates, i + 1, target - candidates[i]);
            }
        }
    }
}

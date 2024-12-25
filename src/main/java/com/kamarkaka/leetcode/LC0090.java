package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 90. Subsets II
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * Example 1:
 *   Input: nums = [1,2,2]
 *   Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *   Input: nums = [0]
 *   Output: [[],[0]]
 * Constraints:
 *   1 <= nums.length <= 10
 *   -10 <= nums[i] <= 10
 */
public class LC0090 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), result);
        return result;
    }

    void dfs(int[] a, int idx, List<Integer> currList, List<List<Integer>> result) {
        result.add(new ArrayList<>(currList));

        for (int i = idx; i < a.length; i++) {
            if(i > idx && a[i] == a[i - 1]) continue;
            currList.add(a[i]);
            dfs(a, i + 1, currList, result);
            currList.remove(currList.size() - 1);
        }
    }
}

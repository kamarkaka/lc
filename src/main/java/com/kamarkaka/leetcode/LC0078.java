package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 78. Subsets
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * Example 1:
 *   Input: nums = [1,2,3]
 *   Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 *   Input: nums = [0]
 *   Output: [[],[0]]
 * Constraints:
 *   1 <= nums.length <= 10
 *   -10 <= nums[i] <= 10
 *   All the numbers of nums are unique.
 */
public class LC0078 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        helper(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> currList, int[] nums, int startIndex){
        result.add(new ArrayList<>(currList));

        for(int i = startIndex; i < nums.length; i++){
            currList.add(nums[i]);
            helper(result, currList, nums, i + 1);
            currList.remove(currList.size() - 1);
        }
    }
}

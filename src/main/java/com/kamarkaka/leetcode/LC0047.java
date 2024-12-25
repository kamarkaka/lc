package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/***
 * 47. Permutations II
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any
 * order.
 * Example 1:
 *   Input: nums = [1,1,2]
 *   Output: [[1,1,2], [1,2,1], [2,1,1]]
 * Example 2:
 *   Input: nums = [1,2,3]
 *   Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Constraints:
 *   1 <= nums.length <= 8
 *   -10 <= nums[i] <= 10
 */
public class LC0047 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        HashSet<List<Integer>> resultSet = recursion(nums, 0);
        for (List<Integer> list : resultSet) result.add(list);
        return result;
    }

    private HashSet<List<Integer>> recursion(int[] nums, int index) {
        HashSet<List<Integer>> resultSet = new HashSet<>();
        if (nums == null || nums.length == 0 || index >= nums.length) return resultSet;
        else if (nums.length == 1 || index == nums.length - 1) {
            resultSet.add(Arrays.asList(nums[index]));
            return resultSet;
        } else {
            int num = nums[index];
            HashSet<List<Integer>> listSet = recursion(nums, index + 1);
            for (List<Integer> list : listSet) {
                for (int i = 0; i <= list.size(); i++) {
                    List<Integer> newList = new ArrayList<>();
                    for (int j = 0; j < list.size(); j++) newList.add(list.get(j));
                    if (i < list.size()) {
                        newList.add(i, num);
                    } else {
                        newList.add(num);
                    }
                    resultSet.add(newList);
                }
            }
            return resultSet;
        }
    }
}

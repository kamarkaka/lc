package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * 46. Permutations
 * Example 1:
 *    Input: nums = [1,2,3]
 *    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 *    Input: nums = [0,1]
 *    Output: [[0,1],[1,0]]
 *
 * Example 3:
 *    Input: nums = [1]
 *    Output: [[1]]
 *
 * Constraints:
 *    1 <= nums.length <= 6
 *    -10 <= nums[i] <= 10
 *    All the integers of nums are unique.
 */
public class LC0046 {
   public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> res = new ArrayList<>();
      List<Integer> list = new ArrayList<>();
      for (int num : nums) {
         list.add(num);
      }

      int n = nums.length;
      backtrack(n, list, res, 0);
      return res;
   }

   private void backtrack(int n, List<Integer> list, List<List<Integer>> res, int first) {
      if (first == n) {
         res.add(new ArrayList<>(list));
         return;
      }

      for (int i = first; i < n; i++) {
         Collections.swap(list, first, i);
         backtrack(n, list, res, first + 1);
         Collections.swap(list, first, i);
      }
   }
}

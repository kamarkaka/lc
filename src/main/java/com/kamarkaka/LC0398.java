package com.kamarkaka;

import java.util.*;

/***
 * 398. Random Pick Index
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 * Implement the Solution class:
 *    Solution(int[] nums) Initializes the object with the array nums.
 *    int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.
 *
 * Example 1:
 *    Input
 *       ["Solution", "pick", "pick", "pick"]
 *       [[[1, 2, 3, 3, 3]], [3], [1], [3]]
 *    Output
 *       [null, 4, 0, 2]
 *
 * Explanation
 *    Solution solution = new Solution([1, 2, 3, 3, 3]);
 *    solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 *    solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
 *    solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 *
 * Constraints:
 *    1 <= nums.length <= 2 * 10^4
 *    -2^31 <= nums[i] <= 2^31 - 1
 *    target is an integer from nums.
 *    At most 104 calls will be made to pick.
 */
public class LC0398 {
}

class Solution {
   private Map<Integer, List<Integer>> indices;
   private Random rand;

   public Solution(int[] nums) {
      this.rand = new Random();
      this.indices = new HashMap<>();

      for (int i = 0; i < nums.length; i++) {
         if (!this.indices.containsKey(nums[i])) {
            this.indices.put(nums[i], new ArrayList<>());
         }

         this.indices.get(nums[i]).add(i);
      }
   }

   public int pick(int target) {
      int size = indices.get(target).size();
      return indices.get(target).get(rand.nextInt(size));
   }
}

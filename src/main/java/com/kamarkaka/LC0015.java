/***
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *   Input: nums = [-1,0,1,2,-1,-4]
 *   Output: [[-1,-1,2],[-1,0,1]]
 *
 * Example 2:
 *   Input: nums = []
 *   Output: []
 *
 * Example 3:
 *   Input: nums = [0]
 *   Output: []
 *
 * Constraints:
 *   0 <= nums.length <= 3000
 *   -10^5 <= nums[i] <= 10^5
 */

package com.kamarkaka;

import com.kamarkaka.common.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC0015 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int target = 0;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            while (lo < hi) {
                if (nums[i] + nums[lo] + nums[hi] > target) {
                    hi--;
                } else if (nums[i] + nums[lo] + nums[hi] < target) {
                    lo++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                    while (lo < hi && nums[lo] == nums[lo - 1]) {
                        lo++;
                    }
                }
            }
        }

        return res;
    }

    public static void run() {
        LC0015 solution = new LC0015();
        Utilities.print(solution.threeSum(new int[]{-1,0,1,2,-1,-4}));
        Utilities.print(solution.threeSum(new int[]{}));
        Utilities.print(solution.threeSum(new int[]{0}));
    }
}

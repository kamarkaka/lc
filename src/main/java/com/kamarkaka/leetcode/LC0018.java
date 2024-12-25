package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 18. 4Sum
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]]
 * such that:
 *   0 <= a, b, c, d < n
 *   a, b, c, and d are distinct.
 *   nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * Example 1:
 *   Input: nums = [1,0,-1,0,-2,2], target = 0
 *   Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 *   Input: nums = [2,2,2,2,2], target = 8
 *   Output: [[2,2,2,2]]
 * Constraints:
 *   1 <= nums.length <= 200
 *   -10^9 <= nums[i] <= 10^9
 *   -10^9 <= target <= 10^9
 */
public class LC0018 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>>result = new ArrayList<>();

        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + 3 * nums[nums.length - 1] < target) continue;
            if (nums[i] * 4 > target) break;

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + 2 * nums[nums.length - 1] < target) continue;
                if (nums[i] + nums[j] * 3 > target) break;

                int k3 = j + 1, k4 = nums.length - 1;
                while (k3 < k4) {
                    int sum = nums[i] + nums[j] + nums[k3] + nums[k4];
                    if (sum == target) {
                        if (k3 == j + 1 || nums[k3] != nums[k3 - 1]) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[k3], nums[k4]));
                        }
                        k3++;
                        k4--;
                    } else if (sum > target) {
                        k4--;
                    } else {
                        k3++;
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }
}

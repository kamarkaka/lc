/***
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 *
 * Example 1:
 *   Input: nums = [-1,2,1,-4], target = 1
 *   Output: 2
 *   Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * Example 2:
 *   Input: nums = [0,0,0], target = 1
 *   Output: 0
 *
 * Constraints:
 *   3 <= nums.length <= 1000
 *   -1000 <= nums[i] <= 1000
 *   -10^4 <= target <= 10^4
 */

package com.kamarkaka;

import java.util.Arrays;

public class LC0016 {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;

        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == target) return target;

                if (sum > target) {
                    hi--;
                } else {
                    lo++;
                }

                if (Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                }
            }
        }

        return res;
    }

    public static void run() {
        LC0016 solution = new LC0016();
        System.out.println(solution.threeSumClosest(new int[]{-1,2,1,-4}, 1));
        System.out.println(solution.threeSumClosest(new int[]{0,0,0}, 1));
    }
}

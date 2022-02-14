/***
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 *   Input: nums = [2,7,11,15], target = 9
 *   Output: [0,1]
 *   Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 *   Input: nums = [3,2,4], target = 6
 *   Output: [1,2]
 *
 * Example 3:
 *   Input: nums = [3,3], target = 6
 *   Output: [0,1]
 *
 * Constraints:
 *   2 <= nums.length <= 10^4
 *   -10^9 <= nums[i] <= 10^9
 *   -10^9 <= target <= 10^9
 *   Only one valid answer exists.
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */

package main.java.com.kamarkaka;

import main.java.com.kamarkaka.common.Utilities;

import java.util.HashMap;

public class LC0001 {
    public int[] twoSum1(int[] nums, int target) {
        int[] res = new int[]{0, 0};

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }

        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[]{0, 0};
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            int num2 = target - num1;

            if (hashMap.containsKey(num2)) {
                res[0] = hashMap.get(num2);
                res[1] = i;
                return res;
            } else {
                hashMap.put(num1, i);
            }

        }

        return res;
    }

    public static void run() {
        LC0001 solution = new LC0001();
        Utilities.print(solution.twoSum2(new int[]{2, 7, 11, 15}, 9));
        Utilities.print(solution.twoSum2(new int[]{3, 2, 4}, 6));
        Utilities.print(solution.twoSum2(new int[]{3, 3}, 6));
    }
}

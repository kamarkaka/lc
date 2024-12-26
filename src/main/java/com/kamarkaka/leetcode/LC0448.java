package com.kamarkaka.leetcode;

import java.util.LinkedList;
import java.util.List;

/***
 * 448. Find All Numbers Disappeared in an Array
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the
 * range [1, n] that do not appear in nums.
 * Example 1:
 *   Input: nums = [4,3,2,7,8,2,3,1]
 *   Output: [5,6]
 * Example 2:
 *   Input: nums = [1,1]
 *   Output: [2]
 * Constraints:
 *   n == nums.length
 *   1 <= n <= 10^5
 *   1 <= nums[i] <= n
 * Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count
 * as extra space.
 */
public class LC0448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new LinkedList<>();
        int index = 0;
        while (index < nums.length) {

            if (nums[index] == index + 1) {
                index++;
                continue;
            }

            int num = nums[index];

            if (nums[index] != nums[num - 1]) {
                int tmp = num;
                nums[index] = nums[num - 1];
                nums[num - 1] = tmp;
            } else {
                index++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) {
                result.add(i + 1);
            }
        }

        return result;

    }
}

package com.kamarkaka.leetcode;

/***
 * 540. Single Element in a Sorted Array
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one
 * element which appears exactly once.
 * Return the single element that appears only once.
 * Your solution must run in O(log n) time and O(1) space.
 * Example 1:
 *   Input: nums = [1,1,2,3,3,4,4,8,8]
 *   Output: 2
 * Example 2:
 *   Input: nums = [3,3,7,7,10,11,11]
 *   Output: 10
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   0 <= nums[i] <= 10^5
 */
public class LC0540 {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0, hi = nums.length - 1, mid, len;
        while (lo < hi) {
            len = (hi - lo) / 2;
            mid = lo + len;

            if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) return nums[mid];
            else if (nums[mid - 1] == nums[mid]) {
                if (len % 2 == 0) hi = mid;
                else lo = mid + 1;
            } else {
                if (len % 2 == 0) lo = mid;
                else hi = mid - 1;
            }
        }

        return nums[lo];
    }
}

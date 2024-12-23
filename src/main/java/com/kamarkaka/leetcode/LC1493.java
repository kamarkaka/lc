package com.kamarkaka.leetcode;

/***
 * 1493. Longest Subarray of 1's After Deleting One Element
 * Given a binary array nums, you should delete one element from it.
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no
 * such subarray.
 * Example 1:
 *   Input: nums = [1,1,0,1]
 *   Output: 3
 *   Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * Example 2:
 *   Input: nums = [0,1,1,1,0,1,1,0,1]
 *   Output: 5
 *   Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is
 *   [1,1,1,1,1].
 * Example 3:
 *   Input: nums = [1,1,1]
 *   Output: 2
 *   Explanation: You must delete one element.
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   nums[i] is either 0 or 1.
 */
public class LC1493 {
    public int longestSubarray(int[] nums) {
        int pl = 0, pr = 0;
        int len = 0, maxLen = 0;
        boolean deleted = false;

        while (pr < nums.length) {
            if (nums[pr] == 1) {
                pr++;
                len++;
            } else {
                if (!deleted) {
                    deleted = true;
                    pr++;
                    len++;
                } else {
                    if (nums[pl] == 1) {
                        pl++;
                        len--;
                    } else {
                        pl++;
                        len--;
                        deleted = false;
                    }
                }
            }

            maxLen = Math.max(maxLen, len);
        }

        return maxLen - 1;
    }
}

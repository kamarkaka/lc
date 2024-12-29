package com.kamarkaka.leetcode;

/***
 * 1060. Missing Element in Sorted Array
 * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an
 * integer k, return the kth missing number starting from the leftmost number of the array.
 * Example 1:
 *   Input: nums = [4,7,9,10], k = 1
 *   Output: 5
 *   Explanation: The first missing number is 5.
 * Example 2:
 *   Input: nums = [4,7,9,10], k = 3
 *   Output: 8
 *   Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.
 * Example 3:
 *   Input: nums = [1,2,4], k = 3
 *   Output: 6
 *   Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 * Constraints:
 *   1 <= nums.length <= 5 * 10^4
 *   1 <= nums[i] <= 10^7
 *   nums is sorted in ascending order, and all the elements are unique.
 *   1 <= k <= 10^8
 * Follow up: Can you find a logarithmic time complexity (i.e., O(log(n))) solution?
 */
public class LC1060 {
    public int missingElement(int[] nums, int k) {
        int currentNumber = 0;
        for (int num : nums) {
            if (currentNumber == 0) {
                currentNumber = num;
                continue;
            }

            if (k > num - currentNumber - 1) {
                k -= num - currentNumber - 1;
                currentNumber = num;
            } else {
                return currentNumber + k;
            }
        }

        return currentNumber + k;
    }

    public int missingElement2(int[] nums, int k) {
        int n = nums.length;
        int low = 0;
        int high = n-1;

        // Check how many elements are missing up to the last index
        int missingCount = nums[high] - nums[0] - high;
        if (missingCount < k) {
            // If the missing count is less than k, the missing number is beyond the array
            return nums[high] + (k - missingCount);
        }

        while (low < high) {
            int mid = high - (high - low) / 2;
            int missingTilMid = nums[mid] - nums[0] - mid;
            if (missingTilMid < k) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return nums[0] + k + low;
    }

    public static void main(String[] args) {
        LC1060 solution = new LC1060();
        System.out.println(solution.missingElement2(new int[]{4,7,9,10}, 3));
    }
}

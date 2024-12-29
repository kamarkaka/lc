package com.kamarkaka.leetcode;

/***
 * 1287. Element Appearing More Than 25% In Sorted Array
 * Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more
 * than 25% of the time, return that integer.
 * Example 1:
 *   Input: arr = [1,2,2,6,6,6,6,7,10]
 *   Output: 6
 * Example 2:
 *   Input: arr = [1,1]
 *   Output: 1
 * Constraints:
 *   1 <= arr.length <= 10^4
 *   0 <= arr[i] <= 10^5
 */
public class LC1287 {
    public int findSpecialInteger(int[] arr) {
        int num = arr[0];
        int count = 0;
        int expectedCount = arr.length / 4 + 1;

        for (int n : arr) {
            if (n == num) {
                count++;
                if (count >= expectedCount) return num;
            } else {
                num = n;
                count = 1;
            }
        }
        return -1;
    }
}

package com.kamarkaka.leetcode;

/***
 * 768. Max Chunks To Make Sorted II
 * You are given an integer array arr.
 * We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After concatenating
 * them, the result should equal the sorted array.
 * Return the largest number of chunks we can make to sort the array.
 * Example 1:
 *   Input: arr = [5,4,3,2,1]
 *   Output: 1
 *   Explanation:
 *   Splitting into two or more chunks will not return the required result.
 *   For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
 * Example 2:
 *   Input: arr = [2,1,3,4,4]
 *   Output: 4
 *   Explanation:
 *   We can split into two chunks, such as [2, 1], [3, 4, 4].
 *   However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
 * Constraints:
 *   1 <= arr.length <= 2000
 *   0 <= arr[i] <= 108
 */
public class LC0768 {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] maxLeft = new int[n];
        int[] minRight = new int[n];

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            max = Math.max(max, arr[i]);
            maxLeft[i] = max;
        }

        int min = Integer.MAX_VALUE;
        for(int i = n-1; i >= 0; i--){
            min = Math.min(min, arr[i]);
            minRight[i] = min;
        }

        int ans = 1;
        for(int i = 0; i < n - 1; i++){
            if(maxLeft[i] <= minRight[i+1]) ans++; //  note
        }

        return ans;
    }
}

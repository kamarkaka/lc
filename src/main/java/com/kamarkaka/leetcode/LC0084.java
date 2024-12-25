package com.kamarkaka.leetcode;

/***
 * 84. Largest Rectangle in Histogram
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return
 * the area of the largest rectangle in the histogram.
 * Example 1:
 *   Input: heights = [2,1,5,6,2,3]
 *   Output: 10
 *   Explanation: The above is a histogram where width of each bar is 1.
 *   The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 *   Input: heights = [2,4]
 *   Output: 4
 * Constraints:
 *   1 <= heights.length <= 10^5
 *   0 <= heights[i] <= 10^4
 */
public class LC0084 {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int[] fromLeft = new int[heights.length];
        int[] fromRight = new int[heights.length];

        fromLeft[0] = -1;
        fromRight[heights.length-1] = heights.length;

        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;

            while (p >= 0 && heights[p] >= heights[i]) {
                p = fromLeft[p];
            }
            fromLeft[i] = p;
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < heights.length && heights[p] >= heights[i]) {
                p = fromRight[p];
            }
            fromRight[i] = p;
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] * (fromRight[i] - fromLeft[i] - 1));
        }
        return max;
    }
}

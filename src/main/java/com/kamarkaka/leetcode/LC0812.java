package com.kamarkaka.leetcode;

/***
 * 812. Largest Triangle Area
 * Given an array of points on the X-Y plane points where points[i] = [xi, yi], return the area of the largest triangle
 * that can be formed by any three different points. Answers within 10-5 of the actual answer will be accepted.
 * Example 1:
 *   Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 *   Output: 2.00000
 *   Explanation: The five points are shown in the above figure. The red triangle is the largest.
 * Example 2:
 *   Input: points = [[1,0],[0,0],[0,1]]
 *   Output: 0.50000
 * Constraints:
 *   3 <= points.length <= 50
 *   -50 <= xi, yi <= 50
 *   All the given points are unique.
 */
public class LC0812 {
    public double largestTriangleArea(int[][] points) {
        double result = 0;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    int[] p1 = points[i];
                    int[] p2 = points[j];
                    int[] p3 = points[k];

                    double area = Math.abs(
                            p1[0] * (p2[1] - p3[1]) +
                                    p2[0] * (p3[1] - p1[1]) +
                                    p3[0] * (p1[1] - p2[1])
                    ) / (double) 2;
                    result = Math.max(result, area);
                }
            }
        }

        return result;
    }
}

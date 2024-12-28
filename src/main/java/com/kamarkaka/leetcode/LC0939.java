package com.kamarkaka.leetcode;

/***
 * 939. Minimum Area Rectangle
 * You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
 * Return the minimum area of a rectangle formed from these points, with sides parallel to the X and Y axes. If there is
 * not any such rectangle, return 0.
 * Example 1:
 *   Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
 *   Output: 4
 * Example 2:
 *   Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 *   Output: 2
 * Constraints:
 *   1 <= points.length <= 500
 *   points[i].length == 2
 *   0 <= xi, yi <= 4 * 10^4
 *   All the given points are unique.
 */
public class LC0939 {
    public int minAreaRect(int[][] points) {
        boolean areaFound = false;
        int minArea = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int w1 = points[i][0] - points[j][0], w2 = points[i][1] - points[j][1];
                if(w1 == 0 || w2 == 0) continue;

                int area = Math.abs(w1) * Math.abs(w2);
                if(area > minArea) continue;

                boolean point3 = false, point4 = false;
                for (int[] p3: points) {
                    if (p3[0] == points[i][0] && p3[1] == points[j][1]) {
                        point3 = true;
                        break;
                    }
                }

                for (int[] p4: points) {
                    if (p4[0] == points[j][0] && p4[1] == points[i][1]) {
                        point4 = true;
                        break;
                    }
                }

                if (point3 && point4) {
                    minArea = area;
                    areaFound = true;
                }
            }
        }

        return areaFound ? minArea : 0;
    }
}

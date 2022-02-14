/***
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 * Example 1:
 *   Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *   Output: 6
 *   Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 *
 * Example 2:
 *   Input: height = [4,2,0,3,2,5]
 *   Output: 9
 *
 * Constraints:
 *   n == height.length
 *   1 <= n <= 2 * 10^4
 *   0 <= height[i] <= 10^5
 */
package main.java.com.kamarkaka;

public class LC0042 {
    public int trap(int[] height) {
        int i = 0, j = height.length - 1, maxHeight = 0, water = 0;

        while (i < j) {
            if (height[i] < height[j]) {
                if (maxHeight < height[i]) maxHeight = height[i];
                water += (maxHeight - height[i]);
                i++;
            } else {
                if(maxHeight < height[j]) maxHeight = height[j];
                water += (maxHeight - height[j]);
                j--;
            }
        }

        return water;
    }

    public static void run() {
        LC0042 solution = new LC0042();
        System.out.println(solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(solution.trap(new int[]{4,2,0,3,2,5}));
    }
}

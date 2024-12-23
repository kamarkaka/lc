package com.kamarkaka.marshallwace;

/***
 * Given an unsorted array arr[] and two numbers x and y, find the minimum distance between x and y in arr[]. The array
 * might also contain duplicates. You may assume that both x and y are different and present in arr[].
 *
 * Examples:
 * Input: arr[] = {1, 2}, x = 1, y = 2
 * Output: Minimum distance between 1 and 2 is 1.
 *
 * Input: arr[] = {3, 4, 5}, x = 3, y = 5
 * Output: Minimum distance between 3 and 5 is 2.
 *
 * Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3},  x = 3, y = 6
 * Output: Minimum distance between 3 and 6 is 4.
 *
 * Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2
 * Output: Minimum distance between 3 and 2 is 1.
 */
public class MinimumDistance {
    public int minDist(int[] arr, int x, int y) {
        int rightMostXIndex = -1;
        int rightMostYIndex = -1;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                rightMostXIndex = i;

                if (rightMostYIndex >= 0) {
                    minDist = Math.min(minDist, i - rightMostYIndex);
                }
            } else if (arr[i] == y) {
                rightMostYIndex = i;
                if (rightMostXIndex >= 0) {
                    minDist = Math.min(minDist, i - rightMostXIndex);
                }
            }
        }

        return minDist;
    }

    public static void main(String[] args) {
        MinimumDistance solution = new MinimumDistance();
        System.out.println(solution.minDist(new int[]{1, 2}, 1, 2));
        System.out.println(solution.minDist(new int[]{3, 4, 5}, 3, 5));
        System.out.println(solution.minDist(new int[]{3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, 3, 6));
        System.out.println(solution.minDist(new int[]{2, 5, 3, 5, 4, 4, 2, 3}, 3, 2));
    }
}

package com.kamarkaka.leetcode;

/***
 * 923. 3Sum With Multiplicity
 * Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and
 * arr[i] + arr[j] + arr[k] == target.
 * As the answer can be very large, return it modulo 10^9 + 7.
 * Example 1:
 *   Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 *   Output: 20
 *   Explanation:
 *   Enumerating by the values (arr[i], arr[j], arr[k]):
 *   (1, 2, 5) occurs 8 times;
 *   (1, 3, 4) occurs 8 times;
 *   (2, 2, 4) occurs 2 times;
 *   (2, 3, 3) occurs 2 times.
 * Example 2:
 *   Input: arr = [1,1,2,2,2,2], target = 5
 *   Output: 12
 *   Explanation:
 *   arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
 *   We choose one 1 from [1,1] in 2 ways,
 *   and two 2s from [2,2,2,2] in 6 ways.
 * Example 3:
 *   Input: arr = [2,1,3], target = 6
 *   Output: 1
 *   Explanation: (1, 2, 3) occured one time in the array so we return 1.
 * Constraints:
 *   3 <= arr.length <= 3000
 *   0 <= arr[i] <= 100
 *   0 <= target <= 300
 */
public class LC0923 {
    private static final int MOD = 1_000_000_007;

    public int threeSumMulti(int[] A, int target) {
        long[] count = new long[101];
        for (int x: A) count[x]++;
        long ans = 0;

        // All different
        for (int x = 0; x <= 100; x++) {
            for (int y = x + 1; y <= 100; y++) {
                int z = target - x - y;
                if (y < z && z <= 100) {
                    ans += count[x] * count[y] * count[z];
                    ans %= MOD;
                }
            }
        }

        // x == y != z
        for (int x = 0; x <= 100; x++) {
            int z = target - 2 * x;
            if (x < z && z <= 100) {
                ans += count[x] * (count[x] - 1) / 2 * count[z];
                ans %= MOD;
            }
        }

        // x != y == z
        for (int x = 0; x <= 100; x++) {
            if (target % 2 == x % 2) {
                int y = (target - x) / 2;
                if (x < y && y <= 100) {
                    ans += count[x] * count[y] * (count[y] - 1) / 2;
                    ans %= MOD;
                }
            }
        }

        // x == y == z
        if (target % 3 == 0) {
            int x = target / 3;
            if (0 <= x && x <= 100) {
                ans += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
                ans %= MOD;
            }
        }

        return (int) ans;
    }
}

package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 475. Heaters
 * Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm
 * all the houses.
 * Every house can be warmed, as long as the house is within the heater's warm radius range.
 * Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that
 * those heaters could cover all houses.
 * Notice that all the heaters follow your radius standard, and the warm radius will the same.
 * Example 1:
 *   Input: houses = [1,2,3], heaters = [2]
 *   Output: 1
 *   Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses
 *   can be warmed.
 * Example 2:
 *   Input: houses = [1,2,3,4], heaters = [1,4]
 *   Output: 1
 *   Explanation: The two heaters were placed at positions 1 and 4. We need to use a radius 1 standard, then all the
 *   houses can be warmed.
 * Example 3:
 *   Input: houses = [1,5], heaters = [2]
 *   Output: 3
 * Constraints:
 *   1 <= houses.length, heaters.length <= 3 * 10^4
 *   1 <= houses[i], heaters[i] <= 10^9
 */
public class LC0475 {
    public int findRadius(int[] houses, int[] heaters) {
        int radius = 0;

        Arrays.sort(houses);
        Arrays.sort(heaters);

        int houseIndex = 0;
        int heaterIndex = 0;
        while (houseIndex < houses.length) {

            if (houseIndex < houses.length - 1 && houses[houseIndex] == houses[houseIndex + 1]) {
                houseIndex++;
                continue;
            }

            if (heaterIndex < heaters.length - 1 && heaters[heaterIndex] == heaters[heaterIndex + 1]) {
                heaterIndex++;
                continue;
            }

            if (heaterIndex < heaters.length - 1 && Math.abs(heaters[heaterIndex] - houses[houseIndex]) > Math.abs(heaters[heaterIndex + 1] - houses[houseIndex])) {
                heaterIndex++;
                continue;
            }

            int newRadius = Math.abs(heaters[heaterIndex] - houses[houseIndex]);
            if (newRadius > radius) radius = newRadius;

            houseIndex++;
        }

        return radius;
    }
}

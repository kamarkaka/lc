package com.kamarkaka.cubist;

import java.util.Arrays;

/***
 * Alex will visit a number of houses that are arranged in a line. Each house has a number of coins and an amount of
 * energy available. The journey must begin at the first house, and each house along the journey must be visited. None
 * can be skipped over, but Alex can end the journey at any point. It costs 1 unit of energy to move from one house to
 * the next. Alex can collect either the energy or the coins when visiting a house. Determine the maximum number of
 * coins Alex can collect while never having a negative energy amount.
 *
 * Example
 * n = 3
 * initialEnergy = 0
 * energy = [2, 1, 1]
 * coins = [11, 5, 7]
 * There are n houses in a line. The i-th house has energy[i] energy and coins[i] coins. Alex starts the journey at the
 * first house with initialEnergy energy.
 * The best approach is to collect 2 units of energy at the first house, then 5+7=12 coins at the second and third
 * houses. Alex's energy level is 0 after moving to the second and third houses.
 *
 * Function Description
 * Complete the function getRich in the editor below. The function must return an integer that represents the maximum
 * number of coins that can be collected.
 * getRich has the following parameters:
 * - initialEnergy: long integer
 * - energy: an array of integers
 * - coins: an array of integers
 *
 * Constraints
 * - 1 <= n <= 1000
 * - 0 <= initialEnergy <= 10^14
 * - 0 <= energy[i], coins[i] <= 1000
 */
public class CoinsAndEnergy {
    public int getRich(long initialEnergy, int[] energies, int[] coins) {
        int numOfHouses = coins.length;

        int[] dp = new int[1000 * numOfHouses + 1];
        Arrays.fill(dp, -1);
        dp[(int) initialEnergy] = coins[0];
        dp[(int) initialEnergy + energies[0]] = 0;

        for (int i = 1; i < numOfHouses; i++) {
            int[] nextDp = new int[1000 * numOfHouses + 1];
            Arrays.fill(nextDp, -1);

            for (int e = 0; e < dp.length; e++) {
                if (dp[e] == -1) {
                    continue;
                }

                // pick coin
                if (e > 0) {
                    nextDp[e - 1] = Math.max(nextDp[e - 1], dp[e] + coins[i]);
                }

                // pick energy
                if (e + energies[i] <= 1000 * numOfHouses) {
                    nextDp[e + energies[i] - 1] = Math.max(nextDp[e + energies[i] - 1], dp[e]);
                }

            }

            dp = nextDp;
        }

        int maxCoins = 0;
        for (int j : dp) {
            maxCoins = Math.max(maxCoins, j);
        }
        return maxCoins;
    }

    public static void main(String[] args) {
        CoinsAndEnergy solution = new CoinsAndEnergy();
        System.out.println(solution.getRich(0, new int[]{2, 1, 1}, new int[]{11, 5, 7}));
        System.out.println(solution.getRich(0, new int[]{2, 1, 1}, new int[]{13, 5, 7}));
    }
}

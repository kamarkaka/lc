package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 1996. The Number of Weak Characters in the Game
 * You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
 * A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
 * Return the number of weak characters.
 *
 * Example 1:
 *    Input: properties = [[5,5],[6,3],[3,6]]
 *    Output: 0
 *    Explanation: No character has strictly greater attack and defense than the other.
 *
 * Example 2:
 *    Input: properties = [[2,2],[3,3]]
 *    Output: 1
 *    Explanation: The first character is weak because the second character has a strictly greater attack and defense.
 *
 * Example 3:
 *    Input: properties = [[1,5],[10,4],[4,3]]
 *    Output: 1
 *    Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 *
 * Constraints:
 *    2 <= properties.length <= 10^5
 *    properties[i].length == 2
 *    1 <= attacki, defensei <= 10^5
 */
public class LC1996 {
   public int numberOfWeakCharacters(int[][] properties) {
      Arrays.sort(properties, (i1, i2) -> {
         if (i1[0] != i2[0]) return i1[0] - i2[0];
         return i2[1] - i1[1];
      });

      int max = properties[properties.length - 1][1];
      int count = 0;
      for (int i = properties.length - 2; i >= 0; i--) {
         if (properties[i][1] < max) {
            count++;
         }
         max = Math.max(max, properties[i][1]);
      }
      return count;
   }
}

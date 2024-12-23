package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 1743. Restore the Array From Adjacent Pairs
 * There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
 * You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
 * It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
 * Return the original array nums. If there are multiple solutions, return any of them.
 *
 * Example 1:
 *    Input: adjacentPairs = [[2,1],[3,4],[3,2]]
 *    Output: [1,2,3,4]
 *    Explanation: This array has all its adjacent pairs in adjacentPairs.
 *    Notice that adjacentPairs[i] may not be in left-to-right order.
 *
 * Example 2:
 *    Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
 *    Output: [-2,4,1,-3]
 *    Explanation: There can be negative numbers.
 *    Another solution is [-3,1,4,-2], which would also be accepted.
 *
 * Example 3:
 *    Input: adjacentPairs = [[100000,-100000]]
 *    Output: [100000,-100000]
 *
 * Constraints:
 *    nums.length == n
 *    adjacentPairs.length == n - 1
 *    adjacentPairs[i].length == 2
 *    2 <= n <= 105
 *    -10^5 <= nums[i], ui, vi <= 10^5
 *    There exists some nums that has adjacentPairs as its pairs.
 */
public class LC1743 {
   public int[] restoreArray(int[][] adjacentPairs) {
      Set<Integer> set = new HashSet<>();
      Map<Integer, List<Integer>> hmap = new HashMap<>();

      for (int[] pair : adjacentPairs) {
         int num1 = pair[0], num2 = pair[1];
         if (set.contains(num1)) set.remove(num1);
         else set.add(num1);

         if (set.contains(num2)) set.remove(num2);
         else set.add(num2);

         hmap.putIfAbsent(num1, new LinkedList<>());
         hmap.get(num1).add(num2);
         hmap.putIfAbsent(num2, new LinkedList<>());
         hmap.get(num2).add(num1);
      }

      LinkedList<Integer> list = new LinkedList<>(set);
      int num, nextNum = 0, prevNum = 0;
      int[] res = new int[adjacentPairs.length + 1];
      for (int i = 0; i < res.length; i++) {
         if (i == 0) {
            num = list.pollFirst();
            nextNum = hmap.get(num).get(0);
         } else {
            num = nextNum;
            if (hmap.get(num).size() == 1) {
               nextNum = hmap.get(num).get(0);
            } else {
               nextNum = hmap.get(num).get(0) == prevNum ? hmap.get(num).get(1) : hmap.get(num).get(0);
            }
         }

         prevNum = num;
         res[i] = num;
      }
      return res;
   }
}

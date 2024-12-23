package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 1345. Jump Game IV
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * In one step you can jump from index i to index:
 *    i + 1 where: i + 1 < arr.length.
 *    i - 1 where: i - 1 >= 0.
 *    j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 *    Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 *    Output: 3
 *    Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 *
 * Example 2:
 *    Input: arr = [7]
 *    Output: 0
 *    Explanation: Start index is the last index. You do not need to jump.
 *
 * Example 3:
 *    Input: arr = [7,6,9,6,9,6,9,7]
 *    Output: 1
 *    Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 *
 * Constraints:
 *    1 <= arr.length <= 5 * 10^4
 *    -10^8 <= arr[i] <= 10^8
 */
public class LC1345 {
   public int minJumps(int[] arr) {
      Map<Integer, List<Integer>> map = new HashMap<>();
      for (int i = 0; i < arr.length; i++) {
         map.putIfAbsent(arr[i], new ArrayList<>());
         map.get(arr[i]).add(i);
      }

      int[] dp = new int[arr.length];
      Arrays.fill(dp, -1);
      dp[0] = 0;

      Queue<Integer> queue = new LinkedList<>();
      queue.add(0);

      while (!queue.isEmpty()) {
         int i = queue.poll();
         int num = arr[i];
         if (map.containsKey(num)) {
            List<Integer> idxs = map.get(num);
            for (int idx : idxs) {
               if (dp[idx] == -1 || dp[idx] > dp[i] + 1) {
                  dp[idx] = dp[i] + 1;
                  queue.add(idx);
               }
            }
            map.remove(num);
         }

         if (i - 1 >= 0 && (dp[i-1] == -1 || dp[i-1] > dp[i] + 1)) {
            dp[i-1] = dp[i] + 1;
            queue.add(i-1);
         }
         if (i + 1 <= arr.length - 1 && (dp[i+1] == -1 || dp[i+1] > dp[i] + 1)) {
            dp[i+1] = dp[i] + 1;
            queue.add(i+1);
         }
      }

      return dp[arr.length-1];
   }

   public static void run() {
      LC1345 sol = new LC1345();
      System.out.println(sol.minJumps(new int[] {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,14}));
      System.out.println(sol.minJumps(new int[] {100,-23,-23,404,100,23,23,23,3,404}));
   }
}

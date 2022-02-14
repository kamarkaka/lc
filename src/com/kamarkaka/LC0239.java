package com.kamarkaka;

import com.kamarkaka.common.Utilities;

import java.util.ArrayDeque;

/***
 * 239. Sliding Window Maximum
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the max sliding window.
 *
 * Example 1:
 *    Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 *    Output: [3,3,5,5,6,7]
 *    Explanation:
 *       Window position                Max
 *       ---------------               -----
 *       [1  3  -1] -3  5  3  6  7       3
 *        1 [3  -1  -3] 5  3  6  7       3
 *        1  3 [-1  -3  5] 3  6  7       5
 *        1  3  -1 [-3  5  3] 6  7       5
 *        1  3  -1  -3 [5  3  6] 7       6
 *        1  3  -1  -3  5 [3  6  7]      7
 *
 * Example 2:
 *    Input: nums = [1], k = 1
 *    Output: [1]
 *
 * Constraints:
 *    1 <= nums.length <= 10^5
 *    -10^4 <= nums[i] <= 10^4
 *    1 <= k <= nums.length
 */
public class LC0239 {
   ArrayDeque<Integer> deq = new ArrayDeque<>();
   int [] nums;

   public int[] maxSlidingWindow(int[] nums, int k) {
      int n = nums.length;
      if (n * k == 0) return new int[0];
      if (k == 1) return nums;

      this.nums = nums;
      int maxIdx = 0;
      for (int i = 0; i < k; i++) {
         cleanDequeue(i, k);
         deq.addLast(i);
         if (nums[i] > nums[maxIdx]) maxIdx = i;
      }

      int[] output = new int[n-k+1];
      output[0] = nums[maxIdx];

      for (int i = k; i < n; i++) {
         cleanDequeue(i, k);
         deq.addLast(i);
         output[i-k+1] = nums[deq.getFirst()];
      }
      return output;
   }

   private void cleanDequeue(int i, int k) {
      if (!deq.isEmpty() && deq.getFirst() == i - k) {
         deq.removeFirst();
      }

      while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
         deq.removeLast();
      }
   }

   public static void run() {
      LC0239 solution = new LC0239();
      Utilities.print(solution.maxSlidingWindow(new int[] {1,3,-1,-3,2,3,6,7}, 3));
   }
}

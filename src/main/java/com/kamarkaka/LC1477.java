package main.java.com.kamarkaka;

/***
 * 1477. Find Two Non-overlapping Sub-arrays Each With Target Sum
 * You are given an array of integers arr and an integer target.
 * You have to find two non-overlapping sub-arrays of arr each with a sum equal target. There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
 *
 * Example 1:
 *   Input: arr = [3,2,2,4,3], target = 3
 *   Output: 2
 *   Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.
 *
 * Example 2:
 *   Input: arr = [7,3,4,7], target = 7
 *   Output: 2
 *   Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but we will choose the first and third sub-arrays as the sum of their lengths is 2.
 *
 * Example 3:
 *   Input: arr = [4,3,2,6,2,3,4], target = 6
 *   Output: -1
 *   Explanation: We have only one sub-array of sum = 6.
 *
 * Constraints:
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 1000
 * 1 <= target <= 10^8
 */
public class LC1477 {
   public int minSumOfLengths(int[] arr, int target) {
      int[] mins = new int[arr.length];
      int minLen = Integer.MAX_VALUE;
      int min = Integer.MAX_VALUE;
      int l = 0;
      int sum = 0;
      for (int r = 0; r < arr.length; r++) {
         sum += arr[r];
         while (target < sum) {
            sum -= arr[l++];
         }
         if (sum == target) {
            int len = r - l + 1;
            minLen = Math.min(minLen, len);
            if (l > 0 && mins[l - 1] != Integer.MAX_VALUE) {
               min = Math.min(len + mins[l - 1], min);
            }
         }
         mins[r] = minLen;
      }
      return min == Integer.MAX_VALUE ? -1 : min;
   }

   public static void run() {
      LC1477 solution = new LC1477();
      System.out.println(solution.minSumOfLengths(new int[] {1,6,1}, 7));
      System.out.println(solution.minSumOfLengths(new int[] {2,1,3,3,2,3,1}, 6));
      System.out.println(solution.minSumOfLengths(new int[] {4,3,2,6,2,3,4}, 6));
   }
}

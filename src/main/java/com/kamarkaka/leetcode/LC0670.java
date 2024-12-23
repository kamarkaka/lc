package com.kamarkaka.leetcode;

/***
 * 670. Maximum Swap
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 *
 * Example 1:
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 *
 * Constraints:
 *    0 <= num <= 10^8
 */
public class LC0670 {
   public int maximumSwap(int num) {
      if (num < 10) return num;

      char[] numArray = String.valueOf(num).toCharArray();
      int[] index = new int[10];

      for (int i = 0; i < numArray.length; i++) {
         index[numArray[i] - '0'] = i;
      }

      for (int i = 0; i < numArray.length; i++) {
         for (char j = '9'; j >= '0'; j--) {
            if (j > numArray[i] && index[j - '0'] > i) {
               char tmp = numArray[i];
               numArray[i] = j;
               numArray[index[j - '0']] = tmp;
               return Integer.parseInt(new String(numArray));
            }
         }
      }

      return num;
   }
}

package com.kamarkaka.meta;

/***
 * 一个char[]，里面有'x', 'y'两种字符，找最长的window size 可以满足 <= k个'y'.
 */
public class LongestWindow {
   public int maxSize(char[] array, int k) {
      int yCount = 0;
      int left = 0, right = 0;
      int maxSize = Integer.MIN_VALUE;

      while (right < array.length) {
         while (right < array.length && yCount <= k) {
            if (array[right] == 'y') {
               if (yCount == k) break;
               else {
                  yCount++;
                  right++;
               }
            } else {
               right++;
            }
         }

         if (right - left > maxSize) {
            maxSize = right - left;
            System.out.println("left: " + left + "; right: " + right);
         }

         while (left < right) {
            if (array[left++] == 'y') yCount--;
            if (yCount < k) break;
         }
      }

      return maxSize;
   }

   public static void main(String[] args) {
      LongestWindow solution = new LongestWindow();
      System.out.println(solution.maxSize("xyyyyxxxyyxxxyyyxx".toCharArray(), 4));
   }
}

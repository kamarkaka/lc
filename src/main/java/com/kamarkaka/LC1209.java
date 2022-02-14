package main.java.com.kamarkaka;

import java.util.Stack;

/***
 * 1209. Remove All Adjacent Duplicates in String II
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 *
 * Example 1:
 *   Input: s = "abcd", k = 2
 *   Output: "abcd"
 *   Explanation: There's nothing to delete.
 *
 * Example 2:
 *   Input: s = "deeedbbcccbdaa", k = 3
 *   Output: "aa"
 *   Explanation:
 *     First delete "eee" and "ccc", get "ddbbbdaa"
 *     Then delete "bbb", get "dddaa"
 *     Finally delete "ddd", get "aa"
 * Example 3:
 *   Input: s = "pbbcggttciiippooaais", k = 2
 *   Output: "ps"
 *
 * Constraints:
 *   1 <= s.length <= 10^5
 *   2 <= k <= 10^4
 *   s only contains lower case English letters.
 */
public class LC1209 {
   public String removeDuplicates(String s, int k) {
      Stack<int[]> stack = new Stack<>();

      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (!stack.isEmpty()) {
            int[] prev = stack.peek();
            if (prev[0] != c - 'a') {
               stack.push(new int[] { c - 'a', 1});
            } else if (prev[1] == k - 1) {
               stack.pop();
            } else {
               prev[1]++;
            }
         } else {
            stack.push(new int[] {c - 'a', 1});
         }
      }

      if (stack.isEmpty()) return "";

      int[] prev = stack.peek();
      if (prev[1] == k) stack.pop();

      StringBuilder sb = new StringBuilder();
      while (!stack.isEmpty()) {
         int[] prev1 = stack.pop();
         char c = (char) ('a' + prev1[0]);
         int count = prev1[1];
         for (int i = 0; i < count; i++) {
            sb.insert(0, c);
         }
      }
      return sb.toString();
   }

   public String removeDuplicates2(String s, int k) {
      char[] chars = s.toCharArray();
      int[] count = new int[s.length()];
      int p = 0;

      for (int i = 0; i < s.length(); i++) {
         chars[p] = chars[i];
         if (p > 0 && chars[p - 1] == chars[p]) {
            count[p] = count[p - 1] + 1;
         } else {
            count[p] = 1;
         }

         //remove k duplicate characters
         if(count[p] == k) p -= k;
         p++;
      }

      return new String(chars, 0, p);
   }

   public static void run() {
      LC1209 solution = new LC1209();
      System.out.println(solution.removeDuplicates2("deeedbbcccbdaa", 3));
   }
}

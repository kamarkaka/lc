package com.kamarkaka;

import java.util.Stack;

/***
 * 946. Validate Stack Sequences
 * Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result of a sequence of push and pop operations on an initially empty stack, or false otherwise.
 *
 * Example 1:
 *   Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 *   Output: true
 *   Explanation: We might do the following sequence:
 *   push(1), push(2), push(3), push(4),
 *   pop() -> 4,
 *   push(5),
 *   pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * Example 2:
 *   Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 *   Output: false
 *   Explanation: 1 cannot be popped before 2.
 *
 * Constraints:
 *   1 <= pushed.length <= 1000
 *   0 <= pushed[i] <= 1000
 *   All the elements of pushed are unique.
 *   popped.length == pushed.length
 *   popped is a permutation of pushed.
 */
public class LC0946 {
   public boolean validateStackSequences(int[] pushed, int[] popped) {
      Stack<Integer> stack = new Stack<>();

      int pushPointer = 0, popPointer = 0;

      while (popPointer < popped.length && pushPointer < pushed.length) {
         int val = popped[popPointer];
         while (pushed[pushPointer] != val) {
            stack.push(pushed[pushPointer]);
            pushPointer++;

            if (pushPointer == pushed.length) return false;
         }

         stack.push(pushed[pushPointer++]);

         while (popPointer < popped.length && popped[popPointer] == stack.peek()) {
            stack.pop();
            popPointer++;

            if (stack.isEmpty()) break;
         }
      }

      return stack.isEmpty();
   }

   public boolean validateStackSequences2(int[] pushed, int[] popped) {
      int i = 0, j = 0;
      for (int push : pushed) {
         pushed[i++] = push;
         while (i > 0 && j < popped.length && pushed[i - 1] == popped[j]) {
            i--;
            j++;
         }
      }
      return i == 0;
   }

   public static void run() {
      LC0946 solution = new LC0946();
      System.out.println(solution.validateStackSequences(new int[] {1,2,3,4,5}, new int[] {4,5,3,2,1}));
      System.out.println(solution.validateStackSequences(new int[] {1,0}, new int[] {1,0}));
      System.out.println(solution.validateStackSequences(new int[] {0}, new int[] {0}));
      System.out.println(solution.validateStackSequences(new int[] {1,2,3,4,5}, new int[] {4,3,5,1,2}));
   }
}

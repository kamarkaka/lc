package com.kamarkaka.leetcode;

import com.kamarkaka.common.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 735. Asteroid Collision
 * We are given an array asteroids of integers representing asteroids in a row. The indices of the asteriod in the array
 * represent their relative position in space.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning
 * right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If
 * both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * Example 1:
 *   Input: asteroids = [5,10,-5]
 *   Output: [5,10]
 *   Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 * Example 2:
 *   Input: asteroids = [8,-8]
 *   Output: []
 *   Explanation: The 8 and -8 collide exploding each other.
 * Example 3:
 *   Input: asteroids = [10,2,-5]
 *   Output: [10]
 *   Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 * Constraints:
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */
public class LC0735 {
   public int[] asteroidCollision(int[] asteroids) {
      Stack<Integer> stack = new Stack<>();
      int p = 0;

      while (p < asteroids.length) {
         int a = asteroids[p++];

         if (stack.isEmpty()) {
            stack.push(a);
            continue;
         }

         while (!stack.isEmpty()) {
            int b = stack.peek();
            if ((b < 0 && a < 0) || (b > 0 && a > 0) || (b < 0 && a > 0)) {
               stack.push(a);
               break;
            } else if (Math.abs(b) > Math.abs(a)) {
               break;
            } else if (Math.abs(b) == Math.abs(a)) {
               a = 0;
               stack.pop();
               break;
            } else {
               stack.pop();
            }
         }

         if (stack.isEmpty() && a != 0) {
            stack.push(a);
         }
      }

      int[] result = new int[stack.size()];
      for (int i = stack.size() - 1; i >= 0; i--) {
         result[i] = stack.pop();
      }
      return result;
   }

   public static void run() {
      LC0735 solution = new LC0735();
      Utilities.print(solution.asteroidCollision(new int[] {-2,-2,1,-2}));
   }
}

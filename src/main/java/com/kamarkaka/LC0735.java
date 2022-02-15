package com.kamarkaka;

import com.kamarkaka.common.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 735. Asteroid Collision
 * We are given an array asteroids of integers representing asteroids in a row.
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 *    Input: asteroids = [5,10,-5]
 *    Output: [5,10]
 *    Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * Example 2:
 *    Input: asteroids = [8,-8]
 *    Output: []
 *    Explanation: The 8 and -8 collide exploding each other.
 *
 * Example 3:
 *    Input: asteroids = [10,2,-5]
 *    Output: [10]
 *    Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 * Constraints:
 *    2 <= asteroids.length <= 10^4
 *    -1000 <= asteroids[i] <= 1000
 *    asteroids[i] != 0
 */
public class LC0735 {
   public int[] asteroidCollision(int[] asteroids) {
      List<Integer> list = new ArrayList<>();
      Stack<Integer> stack = new Stack<>();

      int idx = 0;
      while (idx < asteroids.length ) {
         if (asteroids[idx] < 0) {
            if (stack.isEmpty()) {
               list.add(asteroids[idx]);
               idx++;
            } else {
               while (!stack.isEmpty()) {
                  if (asteroids[idx] + stack.peek() < 0) {
                     stack.pop();
                  } else if (asteroids[idx] + stack.peek() == 0) {
                     stack.pop();
                     idx++;
                     break;
                  } else {
                     idx++;
                     break;
                  }
               }
            }
         } else {
            stack.push(asteroids[idx]);
            idx++;
         }
      }

      List<Integer> list2 = new ArrayList<>();
      while (!stack.isEmpty()) {
         list2.add(0, stack.pop());
      }
      list.addAll(list2);

      int[] res = new int[list.size()];
      for (int i = 0; i < list.size(); i++) {
         res[i] = list.get(i);
      }
      return res;
   }

   public static void run() {
      LC0735 solution = new LC0735();
      Utilities.print(solution.asteroidCollision(new int[] {-2,-2,1,-2}));
   }
}

package com.kamarkaka;

import java.util.Random;

/***
 * 470. Implement Rand10() Using Rand7()
 * Given the API rand7() that generates a uniform random integer in the range [1, 7], write a function rand10() that generates a uniform random integer in the range [1, 10]. You can only call the API rand7(), and you shouldn't call any other API. Please do not use a language's built-in random API.
 * Each test case will have one internal argument n, the number of times that your implemented function rand10() will be called while testing. Note that this is not an argument passed to rand10().
 *
 * Example 1:
 *    Input: n = 1
 *    Output: [2]
 *
 * Example 2:
 *    Input: n = 2
 *    Output: [2,8]
 *
 * Example 3:
 *    Input: n = 3
 *    Output: [3,8,10]
 *
 * Constraints:
 *    1 <= n <= 10^5
 *
 * Follow up:
 *    What is the expected value for the number of calls to rand7() function?
 *    Could you minimize the number of calls to rand7()?
 */
public class LC0470 {
   public int rand10() {
      int row, col, idx;
      do {
         row = rand7();
         col = rand7();
         idx = (row - 1) * 7 + col;
      } while (idx > 40);
      return 1 + (idx - 1) % 10;
   }

   public int rand7() {
      Random rand = new Random();
      return 1 + rand.nextInt(7);
   }
}

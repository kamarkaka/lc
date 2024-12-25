package com.kamarkaka.leetcode;

/***
 * 338. Counting Bits
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of
 * 1's in the binary representation of i.
 * Example 1:
 *   Input: n = 2
 *   Output: [0,1,1]
 *   Explanation:
 *   0 --> 0
 *   1 --> 1
 *   2 --> 10
 * Example 2:
 *   Input: n = 5
 *   Output: [0,1,1,2,1,2]
 *   Explanation:
 *   0 --> 0
 *   1 --> 1
 *   2 --> 10
 *   3 --> 11
 *   4 --> 100
 *   5 --> 101
 * Constraints:
 *   0 <= n <= 10^5
 * Follow up:
 *   It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and
 *   possibly in a single pass?
 *   Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 */
public class LC0338 {
    /*
        T.C = O(n), S.C = O(1)
        take base case as 0 -> count:0, 1 -> count:1
        now if num is even -> count is same as n/2
        else if num is odd -> count is n/2 +1
    */
    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        if(n == 0)
            return ans;
        ans[0] = 0;
        if(n >= 1)
            ans[1] = 1;
        for(int i = 2; i <= n; i++) {
            if(i % 2 == 0)
                ans[i] = ans[i/2]; //let's say 4 -> 2(10 -> 01)
            else
                ans[i] = ans[i/2] + 1; //let's say 3: (3,1):(11, 01)
        }
        return ans;
    }
}

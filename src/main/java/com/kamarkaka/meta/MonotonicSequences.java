package com.kamarkaka.meta;

/***
 * Given an array of integers, determine how many monotonic sequences the array is built of.
 * Examples :
 *   One monotonic sequence :
 *   1 3 5 10 ( ascending monotonic sequence)
 *   7 4 2 2 2 1 (descending monotonic sequence)
 *   Two monotonic sequences :
 *   5 3 1 5 6 7
 *   5 5 5 8 19 14 4 1
 */
public class MonotonicSequences {
    public int monotonic(int[] nums) {
        boolean asc = false, desc = false;
        int count = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                asc = true;
                if (desc) {
                    desc = false;
                    count++;
                }
            } else if (nums[i] > nums[i + 1]) {
                desc = true;
                if (asc) {
                    asc = false;
                    count++;
                }
            }
        }

        if (asc || desc) {
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        MonotonicSequences solution = new MonotonicSequences();
        System.out.println(solution.monotonic(new int[]{1,3,5,10}));
        System.out.println(solution.monotonic(new int[]{7,4,2,2,2,1}));
        System.out.println(solution.monotonic(new int[]{5,3,1,5,6,7}));
        System.out.println(solution.monotonic(new int[]{5,5,5,8,19,14,4,1}));
    }
}

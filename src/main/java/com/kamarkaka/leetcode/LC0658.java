package com.kamarkaka.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/***
 * 658. Find K Closest Elements
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result
 * should also be sorted in ascending order.
 * An integer a is closer to x than an integer b if:
 *   |a - x| < |b - x|, or
 *   |a - x| == |b - x| and a < b
 * Example 1:
 *   Input: arr = [1,2,3,4,5], k = 4, x = 3
 *   Output: [1,2,3,4]
 * Example 2:
 *   Input: arr = [1,1,2,3,4,5], k = 4, x = -1
 *   Output: [1,1,2,3]
 * Constraints:
 *   1 <= k <= arr.length
 *   1 <= arr.length <= 10^4
 *   arr is sorted in ascending order.
 *   -10^4 <= arr[i], x <= 10^4
 */
public class LC0658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left=0;
        int right=arr.length-k;
        while(left<right)
        {
            int mid=left+(right-left)/2;
            if(x-arr[mid]<=arr[mid+k]-x)
            {
                right=mid;

            }
            else
            {
                left=mid+1;
            }
        }
        List<Integer> result=new ArrayList<>();
        for(int i=left;i<left+k;i++)
        {
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        LC0658 solution = new LC0658();
        System.out.println(solution.findClosestElements(new int[]{1,5,10}, 1, 1));
        System.out.println(solution.findClosestElements(new int[]{0,0,0,1,3,5,6,7,8,8}, 2, 2));
    }
}

package com.kamarkaka.leetcode;

import com.kamarkaka.common.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 1424. Diagonal Traverse II
 * Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.
 * Example 1:
 *   Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 *   Output: [1,4,2,7,5,3,8,6,9]
 * Example 2:
 *   Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 *   Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 * Constraints:
 *   1 <= nums.length <= 10^5
 *   1 <= nums[i].length <= 10^5
 *   1 <= sum(nums[i].length) <= 10^5
 *   1 <= nums[i][j] <= 10^5
 */
public class LC1424 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int count = 0;

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            List<Integer> row = nums.get(i);

            for (int j = 0; j < row.size(); j++) {
                int idx = i + j;

                if (lists.size() < idx + 1) {
                    lists.add(new ArrayList<>());
                }
                lists.get(idx).add(row.get(j));

                count ++;
            }
        }

        int[] res = new int[count];
        int idx = 0;
        for (List<Integer> list : lists) {
            for (int i = list.size() - 1; i >= 0; i--) {
                res[idx++] = list.get(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LC1424 solution = new LC1424();

        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(14,12,19,16,9));
        nums.add(Arrays.asList(13,14,15,8, 11));
        nums.add(Arrays.asList(11,13,1));

        Utilities.print(solution.findDiagonalOrder(nums));
    }
}

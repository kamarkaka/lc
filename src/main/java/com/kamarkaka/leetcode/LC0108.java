package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 108. Convert Sorted Array to Binary Search Tree
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary
 * search tree.
 * Example 1:
 *   Input: nums = [-10,-3,0,5,9]
 *   Output: [0,-3,9,-10,null,5]
 *   Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 * Example 2:
 *   Input: nums = [1,3]
 *   Output: [3,1]
 *   Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 * Constraints:
 *   1 <= nums.length <= 10^4
 *   -104 <= nums[i] <= 10^4
 *   nums is sorted in a strictly increasing order.
 */
public class LC0108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return helper(0, nums.length, nums);
    }

    public TreeNode helper(int start, int length, int[] nums) {
        if (length == 0) return null;

        int index = start + length / 2;
        TreeNode node = new TreeNode(nums[index]);

        node.left = helper(start, length / 2, nums);
        node.right = helper(start + length / 2 + 1, length - length / 2 - 1, nums);
        return node;
    }
}

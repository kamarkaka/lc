package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 437. Path Sum III
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values
 * along the path equals targetSum.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from
 * parent nodes to child nodes).
 * Example 1:
 *   Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 *   Output: 3
 *   Explanation: The paths that sum to 8 are shown.
 * Example 2:
 *   Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 *   Output: 3
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 1000].
 *   -10^9 <= Node.val <= 10^9
 *   -1000 <= targetSum <= 1000
 */
public class LC0437 {
    public int pathSum(TreeNode root, int targetSum) {
        return helper(root, (long) targetSum, false);
    }

    public int helper(TreeNode root, long sum, boolean mustIncludeRoot) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            if ((long) root.val == sum) return 1;
            else return 0;
        }

        int count = 0;
        if (root.val == sum) count++;

        if (!mustIncludeRoot) {
            return helper(root.left, sum, false)
                    + helper(root.right, sum, false)
                    + helper(root.left, sum - root.val, true)
                    + helper(root.right, sum - root.val, true)
                    + count;
        } else {
            return helper(root.left, sum - root.val, true)
                    + helper(root.right, sum - root.val, true)
                    + count;
        }
    }
}

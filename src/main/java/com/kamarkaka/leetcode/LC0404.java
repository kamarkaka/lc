package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 404. Sum of Left Leaves
 * Given the root of a binary tree, return the sum of all left leaves.
 * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
 * Example 1:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: 24
 *   Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * Example 2:
 *   Input: root = [1]
 *   Output: 0
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 1000].
 *   -1000 <= Node.val <= 1000
 */
public class LC0404 {
    public int sumOfLeftLeaves(TreeNode root) {
        int result = 0;
        if (root == null) return 0;
        else if (root.left != null && root.left.left == null && root.left.right == null) {
            result += root.left.val;
        }

        if (root.left != null) {
            result += sumOfLeftLeaves(root.left);
        }

        if (root.right != null) {
            result += sumOfLeftLeaves(root.right);
        }

        return result;
    }
}

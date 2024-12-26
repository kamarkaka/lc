package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 110. Balanced Binary Tree
 * Given a binary tree, determine if it is height-balanced.
 * Example 1:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: true
 * Example 2:
 *   Input: root = [1,2,2,3,3,null,null,4,4]
 *   Output: false
 * Example 3:
 *   Input: root = []
 *   Output: true
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 5000].
 *   -10^4 <= Node.val <= 10^4
 */
public class LC0110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);

        if (Math.abs(leftDepth - rightDepth) > 1) return false;
        else return isBalanced(root.left) && isBalanced(root.right);
    }

    public int getDepth(TreeNode node) {
        if (node == null) return 0;
        else return 1 + Math.max(getDepth(node.left), getDepth(node.right));
    }
}

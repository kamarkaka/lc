/***
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as follows:
 *   The left subtree of a node contains only nodes with keys less than the node's key.
 *   The right subtree of a node contains only nodes with keys greater than the node's key.
 *   Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *   Input: root = [2,1,3]
 *   Output: true
 *
 * Example 2:
 *   Input: root = [5,1,4,null,null,3,6]
 *   Output: false
 *   Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 104].
 *   -2^31 <= Node.val <= 2^31 - 1
 */
package com.kamarkaka;

import com.kamarkaka.common.TreeNode;

public class LC0098 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        if (!isValidBST(root.left)) return false;
        if (!isValidBST(root.right)) return false;
        if (root.left != null) {
            if (findMax(root.left) >= root.val) return false;
        }
        if (root.right != null) {
            if (findMin(root.right) <= root.val) return false;
        }
        return true;
    }

    private int findMin(TreeNode root) {
        int res = root.val;
        while (root != null) {
            res = root.val;
            root = root.left;
        }
        return res;
    }

    private int findMax(TreeNode root) {
        int res = root.val;
        while (root != null) {
            res = root.val;
            root = root.right;
        }
        return res;
    }
}

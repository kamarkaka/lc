package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 669. Trim a Binary Search Tree
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high, trim the tree so that
 * all its elements lies in [low, high]. Trimming the tree should not change the relative structure of the elements that
 * will remain in the tree (i.e., any node's descendant should remain a descendant). It can be proven that there is a
 * unique answer.
 * Return the root of the trimmed binary search tree. Note that the root may change depending on the given bounds.
 * Example 1:
 *   Input: root = [1,0,2], low = 1, high = 2
 *   Output: [1,null,2]
 * Example 2:
 *   Input: root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 *   Output: [3,2,null,1]
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 10^4].
 *   0 <= Node.val <= 10^4
 *   The value of each node in the tree is unique.
 *   root is guaranteed to be a valid binary search tree.
 *   0 <= low <= high <= 10^4
 */
public class LC0669 {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        if (root.val < L) return trimBST(root.right, L, R);
        if (root.val > R) return trimBST(root.left, L, R);

        root.left = trimLeft(root.left, L);
        root.right = trimRight(root.right, R);

        return root;
    }

    private TreeNode trimLeft(TreeNode node, int L) {
        if (node == null) return null;

        if (node.val == L) {
            node.left = null;
            return node;
        } else if (node.val > L) {
            node.left = trimLeft(node.left, L);
        } else {
            return trimLeft(node.right, L);
        }

        return node;
    }

    private TreeNode trimRight(TreeNode node, int R) {
        if (node == null) return null;

        if (node.val == R) {
            node.right = null;
            return node;
        } else if (node.val < R) {
            node.right = trimRight(node.right, R);
        } else {
            return trimRight(node.left, R);
        }

        return node;
    }
}

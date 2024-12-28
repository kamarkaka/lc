package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 783. Minimum Distance Between BST Nodes
 * Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different
 * nodes in the tree.
 * Example 1:
 *   Input: root = [4,2,6,1,3]
 *   Output: 1
 * Example 2:
 *   Input: root = [1,0,48,null,null,12,49]
 *   Output: 1
 * Constraints:
 *   The number of nodes in the tree is in the range [2, 100].
 *   0 <= Node.val <= 10^5
 * Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class LC0783 {
    private int diff = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        if (root == null) return 0;
        traverse(root);
        return diff;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            int newDiff = Math.min(Math.abs(root.val - getMin(root.left)), Math.abs(root.val - getMax(root.left)));
            diff = Math.min(diff, newDiff);
            traverse(root.left);
        }
        if (root.right != null) {
            int newDiff = Math.min(Math.abs(root.val - getMin(root.right)), Math.abs(root.val - getMax(root.right)));
            diff = Math.min(diff, newDiff);
            traverse(root.right);
        }
    }

    private int getMin(TreeNode node) {
        int min = node.val;
        while (node.left != null) {
            node = node.left;
            min = node.val;
        }
        return min;
    }

    private int getMax(TreeNode node) {
        int max = node.val;
        while (node.right != null) {
            node = node.right;
            max = node.val;
        }
        return max;
    }
}

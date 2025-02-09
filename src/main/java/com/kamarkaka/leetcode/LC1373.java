package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 1373. Maximum Sum BST in Binary Tree
 * Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 * Assume a BST is defined as follows:
 *   The left subtree of a node contains only nodes with keys less than the node's key.
 *   The right subtree of a node contains only nodes with keys greater than the node's key.
 *   Both the left and right subtrees must also be binary search trees.
 * Example 1:
 *   Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
 *   Output: 20
 *   Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
 * Example 2:
 *   Input: root = [4,3,null,1,2]
 *   Output: 2
 *   Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
 * Example 3:
 *   Input: root = [-4,-2,-5]
 *   Output: 0
 *   Explanation: All values are negatives. Return an empty BST.
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 4 * 10^4].
 *   -4 * 10^4 <= Node.val <= 4 * 10^4
 */
public class LC1373 {
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        traverse(root);
        return maxSum;
    }

    // {min, max, sum}, initialize min=MAX_VALUE, max=MIN_VALUE
    private int[] traverse(TreeNode root) {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};

        int[] left = traverse(root.left);
        int[] right = traverse(root.right);

        // The BST is the tree:
        if (left != null &&        // the left subtree must be BST
            right != null &&       // the right subtree must be BST
            left[1] < root.val &&  // the root's key must greater than maximum keys of the left subtree
            root.val < right[0]) { // the root's key must lower than minimum keys of the right subtree
            int sum = root.val + left[2] + right[2]; // now it's a BST make `root` as root
            maxSum = Math.max(maxSum, sum);
            int min = Math.min(root.val, left[0]);
            int max = Math.max(root.val, right[1]);
            return new int[]{min, max, sum};
        }
        return null;
    }
}

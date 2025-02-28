package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 1372. Longest ZigZag Path in a Binary Tree
 * You are given the root of a binary tree.
 * A ZigZag path for a binary tree is defined as follow:
 *   Choose any node in the binary tree and a direction (right or left).
 *   If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 *   Change the direction from right to left or from left to right.
 *   Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 * Return the longest ZigZag path contained in that tree.
 * Example 1:
 *   Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
 *   Output: 3
 *   Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 * Example 2:
 *   Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 *   Output: 4
 *   Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
 * Example 3:
 *   Input: root = [1]
 *   Output: 0
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 5 * 10^4].
 *   1 <= Node.val <= 100
 */
public class LC1372 {
    int max = 0;

    public void dfs(TreeNode root, boolean isLeft, int length) {
        if (root == null) {
            return;
        }

        max = Math.max(max, length);

        if (isLeft) {
            dfs(root.right, false, length + 1);
            dfs(root.left, true, 1);
        } else {
            dfs(root.left, true, length + 1);
            dfs(root.right, false, 1);
        }
    }

    public int longestZigZag(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root.right, false, 1);
        dfs(root.left, true, 1);
        return max;
    }
}

package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 111. Minimum Depth of Binary Tree
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 * Example 1:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: 2
 * Example 2:
 *   Input: root = [2,null,3,null,4,null,5,null,6]
 *   Output: 5
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 10^5].
 *   -1000 <= Node.val <= 1000
 */
public class LC0111 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 1;
        else if (root.left == null || root.right == null) {
            if (root.left == null) {
                return minDepth(root.right) + 1;
            } else {
                return minDepth(root.left) + 1;
            }
        }
        else return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}

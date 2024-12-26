package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 222. Count Complete Tree Nodes
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and
 * all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last
 * level h.
 * Design an algorithm that runs in less than O(n) time complexity.
 * Example 1:
 *   Input: root = [1,2,3,4,5,6]
 *   Output: 6
 * Example 2:
 *   Input: root = []
 *   Output: 0
 * Example 3:
 *   Input: root = [1]
 *   Output: 1
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 5 * 10^4].
 *   0 <= Node.val <= 5 * 10^4
 *   The tree is guaranteed to be complete.
 */
public class LC0222 {
    private int getHeight(TreeNode node) {
        return node == null ? -1 : 1 + getHeight(node.left);
    }

    public int countNodes(TreeNode root) {
        int h = getHeight(root);
        if (h < 0) return 0;
        if (getHeight(root.right) == h - 1) {
            return (1 << h) + countNodes(root.right);
        } else {
            return (1 << h - 1) + countNodes(root.left);
        }
    }
}

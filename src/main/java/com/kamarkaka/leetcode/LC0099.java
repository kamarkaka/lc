package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 99. Recover Binary Search Tree
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped
 * by mistake. Recover the tree without changing its structure.
 * Example 1:
 *   Input: root = [1,3,null,null,2]
 *   Output: [3,1,null,null,2]
 *   Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 *   Input: root = [3,1,4,null,null,2]
 *   Output: [2,1,4,null,null,3]
 *   Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 * Constraints:
 *   The number of nodes in the tree is in the range [2, 1000].
 *   -2^31 <= Node.val <= 2^31 - 1
 * Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
 */
public class LC0099 {
    TreeNode last;
    TreeNode n1, n2;

    public void recoverTree(TreeNode root) {
        dfs(root);
        int temp = n1.val;
        n1.val = n2.val;
        n2.val = temp;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;

        dfs(root.left);
        if (last != null) {
            if (root.val <= last.val) {
                if (n1 == null) {
                    n1 = last;
                    n2 = root;
                } else {
                    n2 = root;
                }
            }
        }
        last = root;
        dfs(root.right);
    }
}

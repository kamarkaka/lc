package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 965. Univalued Binary Tree
 * A binary tree is uni-valued if every node in the tree has the same value.
 * Given the root of a binary tree, return true if the given tree is uni-valued, or false otherwise.
 * Example 1:
 *   Input: root = [1,1,1,1,1,null,1]
 *   Output: true
 * Example 2:
 *   Input: root = [2,2,2,5,2]
 *   Output: false
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 100].
 *   0 <= Node.val < 100
 */
public class LC0965 {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        int val = root.val;
        return checkValue(val, root);
    }

    private boolean checkValue(int val, TreeNode root) {
        if (root == null) return true;
        if (root.val != val) return false;
        return checkValue(val, root.left) && checkValue(val, root.right);
    }
}

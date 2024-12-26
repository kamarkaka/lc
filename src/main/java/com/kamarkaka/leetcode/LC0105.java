package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
 * is the inorder traversal of the same tree, construct and return the binary tree.
 * Example 1:
 *   Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 *   Output: [3,9,20,null,null,15,7]
 * Example 2:
 *   Input: preorder = [-1], inorder = [-1]
 *   Output: [-1]
 * Constraints:
 *   1 <= preorder.length <= 3000
 *   inorder.length == preorder.length
 *   -3000 <= preorder[i], inorder[i] <= 3000
 *   preorder and inorder consist of unique values.
 *   Each value of inorder also appears in preorder.
 *   preorder is guaranteed to be the preorder traversal of the tree.
 *   inorder is guaranteed to be the inorder traversal of the tree.
 */
public class LC0105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int lp, int rp, int li, int ri) {
        if (lp > rp) return null;

        TreeNode root = new TreeNode(preorder[lp]);
        for (int k = li; k <= ri; k++) {
            if (preorder[lp] == inorder[k]) {
                root.left = helper(preorder, inorder, lp + 1, lp + (k - li), li, k - 1);
                root.right = helper(preorder, inorder, lp + (k - li) + 1, rp, k + 1, ri);
            }
        }
        return root;
    }
}

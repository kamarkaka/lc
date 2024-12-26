package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder
 * is the postorder traversal of the same tree, construct and return the binary tree.
 * Example 1:
 *   Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 *   Output: [3,9,20,null,null,15,7]
 * Example 2:
 *   Input: inorder = [-1], postorder = [-1]
 *   Output: [-1]
 * Constraints:
 *   1 <= inorder.length <= 3000
 *   postorder.length == inorder.length
 *   -3000 <= inorder[i], postorder[i] <= 3000
 *   inorder and postorder consist of unique values.
 *   Each value of postorder also appears in inorder.
 *   inorder is guaranteed to be the inorder traversal of the tree.
 *   postorder is guaranteed to be the postorder traversal of the tree.
 */
public class LC0106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(postorder.length - 1, 0, inorder.length - 1, postorder, inorder);
    }

    private TreeNode helper(int preEnd, int inStart, int inEnd, int[] postorder, int[] inorder) {
        if (inStart > inEnd || preEnd < 0 || inEnd < 0) return null;

        int idx;
        TreeNode root = new TreeNode(postorder[preEnd]);
        //System.out.printf("%d %d %d \t", preEnd, inStart, inEnd);
        for (idx = inEnd; idx > inStart; idx--) {
            if (inorder[idx] == postorder[preEnd]) break;
        }
        //System.out.println("IDX:" + idx + " "+postorder[preEnd] + "\n");
        root.left = helper(preEnd - (inEnd - idx + 1), inStart, idx - 1, postorder, inorder);
        root.right = helper(preEnd - 1, idx + 1, inEnd, postorder, inorder);
        return root;
    }
}

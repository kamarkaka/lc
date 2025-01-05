package com.kamarkaka.meta;

import com.kamarkaka.common.TreeNode;

/***
 * Find median in a BST
 */
public class MedianBST {
    int index = 0;
    int nodeCount = 0;
    public TreeNode findMedian(TreeNode root) {
        if (root == null) return null;

        this.nodeCount = countNodes(root);
        return helper(root);
    }

    private TreeNode helper(TreeNode node) {
        if (node == null) return null;

        TreeNode candidate = helper(node.left);
        if (candidate != null) return candidate;

        if (this.index == this.nodeCount / 2) return node;

        this.index++;
        return helper(node.right);
    }

    private int countNodes(TreeNode node) {
        if (node == null) return 0;
        return countNodes(node.left) + countNodes(node.right) + 1;
    }
}


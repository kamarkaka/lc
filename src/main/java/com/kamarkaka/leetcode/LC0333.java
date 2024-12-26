package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 333. Largest BST Subtree
 * Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the
 * largest means subtree has the largest number of nodes.
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
 *   The left subtree values are less than the value of their parent (root) node's value.
 *   The right subtree values are greater than the value of their parent (root) node's value.
 * Note: A subtree must include all of its descendants.
 * Example 1:
 *   Input: root = [10,5,15,1,8,null,7]
 *   Output: 3
 *   Explanation: The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size,
 *   which is 3.
 * Example 2:
 *   Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 *   Output: 2
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 10^4].
 *   -10^4 <= Node.val <= 10^4
 * Follow up: Can you figure out ways to solve it with O(n) time complexity?
 */
public class LC0333 {
    public int largestBSTSubtree(TreeNode root) {
        Result result = helper(root);
        return result.count;
    }

    private Result helper(TreeNode root) {
        if (root == null) return new Result(true, 0);
        if (root.left == null && root.right == null) return new Result(true, 1);

        Result leftResult = helper(root.left);
        Result rightResult = helper(root.right);

        boolean leftOK = false, rightOK = false;
        if (root.left == null) leftOK = true;
        else if (root.val > findMax(root.left)) leftOK = true;

        if (root.right == null) rightOK = true;
        else if (root.val < findMin(root.right)) rightOK = true;

        if (leftOK && rightOK && leftResult.isBST && rightResult.isBST) return new Result(true, leftResult.count + 1 + rightResult.count);
        else return new Result(false, Math.max(leftResult.count, rightResult.count));
    }

    private int findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    private int findMax(TreeNode node) {
        if (node == null) return 0;
        while (node.right != null) {
            node = node.right;
        }
        return node.val;
    }

    private class Result {
        boolean isBST;
        int count;

        public Result(boolean isBST, int count) {
            this.isBST = isBST;
            this.count = count;
        }
    }
}

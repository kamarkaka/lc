package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 * 94. Binary Tree Inorder Traversal
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * Example 1:
 *   Input: root = [1,null,2,3]
 *   Output: [1,3,2]
 * Example 2:
 *   Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *   Output: [4,2,6,5,7,1,3,9,8]
 * Example 3:
 *   Input: root = []
 *   Output: []
 * Example 4:
 *   Input: root = [1]
 *   Output: [1]
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 100].
 *   -100 <= Node.val <= 100
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class LC0094 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        helper(node.left, result);
        result.add(node.val);
        helper(node.right, result);
    }
}
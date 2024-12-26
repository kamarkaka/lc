package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 145. Binary Tree Postorder Traversal
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * Example 1:
 *   Input: root = [1,null,2,3]
 *   Output: [3,2,1]
 * Example 2:
 *   Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *   Output: [4,6,7,5,2,9,8,3,1]
 * Example 3:
 *   Input: root = []
 *   Output: []
 * Example 4:
 *   Input: root = [1]
 *   Output: [1]
 * Constraints:
 *   The number of the nodes in the tree is in the range [0, 100].
 *   -100 <= Node.val <= 100
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class LC0145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
            result.add(0, node.val);
        }

        return result;
    }
}

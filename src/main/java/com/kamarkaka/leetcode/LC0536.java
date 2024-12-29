package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.Stack;

/***
 * 536. Construct Binary Tree from String
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
 * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same
 * structure.
 * You always start to construct the left child node of the parent first if it exists.
 * Example 1:
 *   Input: s = "4(2(3)(1))(6(5))"
 *   Output: [4,2,6,3,1,5]
 * Example 2:
 *   Input: s = "4(2(3)(1))(6(5)(7))"
 *   Output: [4,2,6,3,1,5,7]
 * Example 3:
 *   Input: s = "-4(2(3)(1))(6(5)(7))"
 *   Output: [-4,2,6,3,1,5,7]
 * Constraints:
 *   0 <= s.length <= 3 * 10^4
 *   s consists of digits, '(', ')', and '-' only.
 */
public class LC0536 {
    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) return null;

        StringBuilder sb = new StringBuilder();
        boolean rootSet = false;
        TreeNode root = new TreeNode();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            if (c != '(' && c != ')') {
                sb.append(c);
                continue;
            }

            if (c == '(') {
                if (!rootSet) {
                    rootSet = true;
                    root.val = Integer.parseInt(sb.toString());
                    sb = new StringBuilder();
                    continue;
                }

                if (!sb.isEmpty()) {
                    TreeNode node = stack.peek();
                    TreeNode child = new TreeNode(Integer.parseInt(sb.toString()));
                    if (node.left == null ) {
                        node.left = child;
                    } else {
                        node.right = child;
                    }
                    stack.push(child);
                    sb = new StringBuilder();
                }
            } else {
                TreeNode node = stack.pop();

                if (!sb.isEmpty()) {
                    if (node.left == null) {
                        node.left = new TreeNode(Integer.parseInt(sb.toString()));
                    } else {
                        node.right = new TreeNode(Integer.parseInt(sb.toString()));
                    }
                    stack.push(node);
                    sb = new StringBuilder();
                }
            }
        }

        if (!sb.isEmpty()) {
            root.val = Integer.parseInt(sb.toString());
        }

        return root;
    }

    public static void main(String[] args) {
        LC0536 solution = new LC0536();
        TreeNode root = solution.str2tree("4(2(3)(1))(6(5))");
    }
}

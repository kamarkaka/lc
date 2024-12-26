package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 501. Find Mode in Binary Search Tree
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently
 * occurred element) in it.
 * If the tree has more than one mode, return them in any order.
 * Assume a BST is defined as follows:
 *   The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 *   The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 *   Both the left and right subtrees must also be binary search trees.
 * Example 1:
 *   Input: root = [1,null,2,2]
 *   Output: [2]
 * Example 2:
 *   Input: root = [0]
 *   Output: [0]
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 104^].
 *   -10^5 <= Node.val <= 10^5
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to
 * recursion does not count).
 */
public class LC0501 {
    private Map<Integer, Integer> counter = new HashMap<>();
    private TreeNode prevNode = null;
    private int max = 0;
    private int count = 1;

    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        if (root.left == null && root.right == null) {
            int[] result = new int[1];
            result[0] = root.val;
            return result;
        }

        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;

        dfs(node.left, list);

        if (prevNode == null) {
            prevNode = node;
            list.add(node.val);
        } else if (prevNode.val == node.val){
            count++;
        } else {
            count = 1;
            prevNode = node;
        }

        if (count > max) {
            max = count;
            list.clear();
            list.add(node.val);
        } else if (count == max) {
            list.add(node.val);
        }

        dfs(node.right, list);
    }
}

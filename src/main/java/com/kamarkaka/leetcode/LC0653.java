package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 * 653. Two Sum IV - Input is a BST
 * Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that
 * their sum is equal to k, or false otherwise.
 * Example 1:
 *   Input: root = [5,3,6,2,4,null,7], k = 9
 *   Output: true
 * Example 2:
 *   Input: root = [5,3,6,2,4,null,7], k = 28
 *   Output: false
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 10^4].
 *   -10^4 <= Node.val <= 10^4
 *   root is guaranteed to be a valid binary search tree.
 *   -10^5 <= k <= 10^5
 */
public class LC0653 {
    private List<Integer> list = new ArrayList<>();

    public boolean findTarget(TreeNode root, int k) {
        DFS(root);

        int p1 = 0;
        int p2 = list.size() - 1;

        while (p1 < p2) {
            int sum = list.get(p1) + list.get(p2);
            if (sum == k) return true;
            else if (sum < k) p1++;
            else p2--;
        }

        return false;
    }

    private void DFS(TreeNode node) {
        if (node == null) return;

        DFS(node.left);
        list.add(node.val);
        DFS(node.right);
    }
}

package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 * 1382. Balance a Binary Search Tree
 * Given the root of a binary search tree, return a balanced binary search tree with the same node values. If there is
 * more than one answer, return any of them.
 * A binary search tree is balanced if the depth of the two subtrees of every node never differs by more than 1.
 * Example 1:
 *   Input: root = [1,null,2,null,3,null,4,null,null]
 *   Output: [2,1,3,null,null,null,4]
 *   Explanation: This is not the only correct answer, [3,1,4,null,2] is also correct.
 * Example 2:
 *   Input: root = [2,1,3]
 *   Output: [2,1,3]
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 10^4].
 *   1 <= Node.val <= 10^5
 */
public class LC1382 {
    public TreeNode balanceBST(TreeNode root) {
        // Create a list to store the inorder traversal of the BST
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);

        // Construct and return the balanced BST
        return createBalancedBST(inorder, 0, inorder.size() - 1);
    }

    private void inorderTraversal(TreeNode root, List<Integer> inorder) {
        // Perform an inorder traversal to store the elements in sorted order
        if (root == null) return;
        inorderTraversal(root.left, inorder);
        inorder.add(root.val);
        inorderTraversal(root.right, inorder);
    }

    private TreeNode createBalancedBST(
            List<Integer> inorder,
            int start,
            int end
    ) {
        // Base case: if the start index is greater than the end index, return null
        if (start > end) return null;

        // Find the middle element of the current range
        int mid = start + (end - start) / 2;

        // Recursively construct the left and right subtrees
        TreeNode leftSubtree = createBalancedBST(inorder, start, mid - 1);
        TreeNode rightSubtree = createBalancedBST(inorder, mid + 1, end);

        // Create a new node with the middle element and attach the subtrees
        TreeNode node = new TreeNode(
                inorder.get(mid),
                leftSubtree,
                rightSubtree
        );
        return node;
    }
}

package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 655. Print Binary Tree
 * Given the root of a binary tree, construct a 0-indexed m x n string matrix res that represents a formatted layout of
 * the tree. The formatted layout matrix should be constructed using the following rules:
 *   The height of the tree is height and the number of rows m should be equal to height + 1.
 *   The number of columns n should be equal to 2height+1 - 1.
 *   Place the root node in the middle of the top row (more formally, at location res[0][(n-1)/2]).
 *   For each node that has been placed in the matrix at position res[r][c], place its left child at
 *   res[r+1][c-2height-r-1] and its right child at res[r+1][c+2height-r-1].
 *   Continue this process until all the nodes in the tree have been placed.
 *   Any empty cells should contain the empty string "".
 * Return the constructed matrix res.
 * Example 1:
 *   Input: root = [1,2]
 *   Output:
 *   [["","1",""],
 *    ["2","",""]]
 * Example 2:
 *   Input: root = [1,2,3,null,4]
 *   Output:
 *   [["","","","1","","",""],
 *    ["","2","","","","3",""],
 *    ["","","4","","","",""]]
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 2^10].
 *   -99 <= Node.val <= 99
 *   The depth of the tree will be in the range [1, 10].
 */
public class LC0655 {
    public List<List<String>> printTree(TreeNode root) {
        int m = height(root);
        int n = (int)Math.pow(2, m) - 1; // Sum of a GP: 2^0 + 2^1 + 2^2 + ... + 2^(m-1)

        // Initialize empty matrix
        String[][] matrix = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = "";
            }
        }
        fillMatrix(root, matrix, 0, 0, n-1);

        // Convert matrix to list
        List<List<String>> list = new ArrayList<>();
        for (String[] row : matrix) list.add(Arrays.asList(row));
        return list;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private void fillMatrix(TreeNode root, String[][] matrix, int row, int leftBoundary, int rightBoundary) {
        if (root == null) return;
        int col = (leftBoundary + rightBoundary) / 2;
        matrix[row][col] = String.valueOf(root.val);
        fillMatrix(root.left, matrix, row + 1, leftBoundary, col - 1);
        fillMatrix(root.right, matrix, row + 1, col + 1, rightBoundary);
    }
}

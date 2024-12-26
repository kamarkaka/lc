package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * 449. Serialize and Deserialize BST
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or
 * memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another
 * computer environment.
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized
 * to a string, and this string can be deserialized to the original tree structure.
 * The encoded string should be as compact as possible.
 * Example 1:
 *   Input: root = [2,1,3]
 *   Output: [2,1,3]
 * Example 2:
 *   Input: root = []
 *   Output: []
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 10^4].
 *   0 <= Node.val <= 10^4
 *   The input tree is guaranteed to be a binary search tree.
 */
public class LC0449 {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();

        if (root == null) {
            sb.append("null");
        } else {
            List<TreeNode> list = new LinkedList<>();
            list.add(root);

            while (list.size() > 0) {
                List<TreeNode> newList = new LinkedList<>();
                boolean allLeafNodes = true;
                StringBuilder sbLevel = new StringBuilder();
                for (TreeNode node : list) {
                    if (node == null) {
                        sbLevel.append("null,");
                    } else {
                        sbLevel.append(node.val + ",");

                        if (node.left != null || node.right != null) allLeafNodes = false;

                        newList.add(node.left);
                        newList.add(node.right);
                    }
                }

                sb.append(sbLevel);

                if (allLeafNodes) break;
                else list = newList;
            }
        }



        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodeValues = data.split(",");
        TreeNode root;

        if (nodeValues[0].equalsIgnoreCase("null")) root = null;
        else root = new TreeNode(Integer.parseInt(nodeValues[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode currNode = queue.poll();
        boolean isLeftFilled = false;

        for (int i = 1; i < nodeValues.length; i++) {
            String nodeValue = nodeValues[i];
            if (nodeValue.isEmpty()) continue;

            if (nodeValue.equals("null")) {
                if (!isLeftFilled) {
                    currNode.left = null;
                    isLeftFilled = true;
                } else {
                    currNode.right = null;
                    currNode = queue.poll();
                    isLeftFilled = false;
                }
            } else {
                TreeNode node = new TreeNode(Integer.parseInt(nodeValue));
                queue.add(node);

                if (!isLeftFilled) {
                    currNode.left = node;
                    isLeftFilled = true;
                } else {
                    currNode.right = node;
                    currNode = queue.poll();
                    isLeftFilled = false;
                }

            }
        }

        return root;
    }

}

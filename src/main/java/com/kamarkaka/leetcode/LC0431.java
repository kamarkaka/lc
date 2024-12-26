package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 * 431. Encode N-ary Tree to Binary Tree
 * Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary
 * tree. An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a
 * rooted tree in which each node has no more than 2 children. There is no restriction on how your encode/decode
 * algorithm should work. You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary
 * tree can be decoded to the original N-nary tree structure.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by
 * the null value (See following example).
 * For example, you may encode the following 3-ary tree to a binary tree in this way:
 *     1             1
 *    /|\           /\
 *   3 2 4  =>     3  2
 *  /\            /    \
 * 5 6           5      4
 *                \
 *                 6
 * Input: root = [1,null,3,2,4,null,5,6]
 * Note that the above is just an example which might or might not work. You do not necessarily need to follow this
 * format, so please be creative and come up with different approaches yourself.
 * Example 1:
 *   Input: root = [1,null,3,2,4,null,5,6]
 *   Output: [1,null,3,2,4,null,5,6]
 * Example 2:
 *   Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *   Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Example 3:
 *   Input: root = []
 *   Output: []
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 10^4].
 *   0 <= Node.val <= 10^4
 *   The height of the n-ary tree is less than or equal to 1000
 *   Do not use class member/global/static variables to store states. Your encode and decode algorithms should be
 *   stateless.
 */
public class LC0431 {
    class Node {
        public int val;
        public List<Node> children;

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public TreeNode encode(Node root) {
        return encodeHelper(root, true);
    }

    private TreeNode encodeHelper(Node root, boolean dir) {
        if (root == null) return null;

        TreeNode node = new TreeNode(root.val);
        TreeNode pre = node;
        for (Node c : root.children) {
            TreeNode d = encodeHelper(c, !dir);
            if (dir) pre.right = d;
            else pre.left = d;
            pre = d;
        }

        return node;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        return decodeHelper(root, true);
    }

    private Node decodeHelper(TreeNode root, boolean dir) {
        if (root == null) return null;

        Node node = new Node(root.val, new ArrayList<>());
        TreeNode curr = root;
        while (dir && curr.right != null || !dir && curr.left != null) {
            Node c = decodeHelper(dir ? curr.right : curr.left, !dir);
            node.children.add(c);
            curr = dir ? curr.right : curr.left;
        }

        return node;
    }
}

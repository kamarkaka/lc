package com.kamarkaka;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 1305. All Elements in Two Binary Search Trees
 * Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.
 *
 * Example 1:
 *    Input: root1 = [2,1,4], root2 = [1,0,3]
 *    Output: [0,1,1,2,3,4]
 *
 * Example 2:
 *    Input: root1 = [1,null,8], root2 = [8,1]
 *    Output: [1,1,8,8]
 *
 * Constraints:
 *    The number of nodes in each tree is in the range [0, 5000].
 *    -10^5 <= Node.val <= 10^5
 */
public class LC1305 {
   public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
      Stack<TreeNode> stack1 = new Stack<>();
      Stack<TreeNode> stack2 = new Stack<>();
      List<Integer> res = new ArrayList<>();

      while (root1 != null || root2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
         while (root1 != null) {
            stack1.push(root1);
            root1 = root1.left;
         }

         while (root2 != null) {
            stack2.push(root2);
            root2 = root2.left;
         }

         if (stack2.isEmpty() || !stack1.isEmpty() && stack1.peek().val <= stack2.peek().val) {
            root1 = stack1.pop();
            res.add(root1.val);
            root1 = root1.right;
         } else {
            root2 = stack2.pop();
            res.add(root2.val);
            root2 = root2.right;
         }
      }

      return res;
   }
}

package com.kamarkaka;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 652. Find Duplicate Subtrees
 * Given the root of a binary tree, return all duplicate subtrees.
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 * Example 1:
 *    Input: root = [1,2,3,4,null,2,4,null,null,4]
 *    Output: [[2,4],[4]]
 *
 * Example 2:
 *    Input: root = [2,1,1]
 *    Output: [[1]]
 *
 * Example 3:
 *    Input: root = [2,2,2,3,null,3,null]
 *    Output: [[2,3],[3]]
 *
 * Constraints:
 *    The number of the nodes in the tree will be in the range [1, 10^4]
 *    -200 <= Node.val <= 200
 */
public class LC0652 {
   int id = 1;

   public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
      List<TreeNode> res = new ArrayList<>();
      if (root == null) return res;
      Map<String, Integer> serialization = new HashMap<>();
      Map<Integer, Integer> count = new HashMap<>();
      getSerializationId(root, serialization, count, res);
      return res;
   }
   public int getSerializationId(TreeNode root,  Map<String, Integer> s, Map<Integer, Integer> count, List<TreeNode> res) {
      if (root == null) return 0;

      int lId = getSerializationId(root.left, s, count, res);
      int rId = getSerializationId(root.right, s, count, res);
      String str = root.val + "*" + lId + "*" + rId;
      Integer nId = s.get(str);
      if (nId == null) {
         s.put(str, id);
         nId = id;
         id++;
      }
      count.put(nId, count.getOrDefault(nId, 0) + 1);
      if (count.get(nId) == 2) res.add(root);
      return nId;

   }
}

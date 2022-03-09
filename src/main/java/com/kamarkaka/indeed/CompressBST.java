package com.kamarkaka.indeed;

import com.kamarkaka.common.TreeNode;
import com.kamarkaka.common.Utilities;

import java.util.*;

public class CompressBST {
   public Integer[] compressDense(TreeNode root) {
      int height = getHeight(root);
      if (height == 0) return new Integer[0];

      int len = (int) Math.pow(2, height) - 1;
      Integer[] arr = new Integer[len];
      Arrays.fill(arr, null);

      Queue<TreeNode> nodeQueue = new LinkedList<>();
      Queue<Integer> idxQueue = new LinkedList<>();

      nodeQueue.add(root);
      idxQueue.add(0);

      while (!nodeQueue.isEmpty()) {
         TreeNode node = nodeQueue.poll();
         int idx = idxQueue.poll();

         arr[idx] = node.val;

         if (node.left != null) {
            nodeQueue.add(node.left);
            idxQueue.add(2 * idx + 1);
         }
         if (node.right != null) {
            nodeQueue.add(node.right);
            idxQueue.add(2 * idx + 2);
         }
      }

      return arr;
   }

   public int[][] compressSparse(TreeNode root) {
      List<Integer> nodeValues = new ArrayList<>();
      List<Integer> parents = new ArrayList<>();

      Queue<TreeNode> nodeQueue = new LinkedList<>();
      Queue<Integer> idxQueue = new LinkedList<>();

      nodeQueue.add(root);
      idxQueue.add(0);

      nodeValues.add(root.val);
      parents.add(-1);

      while (!nodeQueue.isEmpty()) {
         TreeNode node = nodeQueue.poll();
         int idx = idxQueue.poll();

         if (node.left != null) {
            nodeValues.add(node.left.val);
            parents.add(idx);

            nodeQueue.add(node.left);
            idxQueue.add(parents.size() - 1);
         }
         if (node.right != null) {
            nodeValues.add(node.right.val);
            parents.add(idx);

            nodeQueue.add(node.right);
            idxQueue.add(parents.size() - 1);
         }
      }

      int[][] res = new int[2][parents.size()];
      for (int i = 0; i < nodeValues.size(); i++) {
         res[0][i] = nodeValues.get(i);
      }
      for (int i = 0; i < parents.size(); i++) {
         res[1][i] = parents.get(i);
      }
      return res;
   }

   private int getHeight(TreeNode root) {
      if (root == null) return 0;
      return 1 + Math.max(getHeight(root.left), getHeight(root.right));
   }

   public static void run() {
      CompressBST sol = new CompressBST();

      TreeNode root = new TreeNode(1);
      root.left = new TreeNode(2);
      root.right = new TreeNode(3);
      root.left.left = new TreeNode(4);

      Utilities.print(sol.compressDense(root));
      Utilities.print(sol.compressSparse(root));
   }
}

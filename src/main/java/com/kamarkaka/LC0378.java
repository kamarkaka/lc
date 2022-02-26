package com.kamarkaka;

import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * 378. Kth Smallest Element in a Sorted Matrix
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * You must find a solution with a memory complexity better than O(n^2).
 *
 * Example 1:
 *    Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 *    Output: 13
 *    Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 *
 * Example 2:
 *    Input: matrix = [[-5]], k = 1
 *    Output: -5
 *
 * Constraints:
 *    n == matrix.length == matrix[i].length
 *    1 <= n <= 300
 *    -10^9 <= matrix[i][j] <= 10^9
 *    All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 *    1 <= k <= n^2
 *
 * Follow up:
 *    Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 *    Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 */
public class LC0378 {
   public int kthSmallest(int[][] matrix, int k) {
      int N = matrix.length;
      PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

      for (int r = 0; r < Math.min(N, k); r++) {
         minHeap.add(new HeapNode(r, 0, matrix[r][0]));
      }

      HeapNode node = minHeap.peek();
      while (k-- > 0) {
         node = minHeap.poll();
         int r = node.row, c = node.col;
         if (c < N - 1) {
            minHeap.add(new HeapNode(r, c + 1, matrix[r][c+1]));
         }
      }
      return node.val;
   }

   class HeapNode {
      int row;
      int col;
      int val;

      HeapNode(int row, int col, int val) {
         this.row = row;
         this.col = col;
         this.val = val;
      }
   }

   public int kthSmallest2(int[][] matrix, int k) {
      int n = matrix.length;
      int lo = matrix[0][0], hi = matrix[n-1][n-1];
      while (lo <= hi) {
         int mid = lo + (hi - lo) / 2;
         int count = getLessEqual(matrix, mid);
         if (count < k) lo = mid + 1;
         else hi = mid - 1;
      }
      return lo;
   }

   private int getLessEqual(int[][] matrix, int val) {
      int res = 0;
      int n = matrix.length, i = n-1, j = 0;
      while (i >= 0 && j < n) {
         if (matrix[i][j] > val) i--;
         else {
            res += i + 1;
            j++;
         }
      }
      return res;
   }
}

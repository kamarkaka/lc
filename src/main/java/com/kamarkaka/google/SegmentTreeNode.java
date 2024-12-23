package com.kamarkaka.google;

public class SegmentTreeNode {
   public int count;
   public int start;
   public int end;
   public SegmentTreeNode left;
   public SegmentTreeNode right;

   public SegmentTreeNode(int start, int end, int count) {
      this.count = count;
      this.start = start;
      this.end = end;
      this.left = null;
      this.right = null;
   }

   public static SegmentTreeNode addSegment(SegmentTreeNode node, int start, int end, int count) {
      if (node == null) {
         return new SegmentTreeNode(start, end, count);
      }

      if (node.start >= end) {
         node.left = addSegment(node.left, start, end, count);
         return node;
      }

      if (node.end <= start) {
         node.right = addSegment(node.right, start, end, count);
         return node;
      }

      int leftStart = Math.min(start, node.start);
      int leftEnd = Math.max(start, node.start);
      int leftCount = leftStart == start ? count : node.count;

      int rightStart = Math.min(end, node.end);
      int rightEnd = Math.max(end, node.end);
      int rightCount = rightEnd == end ? count : node.count;

      node.start = leftEnd;
      node.end = rightStart;
      node.count += count;

      if (leftStart != leftEnd) node.left = addSegment(node.left, leftStart, leftEnd, leftCount);
      if (rightStart != rightEnd) node.right = addSegment(node.right, rightStart, rightEnd, rightCount);
      return node;
   }

   public void print() {
      if (this.left != null) this.left.print();
      System.out.println("(from: " + this.start + ", to: " + this.end + ", count: " + this.count + ")");
      if (this.right != null) this.right.print();
   }

   public static void run() {
      SegmentTreeNode root = null;
      root = SegmentTreeNode.addSegment(root, 10, 20, 1);
      root = SegmentTreeNode.addSegment(root, 50, 60, 1);
      root = SegmentTreeNode.addSegment(root, 10, 40, 1);
      root = SegmentTreeNode.addSegment(root, 5, 15, 1);
      root = SegmentTreeNode.addSegment(root, 5, 10, 1);
      root = SegmentTreeNode.addSegment(root, 25, 55, 1);

      root.print();
   }
}

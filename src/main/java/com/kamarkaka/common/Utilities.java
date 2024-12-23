package com.kamarkaka.common;

import com.kamarkaka.meta.SubArrays;

import java.util.Arrays;
import java.util.List;

public class Utilities {
   public static void print(int[] nums) {
      System.out.println(Arrays.toString(nums));
   }

   public static void print(Integer[] nums) {
      System.out.println(Arrays.toString(nums));
   }

   public static void print(String[] strings) {
      System.out.println(Arrays.toString(strings));
   }

   public static void print(int[][] matrix) {
      String[] prints = new String[matrix.length];
      for (int i = 0; i < matrix.length; i++) {
         prints[i] = Arrays.toString(matrix[i]);
      }
      System.out.println(Arrays.toString(prints));
   }

   public static void print(char[][] matrix) {
      String[] prints = new String[matrix.length];
      for (int i = 0; i < matrix.length; i++) {
         prints[i] = Arrays.toString(matrix[i]);
      }
      System.out.println(Arrays.toString(prints));
   }

   public static <T> void print(List<List<T>> lists) {
      String[] s = new String[lists.size()];
      for (int i = 0; i < lists.size(); i++) {
         s[i] = lists.get(i).toString();
      }

      System.out.println(Arrays.toString(s));
   }

   public static void print(ListNode node) {
      System.out.print("[");
      while (node != null) {
         System.out.print(node.val);
         node = node.next;
         if (node != null) System.out.print(", ");
      }
      System.out.println("]");
   }

   public static void main(String[] args) {
      SubArrays.run();
   }
}

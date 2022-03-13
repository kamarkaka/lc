package com.kamarkaka.twosigma;

import com.kamarkaka.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKStream {
   public ListNode mergeK(ListNode[] lists) {
      PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
      for (ListNode list : lists) {
         if (list != null) pq.add(list);
      }

      ListNode head = null, tail = null;
      while (!pq.isEmpty()) {
         ListNode node = pq.poll();
         if (head == null) {
            head = node;
            tail = node;
         } else {
            tail.next = node;
            tail = tail.next;
         }

         if (node.next != null) pq.add(node.next);
      }
      return head;
   }
}

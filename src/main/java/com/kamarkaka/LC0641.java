package main.java.com.kamarkaka;

/***
 * 641. Design Circular Deque
 * Design your implementation of the circular double-ended queue (deque).
 * Implement the MyCircularDeque class:
 *   MyCircularDeque(int k) Initializes the deque with a maximum size of k.
 *   boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
 *   boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
 *   boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
 *   boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
 *   int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
 *   int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
 *   boolean isEmpty() Returns true if the deque is empty, or false otherwise.
 *   boolean isFull() Returns true if the deque is full, or false otherwise.
 *
 * Example 1:
 *   Input
 *     ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 *     [[3], [1], [2], [3], [4], [], [], [], [4], []]
 *   Output
 *     [null, true, true, true, false, 2, true, true, true, 4]
 *   Explanation
 *     MyCircularDeque myCircularDeque = new MyCircularDeque(3);
 *     myCircularDeque.insertLast(1);  // return True
 *     myCircularDeque.insertLast(2);  // return True
 *     myCircularDeque.insertFront(3); // return True
 *     myCircularDeque.insertFront(4); // return False, the queue is full.
 *     myCircularDeque.getRear();      // return 2
 *     myCircularDeque.isFull();       // return True
 *     myCircularDeque.deleteLast();   // return True
 *     myCircularDeque.insertFront(4); // return True
 *     myCircularDeque.getFront();     // return 4
 *
 * Constraints:
 *   1 <= k <= 1000
 *   0 <= value <= 1000
 *   At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
 */
public class LC0641 {
   class MyCircularDeque {
      private final int capacity;
      private int size;
      private final Node head;
      private final Node tail;

      public MyCircularDeque(int k) {
         this.capacity = k;
         this.size = 0;
         this.head = new Node(-1);
         this.tail = new Node(-1);

         head.next = tail;
         tail.prev = head;
      }

      public boolean insertFront(int value) {
         if (isFull()) return false;

         Node node = new Node(value);
         Node next = head.next;
         head.next = node;
         node.next = next;
         next.prev = node;
         node.prev = head;
         size++;
         return true;

      }

      public boolean insertLast(int value) {
         if (isFull()) return false;

         Node node = new Node(value);

         Node prev = tail.prev;
         prev.next = node;
         node.prev = prev;
         node.next = tail;
         tail.prev = node;
         size++;
         return true;
      }

      public boolean deleteFront() {
         if (isEmpty()) return false;

         Node firs = head.next;
         Node next = firs.next;
         head.next = next;
         next.prev = head;
         size--;
         return true;
      }

      public boolean deleteLast() {
         if (isEmpty()) return false;

         Node last = tail.prev;
         Node prev = last.prev;
         prev.next = tail;
         tail.prev = prev;
         size--;
         return true;
      }

      public int getFront() {
         return head.next.val;
      }

      public int getRear() {
         return tail.prev.val;
      }

      public boolean isEmpty() {
         return size == 0;
      }

      public boolean isFull() {
         return size == capacity;
      }

      private class Node {
         int val;
         Node prev;
         Node next;

         public Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
         }
      }
   }
}

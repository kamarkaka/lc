package main.java.com.kamarkaka;

import java.util.*;

/***
 * 146. LRU Cache
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 *   LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 *   int get(int key) Return the value of the key if the key exists, otherwise return -1.
 *   void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *   The functions get and put must each run in O(1) average time complexity.
 *
 * Example 1:
 *   Input
 *   ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 *   [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 *   Output
 *   [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 *   LRUCache lRUCache = new LRUCache(2);
 *   lRUCache.put(1, 1); // cache is {1=1}
 *   lRUCache.put(2, 2); // cache is {1=1, 2=2}
 *   lRUCache.get(1);    // return 1
 *   lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 *   lRUCache.get(2);    // returns -1 (not found)
 *   lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 *   lRUCache.get(1);    // return -1 (not found)
 *   lRUCache.get(3);    // return 3
 *   lRUCache.get(4);    // return 4
 *
 * Constraints:
 *   1 <= capacity <= 3000
 *   0 <= key <= 10^4
 *   0 <= value <= 10^5
 *   At most 2 * 10^5 calls will be made to get and put.
 */
public class LC0146 {
   class LRUCache {
      private int capacity;
      private int currCapacity;
      private HashMap<Integer, Node> hmap;
      private Node head;
      private Node tail;

      public LRUCache(int capacity) {
         this.capacity = capacity;
         this.currCapacity = 0;
         this.hmap = new HashMap<>();
         this.head = null;
         this.tail = null;
      }

      public int get(int key) {
         // return if cache is empty or key is not in map
         if (capacity == 0 || !hmap.containsKey(key)) return -1;

         Node node = hmap.get(key);
         Node prevNode = node.prev;
         Node nextNode = node.next;

         // if node is tail, do nothing
         // only need to consider if node is not tail
         if (nextNode != null) {
            if (prevNode == null) { // if node is head
               head = nextNode;
            } else {
               prevNode.next = nextNode;
            }

            // move node to tail
            nextNode.prev = prevNode;
            tail.next = node;
            node.prev = tail;
            node.next = null;
            tail = node;
         }

         return node.val;
      }

      public void put(int key, int value) {
         if (capacity == 0) return;

         // if key exists, only update
         if (hmap.containsKey(key)) {
            // update
            this.get(key);
            Node node = hmap.get(key);
            node.val = value;
         } else {
            if (currCapacity == capacity) { // if cache is full, remove head
               Node headNode = head;
               Node nextNode = head.next;
               hmap.remove(headNode.key);
               head = nextNode;

               if (nextNode != null) {
                  nextNode.prev = null;
               }

               currCapacity--;
            }

            // put new node to tail
            Node newNode = new Node(key, value);
            hmap.put(key, newNode);

            if (currCapacity == 0) {
               head = newNode;
               tail = newNode;
            } else {
               tail.next = newNode;
               newNode.prev = tail;
               tail = newNode;
            }
            currCapacity++;
         }
      }
   }

   private class Node {
      int key;
      int val;
      Node next;
      Node prev;

      public Node(int key, int val) {
         this.key = key;
         this.val = val;
         next = null;
         prev = null;
      }
   }
}

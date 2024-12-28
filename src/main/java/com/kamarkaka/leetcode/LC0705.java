package com.kamarkaka.leetcode;

/***
 * 705. Design HashSet
 * Design a HashSet without using any built-in hash table libraries.
 * Implement MyHashSet class:
 *   void add(key) Inserts the value key into the HashSet.
 *   bool contains(key) Returns whether the value key exists in the HashSet or not.
 *   void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 * Example 1:
 *   Input
 *   ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 *   [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 *   Output
 *   [null, null, null, true, false, null, true, null, false]
 *   Explanation
 *   MyHashSet myHashSet = new MyHashSet();
 *   myHashSet.add(1);      // set = [1]
 *   myHashSet.add(2);      // set = [1, 2]
 *   myHashSet.contains(1); // return True
 *   myHashSet.contains(3); // return False, (not found)
 *   myHashSet.add(2);      // set = [1, 2]
 *   myHashSet.contains(2); // return True
 *   myHashSet.remove(2);   // set = [1]
 *   myHashSet.contains(2); // return False, (already removed)
 * Constraints:
 *   0 <= key <= 10^6
 *   At most 104 calls will be made to add, remove, and contains.
 */
public class LC0705 {
    class MyHashSet {
        private int capacity = 10001;
        private Node[] bucket;

        /** Initialize your data structure here. */
        public MyHashSet() {
            bucket = new Node[capacity];
        }

        public void add(int key) {
            int hash = key % capacity;
            Node prev = bucket[hash];
            while (prev != null) {
                if (prev.key == key) {
                    return;
                }
                prev = prev.next;
            }

            Node node = new Node(key);
            node.next = bucket[hash];
            bucket[hash] = node;
        }

        public void remove(int key) {
            int hash = key % capacity;
            Node prev = null;
            Node curr = bucket[hash];
            while (curr != null) {
                if (curr.key == key) {
                    if (prev == null) {
                        bucket[hash] = curr.next;
                    } else {
                        prev.next = curr.next;
                    }
                }

                prev = curr;
                curr = curr.next;
            }
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int hash  = key % capacity;
            Node prev = bucket[hash];
            while (prev != null) {
                if (prev.key == key) {
                    return true;
                }

                prev = prev.next;
            }
            return false;
        }

        class Node {
            public int key;
            public Node next;
            Node(int key) {
                this.key = key;
            }
        }

    }
}

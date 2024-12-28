package com.kamarkaka.leetcode;

/***
 * 706. Design HashMap
 * Design a HashMap without using any built-in hash table libraries.
 * Implement the MyHashMap class:
 *   MyHashMap() initializes the object with an empty map.
 *   void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map,
 *   update the corresponding value.
 *   int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for
 *   the key.
 *   void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 * Example 1:
 *   Input
 *   ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 *   [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 *   Output
 *   [null, null, null, 1, -1, null, 1, null, -1]
 *   Explanation
 *   MyHashMap myHashMap = new MyHashMap();
 *   myHashMap.put(1, 1); // The map is now [[1,1]]
 *   myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
 *   myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
 *   myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
 *   myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
 *   myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
 *   myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
 *   myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 * Constraints:
 *   0 <= key, value <= 10^6
 *   At most 10^4 calls will be made to put, get, and remove.
 */
public class LC0706 {
    class MyHashMap {
        private int capacity = 10001;
        private Node[] bucket;

        /** Initialize your data structure here. */
        public MyHashMap() {
            bucket = new Node[capacity];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int hash = key % capacity;
            Node prev = bucket[hash];
            while (prev != null) {
                if (prev.key == key) {
                    prev.value = value;
                    return;
                }
                prev = prev.next;
            }

            Node node = new Node(key, value);
            node.next = bucket[hash];
            bucket[hash] = node;
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int hash = key % capacity;
            Node prev = bucket[hash];
            while (prev != null) {
                if (prev.key == key) {
                    return prev.value;
                }
                prev = prev.next;
            }

            return -1;
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
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

        class Node {
            public int key;
            public int value;
            public Node next;
            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}

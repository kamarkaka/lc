package com.kamarkaka.leetcode;

import java.util.PriorityQueue;

/***
 * 716. Max Stack
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum
 * element.
 * Implement the MaxStack class:
 *   MaxStack() Initializes the stack object.
 *   void push(int x) Pushes element x onto the stack.
 *   int pop() Removes the element on top of the stack and returns it.
 *   int top() Gets the element on the top of the stack without removing it.
 *   int peekMax() Retrieves the maximum element in the stack without removing it.
 *   int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element,
 *   only remove the top-most one.
 * You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.
 * Example 1:
 *   Input
 *   ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
 *   [[], [5], [1], [5], [], [], [], [], [], []]
 *   Output
 *   [null, null, null, null, 5, 5, 1, 5, 1, 5]
 *   Explanation
 *   MaxStack stk = new MaxStack();
 *   stk.push(5);   // [5] the top of the stack and the maximum number is 5.
 *   stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
 *   stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
 *   stk.top();     // return 5, [5, 1, 5] the stack did not change.
 *   stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
 *   stk.top();     // return 1, [5, 1] the stack did not change.
 *   stk.peekMax(); // return 5, [5, 1] the stack did not change.
 *   stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
 *   stk.top();     // return 5, [5] the stack did not change.
 * Constraints:
 *   -10^7 <= x <= 10^7
 *   At most 105 calls will be made to push, pop, top, peekMax, and popMax.
 *   There will be at least one element in the stack when pop, top, peekMax, or popMax is called.
 */
public class LC0716 {
    class MaxStack {
        Node head;
        Node tail;

        /** initialize your data structure here. */
        public MaxStack() {
            this.head = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);
            this.tail = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
            head.next = tail;
            tail.prev = head;
        }

        public void push(int x) {
            Node newNode = new Node(Math.max(tail.prev.max, x), x);
            tail.prev.next = newNode;
            newNode.prev = tail.prev;
            tail.prev = newNode;
            newNode.next = tail;
        }

        public int pop() {
            Node curr = tail.prev;
            curr.prev.next = tail;
            tail.prev = curr.prev;
            return curr.val;
        }

        public int top() {
            return tail.prev.val;
        }

        public int peekMax() {
            return tail.prev.max;
        }

        public int popMax() {
            int max = tail.prev.max;
            Node curr = tail.prev;
            while (curr.val != max) curr = curr.prev;
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr = curr.next;
            while (curr!=tail) {
                curr.max = Math.max(curr.val, curr.prev.max);
                curr = curr.next;
            }
            return max;
        }

        class Node {
            int max;
            int val;
            Node next;
            Node prev;

            public Node(int max, int val) {
                this.max = max;
                this.val = val;
                this.next = null;
                this.prev = null;
            }
        }
    }
}

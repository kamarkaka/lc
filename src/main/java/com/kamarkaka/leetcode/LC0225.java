package com.kamarkaka.leetcode;

import java.util.LinkedList;

/***
 * 225. Implement Stack using Queues
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the
 * functions of a normal stack (push, top, pop, and empty).
 * Implement the MyStack class:
 *   void push(int x) Pushes element x to the top of the stack.
 *   int pop() Removes the element on the top of the stack and returns it.
 *   int top() Returns the element on the top of the stack.
 *   boolean empty() Returns true if the stack is empty, false otherwise.
 * Notes:
 *   You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and
 *   is empty operations are valid.
 *   Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque
 *   (double-ended queue) as long as you use only a queue's standard operations.
 * Example 1:
 *   Input
 *   ["MyStack", "push", "push", "top", "pop", "empty"]
 *   [[], [1], [2], [], [], []]
 *   Output
 *   [null, null, null, 2, 2, false]
 *   Explanation
 *   MyStack myStack = new MyStack();
 *   myStack.push(1);
 *   myStack.push(2);
 *   myStack.top(); // return 2
 *   myStack.pop(); // return 2
 *   myStack.empty(); // return False
 * Constraints:
 *   1 <= x <= 9
 *   At most 100 calls will be made to push, pop, top, and empty.
 *   All the calls to pop and top are valid.
 * Follow-up: Can you implement the stack using only one queue?
 */
public class LC0225 {
    class MyStack {
        private MyQueue queue;

        /** Initialize your data structure here. */
        public MyStack() {
            queue = new MyQueue();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue.push(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            int len = queue.getSize();

            if (len > 0) {
                while (true) {
                    len--;

                    int x = queue.pop();

                    if (len == 0) {
                        return x;
                    } else {
                        queue.push(x);
                    }
                }


            } else {
                throw new RuntimeException("stack is empty");
            }
        }

        /** Get the top element. */
        public int top() {
            int len = queue.getSize();

            if (len > 0) {
                while (true) {
                    len--;

                    int x = queue.pop();
                    queue.push(x);

                    if (len == 0) {
                        return x;
                    }
                }
            } else {
                throw new RuntimeException("stack is empty");
            }
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.getSize() == 0;
        }
    }

    class MyQueue {
        private LinkedList<Integer> queue;
        private int size;

        public MyQueue() {
            queue = new LinkedList<Integer>();
            size = 0;
        }

        public void push(int x) {
            queue.addLast(x);
            size++;
        }

        public int peek() {
            if (size > 0) {
                return queue.getFirst();
            } else {
                throw new RuntimeException("queue is empty");
            }
        }

        public int pop() {
            if (size > 0) {
                int x = queue.getFirst();
                queue.removeFirst();
                size--;
                return x;
            } else {
                throw new RuntimeException("queue is empty");
            }
        }

        public int getSize() {
            return size;
        }
    }
}

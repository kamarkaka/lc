package com.kamarkaka.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 346. Moving Average from Data Stream
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * Implement the MovingAverage class:
 *   MovingAverage(int size) Initializes the object with the size of the window size.
 *   double next(int val) Returns the moving average of the last size values of the stream.
 * Example 1:
 *   Input
 *   ["MovingAverage", "next", "next", "next", "next"]
 *   [[3], [1], [10], [3], [5]]
 *   Output
 *   [null, 1.0, 5.5, 4.66667, 6.0]
 *   Explanation
 *   MovingAverage movingAverage = new MovingAverage(3);
 *   movingAverage.next(1); // return 1.0 = 1 / 1
 *   movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 *   movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 *   movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 * Constraints:
 *   1 <= size <= 1000
 *   -10^5 <= val <= 10^5
 *   At most 10^4 calls will be made to next.
 */
public class LC0346 {
    Queue<Integer> queue;
    int capacity;
    long sum;

    /** Initialize your data structure here. */
    public LC0346(int size) {
        queue = new LinkedList<>();
        capacity = size;
    }

    public double next(int val) {
        if (capacity == 0) return 0;

        if (queue.size() == capacity) {
            int num = queue.poll();
            sum -= num;
        }

        queue.add(val);
        sum += val;
        return (double) sum / queue.size();
    }
}

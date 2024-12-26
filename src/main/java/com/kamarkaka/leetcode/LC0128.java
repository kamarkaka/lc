package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 128. Longest Consecutive Sequence
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 * Example 1:
 *   Input: nums = [100,4,200,1,3,2]
 *   Output: 4
 *   Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * Example 2:
 *   Input: nums = [0,3,7,2,5,8,4,6,0,1]
 *   Output: 9
 * Constraints:
 *   0 <= nums.length <= 10^5
 *   -10^9 <= nums[i] <= 10^9
 */
public class LC0128 {
    public int longestConsecutive(int[] nums) {
        Map<Integer, ConsecutiveSequence> map = new HashMap<>();
        int length = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) continue; // ignore duplicates

            if (map.containsKey(num - 1)) { // num can be appended to the tail of a seq
                ConsecutiveSequence seq = map.get(num - 1);
                seq.updateTail(num);

                int tail = seq.tail;
                while (map.containsKey(tail + 1)) {
                    int newTail = seq.updateTail(map.get(tail + 1).tail);
                    if (tail == newTail) break;
                    tail = newTail;
                }
                map.put(tail, seq);
                map.put(num, seq);
                length = Math.max(length, seq.getLength());
            } else if (map.containsKey(num + 1)) { // num can be inserted into the head of a seq
                ConsecutiveSequence seq = map.get(num + 1);
                seq.updateHead(num);

                int head = seq.head;
                while (map.containsKey(head - 1)) {
                    int newHead = seq.updateHead(map.get(head - 1).head);
                    if (newHead == head) break;
                    head = newHead;
                }
                map.put(head, seq);
                map.put(num, seq);
                length = Math.max(length, seq.getLength());
            } else {
                map.put(num, new ConsecutiveSequence(num));
                length = Math.max(length, 1);
            }
        }

        return length;
    }

    class ConsecutiveSequence {
        int head;
        int tail;

        public ConsecutiveSequence(int num) {
            this.head = num;
            this.tail = num;
        }

        public int updateHead(int head) {
            if (head < this.head) this.head = head;
            return this.head;
        }

        public int updateTail(int tail) {
            if (tail > this.tail) this.tail = tail;
            return this.tail;
        }

        public int getLength() {
            return tail - head + 1;
        }
    }
}

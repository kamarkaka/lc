package com.kamarkaka;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/***
 * 855. Exam Room
 * There is an exam room with n seats in a single row labeled from 0 to n - 1.
 * When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If there are multiple such seats, they sit in the seat with the lowest number. If no one is in the room, then the student sits at seat number 0.
 * Design a class that simulates the mentioned exam room.
 * Implement the ExamRoom class:
 *    ExamRoom(int n) Initializes the object of the exam room with the number of the seats n.
 *    int seat() Returns the label of the seat at which the next student will set.
 *    void leave(int p) Indicates that the student sitting at seat p will leave the room. It is guaranteed that there will be a student sitting at seat p.
 *
 * Example 1:
 *    Input
 *       ["ExamRoom", "seat", "seat", "seat", "seat", "leave", "seat"]
 *       [[10], [], [], [], [], [4], []]
 *    Output
 *       [null, 0, 9, 4, 2, null, 5]
 *    Explanation
 *       ExamRoom examRoom = new ExamRoom(10);
 *       examRoom.seat(); // return 0, no one is in the room, then the student sits at seat number 0.
 *       examRoom.seat(); // return 9, the student sits at the last seat number 9.
 *       examRoom.seat(); // return 4, the student sits at the last seat number 4.
 *       examRoom.seat(); // return 2, the student sits at the last seat number 2.
 *       examRoom.leave(4);
 *       examRoom.seat(); // return 5, the student sits at the last seat number 5.
 *
 * Constraints:
 *    1 <= n <= 10^9
 *    It is guaranteed that there is a student sitting at seat p.
 *    At most 104 calls will be made to seat and leave.
 */
public class LC0855 {
   class ExamRoom {
      private PriorityQueue<Interval> pq;
      private int N;

      public ExamRoom(int n) {
         this.pq = new PriorityQueue<>((a, b) -> a.length != b.length ? b.length - a.length : a.start - b.start);
         this.N = n;
         pq.offer(new Interval(0, N - 1));
      }

      public int seat() {
         Interval in = pq.poll();
         int result;
         if (in.start == 0) {
            result = 0;
         } else if (in.end == N - 1) {
            result = N - 1;
         } else {
            result = in.start + in.length;
         }

         if (result > in.start) {
            pq.offer(new Interval(in.start, result - 1));
         }
         if (in.end > result) {
            pq.offer(new Interval(result + 1, in.end));
         }
         return result;
      }

      public void leave(int p) {
         List<Interval> list = new ArrayList(pq);
         Interval prev = null;
         Interval next = null;
         for (Interval in : list) {
            if (in.end + 1 == p) {
               prev = in;
            }
            if (in.start - 1 == p) {
               next = in;
            }
         }
         pq.remove(prev);
         pq.remove(next);
         pq.offer(new Interval(prev == null ? p : prev.start, next == null ? p : next.end));
      }

      class Interval {
         int start;
         int end;
         int length;

         public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            if (start == 0 || end == N - 1) {
               this.length = end - start;
            } else {
               this.length = (end - start) / 2;
            }
         }
      }
   }
}

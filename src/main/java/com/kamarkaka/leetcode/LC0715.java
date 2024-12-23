package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/***
 * 715. Range Module
 * A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.
 * A half-open interval [left, right) denotes all the real numbers x where left <= x < right.
 * Implement the RangeModule class:
 *   RangeModule() Initializes the object of the data structure.
 *   void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
 *   boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
 *   void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).
 *
 * Example 1:
 *   Input
 *     ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 *     [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 *   Output
 *     [null, null, null, true, false, true]
 *   Explanation
 *     RangeModule rangeModule = new RangeModule();
 *     rangeModule.addRange(10, 20);
 *     rangeModule.removeRange(14, 16);
 *     rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is being tracked)
 *     rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
 *     rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17) is still being tracked, despite the remove operation)
 *
 * Constraints:
 * 1 <= left < right <= 10^9
 * At most 104 calls will be made to addRange, queryRange, and removeRange.
 */
public class LC0715 {
   class RangeModule {
      TreeSet<Interval> ranges;

      public RangeModule() {
         ranges = new TreeSet<>();
      }

      public void addRange(int left, int right) {
         Iterator<Interval> iterator = ranges.tailSet(new Interval(0, left)).iterator();
         while (iterator.hasNext()) {
            Interval iv = iterator.next();
            if (right < iv.left) break;

            left = Math.min(left, iv.left);
            right = Math.max(right, iv.right);
            iterator.remove();
         }
         ranges.add(new Interval(left, right));
      }

      public boolean queryRange(int left, int right) {
         Interval iv = ranges.higher(new Interval(0, left));
         return (iv != null && iv.left <= left && right <= iv.right);
      }

      public void removeRange(int left, int right) {
         List<Interval> todo = new ArrayList<>();
         Iterator<Interval> iterator = ranges.tailSet(new Interval(0, left + 1)).iterator();
         while (iterator.hasNext()) {
            Interval iv = iterator.next();
            if (right < iv.left) break;
            if (iv.left < left) todo.add(new Interval(iv.left, left));
            if (right < iv.right) todo.add(new Interval(right, iv.right));

            iterator.remove();
         }

         ranges.addAll(todo);
      }
   }

   private class Interval implements Comparable<Interval> {
      int left;
      int right;

      public Interval(int left, int right) {
         this.left = left;
         this.right = right;
      }

      public int compareTo(Interval that) {
         if (this.right == that.right) return this.left - that.left;
         return this.right - that.right;
      }
   }
}

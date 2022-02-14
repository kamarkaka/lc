package main.java.com.kamarkaka;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

/***
 * 846. Hand of Straights
 * Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
 * Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
 *
 * Example 1:
 *   Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 *   Output: true
 *   Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 *
 * Example 2:
 *   Input: hand = [1,2,3,4,5], groupSize = 4
 *   Output: false
 *   Explanation: Alice's hand can not be rearranged into groups of 4.
 *
 * Constraints:
 * 1 <= hand.length <= 10^4
 * 0 <= hand[i] <= 10^9
 * 1 <= groupSize <= hand.length
 *
 * Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */
public class LC0846 {
   public boolean isNStraightHand(int[] hand, int groupSize) {
      if (hand.length % groupSize != 0) return false;

      SortedMap<Integer, Integer> sortedMap = new TreeMap<>();
      for (int val : hand) {
         sortedMap.put(val, sortedMap.getOrDefault(val, 0) + 1);
      }

      while (sortedMap.size() > 0) {
         int val = 0, freq = 0;
         for (int i = 0; i < groupSize; i++) {
            if (i == 0) {
               val = sortedMap.firstKey();
            } else if (!sortedMap.containsKey(val)) return false;

            freq = sortedMap.get(val);
            if (freq > 1) {
               sortedMap.put(val, freq - 1);
            } else {
               sortedMap.remove(val);
            }

            val++;
         }
      }

      return true;
   }

   public boolean isNStraightHand2(int[] hand, int groupSize) {
      if(hand.length % groupSize != 0) return false;

      Arrays.sort(hand);   //[1,2,2,3,3,4,6,7,8]

      // Traverse the array and for each value find k consecutive values and mark the values used
      // and if not possible then return false or else continue .

      for (int i = 0; i < hand.length; i++) {
         if (hand[i] == -1) continue;   // the value has already been encountered and used .
         int currHand = hand[i];
         hand[i] = -1;
         int temp = groupSize - 1;

         // used the current value and if temp is not zero implies that we have to
         // take  (W-1) more values
         if (temp != 0) {
            for (int j = i + 1; j < hand.length; j++) {
               // Checking for the consecutive values
               if (hand[j] == currHand + 1) {
                  temp--;
                  currHand = hand[j];
                  hand[j] = -1;  // updating the consecutive values as used;
               }
               // if we got all the required number of values break the loop.
               if(temp == 0) {
                  break;
               }
            }
         }
         // if we didn't find then return false;
         if (temp != 0) return false;

         // After one loop the array will look like this [-1,-1,2,-1,3,4,6,7,8] ;
      }

      for (int i : hand) {
         // if we have a remaing value which is not yet used then return false;
         if (i != -1) {
            return false;
         }
      }
      return true;
   }
}

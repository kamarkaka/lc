package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 1472. Design Browser History
 * You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.
 * Implement the BrowserHistory class:
 *   BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
 *   void visit(string url) Visits url from the current page. It clears up all the forward history.
 *   string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
 *   string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
 *
 * Example:
 *   Input:
 *     ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
 *     [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
 *   Output:
 *     [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
 *   Explanation:
 *     BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
 *     browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
 *     browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
 *     browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
 *     browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
 *     browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
 *     browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
 *     browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
 *     browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
 *     browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
 *     browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
 *
 * Constraints:
 *   1 <= homepage.length <= 20
 *   1 <= url.length <= 20
 *   1 <= steps <= 100
 *   homepage and url consist of  '.' or lower case English letters.
 *   At most 5000 calls will be made to visit, back, and forward.
 */
public class LC1472 {
   class BrowserHistory {
      Node curr;
      Map<String, Node> map;

      public BrowserHistory(String homepage) {
         curr = new Node(homepage);
         map = new HashMap<>();
         map.put(homepage, curr);
      }

      public void visit(String url) {
         Node node;
         if (map.containsKey(url)) {
            node = map.get(url);
            Node prev = node.prev;
            Node next = node.next;
            if (prev != null) {
               prev.next = next;
               if (next != null) {
                  next.prev = prev;
               }
            } else {
               next.prev = null;
            }
         } else {
            node = new Node(url);
            map.put(url, node);
         }

         curr.next = node;
         node.prev = curr;
         curr = node;
      }

      public String back(int steps) {
         Node node = curr;
         for (int i = 0; i < steps; i++) {
            if (node.prev == null) break;

            node = node.prev;
         }

         curr = node;
         return curr.url;
      }

      public String forward(int steps) {
         Node node = curr;
         for (int i = 0; i < steps; i++) {
            if (node.next == null) break;

            node = node.next;
         }

         curr = node;
         return curr.url;
      }
   }

   class Node {
      String url;
      Node prev;
      Node next;

      public Node(String url) {
         this.url = url;
         this.prev = null;
         this.next = null;
      }
   }

   class BrowserHistory2 {
      private List<String> visits;
      private int size;
      private int cur;

      public BrowserHistory2(String homepage) {
         visits = new ArrayList<>();
         visits.add(homepage);
         size = 1;
         cur = 0;
      }

      public void visit(String url) {
         if (cur + 1 < visits.size()) {
            visits.set(cur + 1, url);
         } else {
            visits.add(url);
         }
         cur++;
         size = cur + 1;
      }

      public String back(int steps) {
         int index = Math.max(0, cur - steps);
         cur = index;
         return visits.get(cur);
      }

      public String forward(int steps) {
         int index = Math.min(size - 1, cur + steps);
         cur = index;
         return visits.get(cur);
      }
   }
}

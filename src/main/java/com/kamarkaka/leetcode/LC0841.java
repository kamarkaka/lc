package com.kamarkaka.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/***
 * 841. Keys and Rooms
 * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all
 * the rooms. However, you cannot enter a locked room without having its key.
 * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it
 * unlocks, and you can take all of them with you to unlock the other rooms.
 * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you
 * can visit all the rooms, or false otherwise.
 * Example 1:
 *   Input: rooms = [[1],[2],[3],[]]
 *   Output: true
 *   Explanation:
 *   We visit room 0 and pick up key 1.
 *   We then visit room 1 and pick up key 2.
 *   We then visit room 2 and pick up key 3.
 *   We then visit room 3.
 *   Since we were able to visit every room, we return true.
 * Example 2:
 *   Input: rooms = [[1,3],[3,0,1],[2],[0]]
 *   Output: false
 *   Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
 * Constraints:
 *   n == rooms.length
 *   2 <= n <= 1000
 *   0 <= rooms[i].length <= 1000
 *   1 <= sum(rooms[i].length) <= 3000
 *   0 <= rooms[i][j] < n
 *   All the values of rooms[i] are unique.
 */
public class LC0841 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>();

        Queue<Integer> roomsToVisit = new LinkedList<>();
        roomsToVisit.add(0);

        while (!roomsToVisit.isEmpty()) {
            int room = roomsToVisit.poll();
            visited.add(room);

            List<Integer> keys = rooms.get(room);
            for (int key : keys) {
                if (!visited.contains(key)) {
                    roomsToVisit.add(key);
                }
            }
        }

        return visited.size() == rooms.size();
    }
}

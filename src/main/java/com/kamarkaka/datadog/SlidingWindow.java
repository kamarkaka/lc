package com.kamarkaka.datadog;

import java.util.*;

/***
 * Sliding windows evaluate sum of last k value, filtered by tag, sorted by timestamp in ascending order.
 * Input: [
 *   {"prod1", 1, -1},
 *   {"prod1", 10, 10},
 *   {"prod1", 3, -10},
 *   {"prod1", 100, 100},
 *   {"prod1", 2, 2},
 *   ...
 * ]
 */
public class SlidingWindow {
    class Log {
        int timestamp;
        int value;

        Log(int timestamp, int value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    int sum(String[] tags, int[] timestamps, int[] values, String tag, int k) {
        Map<String, List<Log>> logMap = new HashMap<>();
        for (int i = 0; i < tags.length; i++) {
            logMap.putIfAbsent(tags[i], new ArrayList<>());
            logMap.get(tags[i]).add(new Log(timestamps[i], values[i]));
        }

        List<Log> logs = logMap.get(tag);
        logs.sort(Comparator.comparingInt(l -> l.timestamp));

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += logs.get(logs.size() - 1 - k).value;
        }
        return sum;
    }
}

package com.kamarkaka.datadog;

import java.util.*;

/***
 * ['apple, facebook, google', 'banana, facebook', 'facebook, google, tesla', 'intuit, google, facebook']
 * 然后有一个 filter list， 根据 filter list 输出这些 Tags 的补集
 * 比如 filter by ['apple']那么 return ['facebook', 'google'] (只有第一个里面有 APPLE）
 * 比如 filter by ['facebook', 'google']那么 return ['apple', 'tesla','intuit']
 * 需要 high performance filter
 * 我是用hashmap 做Cache。 Follow up 是怎么更好的index 这个hashmap (Key 是啥，Value 是啥）。
 */
public class HighPerformanceFilter {
    Map<String, Set<Integer>> streamMap = new HashMap<>();
    List<String> stream = new ArrayList<>();

    public void addTag(String tag) {
        int index = stream.size();
        for (String s : tag.split(",\\s*")) {
            s = s.toLowerCase();
            streamMap.putIfAbsent(s, new HashSet<>());
            streamMap.get(s).add(index);
        }
        stream.add(tag);
    }

    public Set<String> searchTags(List<String> keywords) {
        Map<Integer, Integer> counterMap = new HashMap<>();
        for (String keyword : keywords) {
            for (int document : streamMap.getOrDefault(keyword, new HashSet<>())) {
                counterMap.put(document, counterMap.getOrDefault(document, 0) + 1);
            }
        }
        Set<String> result = new HashSet<>();
        for (int key : counterMap.keySet()) {
            if (counterMap.get(key) == keywords.size()) {
                result.addAll(Arrays.asList(stream.get(key).split(",\\s*")));
            }
        }
        result.removeAll(keywords);
        return result;
    }

    public static void main(String[] args) {
        HighPerformanceFilter solution = new HighPerformanceFilter();
        solution.addTag("apple, facebook, google");
        solution.addTag("banana, facebook");
        solution.addTag("facebook, google, tesla");
        solution.addTag("intuit, google, facebook");
        System.out.println(solution.searchTags(Arrays.asList("facebook", "google")));
    }
}

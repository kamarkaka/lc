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
public class TagFiltering {
    public List<String> filter(List<String> tags, List<String> filters) {
        Map<String, Set<Integer>> tagMap = new HashMap<>();
        Map<Integer, Set<String>> indexMap = new HashMap<>();

        for (int i = 0; i < tags.size(); i++) {
            String tag = tags.get(i);
            for (String tagPiece : tag.split(",")) {
                tagPiece = tagPiece.trim();
                tagMap.putIfAbsent(tagPiece, new HashSet<>());
                tagMap.get(tagPiece).add(i);

                indexMap.putIfAbsent(i, new HashSet<>());
                indexMap.get(i).add(tagPiece);
            }
        }

        Set<Integer> resultSet = new HashSet<>();
        for (String filter : filters) {
            if (!tagMap.containsKey(filter)) break;
            Set<Integer> indexSet = tagMap.get(filter);
            if (resultSet.isEmpty()) {
                resultSet.addAll(indexSet);
            } else {
                resultSet = intersection(resultSet, indexSet);

                if (resultSet.isEmpty()) break;
            }
        }

        List<String> result = new ArrayList<>();
        for (int index : resultSet) {
            for (String tag : indexMap.get(index)) {
                if (!filters.contains(tag)) {
                    result.add(tag);
                }
            }
        }
        return result;
    }

    private Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>();
        for (int el : set1) {
            if (set2.contains(el)) {
                result.add(el);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TagFiltering solution = new TagFiltering();
        List<String> tags = Arrays.asList("apple, facebook, google", "banana, facebook", "facebook, google, tesla", "intuit, google, facebook");
        List<String> filters = Arrays.asList("facebook", "google");
        System.out.println(solution.filter(tags, filters));
    }
}

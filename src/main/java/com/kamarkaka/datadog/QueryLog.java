package com.kamarkaka.datadog;

import java.util.*;

/***
 * Given a series of strings:
 * "Q: Hello world",
 * "Q: Good morning",
 * "L: Hello my friend and the morning is good in this world",
 * "L: This morning is good",
 * "Q: Be quick",
 * "Q: Be careful",
 * "L: Be quick and careful",
 * ...
 * 有的是Query，有的是Log。这些字符串是从左往右一个个按顺序进来的。
 * 每进来一个Query，你要打印出这个Query被assign的ID，比如第一个Query就需要打印1，第二个Query就需要打印2，以此类推。
 * 每进来一个Log，你要打印出有哪些之前进来的Query是match这个Log的。Match的定义就是该Query的所有单词都在该Log里出现，次序不重要。
 * 所以对于第一个Log，我们要打印Query 1和2都match，对于第二个Log，我们要打印Query 2是match。
 *
 */
public class QueryLog {
    private int queryId = 0;
    private final Map<String, Set<Integer>> reverseIndex = new HashMap<>();
    private final Map<Integer, String[]> queryMap = new HashMap<>();

    public String getInput(String str) {
        if (str.charAt(0) == 'Q') {
            this.queryId++;

            String[] words = str.split(" ");
            for (int i = 1; i < words.length; i++) {
                String word = words[i].toLowerCase();
                this.reverseIndex.putIfAbsent(word, new HashSet<>());
                this.reverseIndex.get(word).add(this.queryId);
            }
            queryMap.put(this.queryId, words);
            return Integer.toString(this.queryId);
        } else if (str.charAt(0) == 'L') {
            Set<Integer> potentialQueries = new HashSet<>();
            Set<String> logWordsSet = new HashSet<>();

            String[] words = str.split(" ");
            for (int i = 1; i < words.length; i++) {
                String word = words[i].toLowerCase();
                if (reverseIndex.containsKey(word)) {
                    potentialQueries.addAll(reverseIndex.get(word));
                }
                logWordsSet.add(word);
            }

            Set<Integer> result = new HashSet<>();
            for (int queryId : potentialQueries) {
                String[] queryWords =  this.queryMap.get(queryId);
                boolean queryFound = true;
                for (int i = 1; i < queryWords.length; i++) {
                    String queryWord = queryWords[i].toLowerCase();
                    if (!logWordsSet.contains(queryWord)) {
                        queryFound = false;
                        break;
                    }
                }
                if (queryFound) {
                    result.add(queryId);
                }
            }
            return result.toString();
        }
        return "";
    }

    public static void main(String[] args) {
        QueryLog solution = new QueryLog();
        System.out.println(solution.getInput("Q: Hello world"));
        System.out.println(solution.getInput("Q: Good morning"));
        System.out.println(solution.getInput("L: Hello my friend and the morning is good in this world"));
        System.out.println(solution.getInput("L: This morning is good"));
        System.out.println(solution.getInput("Q: Be quick"));
        System.out.println(solution.getInput("Q: Be careful"));
        System.out.println(solution.getInput("L: Be quick and careful"));
    }
}

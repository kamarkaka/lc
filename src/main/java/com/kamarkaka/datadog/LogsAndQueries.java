package com.kamarkaka.datadog;

import java.util.*;
import java.util.stream.Collectors;

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
 * Input example: [
 *   "Q: hello world",
 *   "Q: data failure",
 *   "Q: world hello",
 *   "L: hello world we have a data failure",
 *   "L: oh no hello system error",
 *   "Q: system error",
 *   "L: oh no hello system error again",
 *   "L: oh no hello world system error again"
 * ]
 * Output would be: [
 *   "Registered q1",
 *   "Registered q2",
 *   "Registered q1",
 *   "Log q1, q2",
 *   "Log no match",
 *   "Registered q3",
 *   "Log q3",
 *   "Log q1, q3"
 * ]
 */
public class LogsAndQueries {
    private int queryId = 0;
    // word -> set of query ids
    private final Map<String, Set<Integer>> invertedIndex = new HashMap<>();
    // query id -> words
    private final Map<Integer, Set<String>> queryMap = new HashMap<>();
    // query signature -> query id, used to look up existing query for reuse
    private final Map<String, Integer> queryLookupMap = new HashMap<>();

    public String getInput(String str) {
        String[] words = str.substring(3).toLowerCase().split(" ");
        Set<String> wordSet = Arrays.stream(words).collect(Collectors.toSet());

        if (str.charAt(0) == 'Q') {
            List<String> wordList = new ArrayList<>(wordSet);
            Collections.sort(wordList);
            String querySignature = wordList.toString();

            int id = this.queryLookupMap.getOrDefault(querySignature, -1);
            if (id == -1) {
                id = ++this.queryId;
                this.queryLookupMap.put(querySignature, id);
            }

           for (String word : words) {
              this.invertedIndex.putIfAbsent(word, new HashSet<>());
              this.invertedIndex.get(word).add(id);
           }
            queryMap.put(id, wordSet);
            return Integer.toString(id);
        } else if (str.charAt(0) == 'L') {
            Set<Integer> potentialQueries = new HashSet<>();

            for (String word : words) {
                if (invertedIndex.containsKey(word)) {
                    potentialQueries.addAll(invertedIndex.get(word));
                }
            }

            Set<Integer> result = new HashSet<>();
            for (int queryId : potentialQueries) {
                Set<String> queryWords =  this.queryMap.get(queryId);
                if (wordSet.containsAll(queryWords)) {
                    result.add(queryId);
                }
            }
            return result.toString();
        }
        return "";
    }

    public static void main(String[] args) {
        LogsAndQueries solution = new LogsAndQueries();
//        System.out.println(solution.getInput("Q: Hello world"));
//        System.out.println(solution.getInput("Q: Good morning"));
//        System.out.println(solution.getInput("L: Hello my friend and the morning is good in this world"));
//        System.out.println(solution.getInput("L: This morning is good"));
//        System.out.println(solution.getInput("Q: Be quick"));
//        System.out.println(solution.getInput("Q: Be careful"));
//        System.out.println(solution.getInput("L: Be quick and careful"));
        System.out.println(solution.getInput("Q: hello world"));
        System.out.println(solution.getInput("Q: data failure"));
        System.out.println(solution.getInput("Q: world hello"));
        System.out.println(solution.getInput("L: hello world we have a data failure"));
        System.out.println(solution.getInput("L: oh no hello system error"));
        System.out.println(solution.getInput("Q: system error"));
        System.out.println(solution.getInput("L: oh no hello system error again"));
        System.out.println(solution.getInput("L: oh no hello world system error again"));
    }
}

package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 1170. Compare Strings by Frequency of the Smallest Character
 * Let the function f(s) be the frequency of the lexicographically smallest character in a non-empty string s. For
 * example, if s = "dcce" then f(s) = 2 because the lexicographically smallest character is 'c', which has a frequency
 * of 2.
 * You are given an array of strings words and another array of query strings queries. For each query queries[i], count
 * the number of words in words such that f(queries[i]) < f(W) for each W in words.
 * Return an integer array answer, where each answer[i] is the answer to the ith query.
 * Example 1:
 *   Input: queries = ["cbd"], words = ["zaaaz"]
 *   Output: [1]
 *   Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
 * Example 2:
 *   Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
 *   Output: [1,2]
 *   Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are
 *   both > f("cc").
 * Constraints:
 *   1 <= queries.length <= 2000
 *   1 <= words.length <= 2000
 *   1 <= queries[i].length, words[i].length <= 10
 *   queries[i][j], words[i][j] consist of lowercase English letters.
 */
public class LC1170 {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] freq = getAllFrequency(words); //Evaluate f(word) for each word
        Arrays.sort(freq);  // Sort the array to do binary search
        int[] res = new int[queries.length];
        for(int i=0;i<queries.length;i++){
            int frequency = getFrequency(queries[i]);
            res[i]=freq.length - getPosition(freq, frequency);  // get position where f(i-1)<=frequency and f(i)>frequency
        }
        return res;
    }

    private int getPosition(int[] words, int freq){
        int start = 0, end = words.length, mid;
        while(start<=end && start<words.length && end>=0){
            mid = start+(end-start)/2;
            if(mid>0 && words[mid-1]<=freq && words[mid]>freq){
                //System.out.println(start+" "+freq);
                return mid;
            }else if(words[mid]<=freq){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        // System.out.println(start+" "+freq);
        return start;
    }

    private int[] getAllFrequency(String[] words){
        int[] arr = new int[words.length];
        for(int i=0;i<words.length;i++){
            arr[i] =getFrequency(words[i]);
        }
        return arr;
    }

    private int getFrequency(String s){
        int count=0;
        if(s==null || s.isEmpty()){return 0;}
        char c=s.charAt(0);
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch<c){
                c=ch;
                count=1;
            }else if(c==ch){
                count++;
            }
        }
        //System.out.println(s+" "+count);
        return count;
    }
}

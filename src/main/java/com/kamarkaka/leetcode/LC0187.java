package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 187. Repeated DNA Sequences
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more
 * than once in a DNA molecule. You may return the answer in any order.
 * Example 1:
 *   Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 *   Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 *   Input: s = "AAAAAAAAAAAAA"
 *   Output: ["AAAAAAAAAA"]
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class LC0187 {
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length() <= 10) return new ArrayList<>();

        Set<String> seen = new HashSet<>();
        Set<String> added = new HashSet<>();
        for (int i = 0; i + 10 <= s.length(); ++i) {
            String currSub = s.substring(i, i + 10);
            if (!seen.add(currSub)) added.add(currSub);
        }

        return new ArrayList<>(added);
    }
}

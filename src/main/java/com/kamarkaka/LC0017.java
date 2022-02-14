package main.java.com.kamarkaka;

import java.util.*;

public class LC0017 {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a','b','c'));
        map.put('3', Arrays.asList('d','e','f'));
        map.put('4', Arrays.asList('g','h','i'));
        map.put('5', Arrays.asList('j','k','l'));
        map.put('6', Arrays.asList('m','n','o'));
        map.put('7', Arrays.asList('p','q','r','s'));
        map.put('8', Arrays.asList('t','u','v'));
        map.put('9', Arrays.asList('w','x','y','z'));

        for (int i = 0; i < digits.length(); i++) {
            char c = digits.charAt(i);

            List<Character> chars = map.get(c);
            List<String> newRes = new ArrayList<>();
            if (i == 0) {
                for (Character c1 : chars) {
                    newRes.add(c1.toString());
                }
            } else {
                for (Character c1 : chars) {
                    for (String str : res) {
                        newRes.add(str + c1);
                    }
                }
            }
            res = newRes;
        }

        return res;
    }
}

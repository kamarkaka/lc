/***
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *   Every adjacent pair of words differs by a single letter.
 *   Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 *   sk == endWord
 *
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 * Example 1:
 *   Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 *   Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 *   Explanation: There are 2 shortest transformation sequences:
 *     "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 *     "hit" -> "hot" -> "lot" -> "log" -> "cog"
 *
 * Example 2:
 *   Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 *   Output: []
 *   Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * Constraints:
 *   1 <= beginWord.length <= 5
 *   endWord.length == beginWord.length
 *   1 <= wordList.length <= 1000
 *   wordList[i].length == beginWord.length
 *   beginWord, endWord, and wordList[i] consist of lowercase English letters.
 *   beginWord != endWord
 *   All the words in wordList are unique.
 */

package main.java.com.kamarkaka;

import java.util.*;

/***
 * 126. Word Ladder II
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *  Every adjacent pair of words differs by a single letter.
 *  Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 *  sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 * Example 1:
 *  Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 *  Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 *  Explanation: There are 2 shortest transformation sequences:
 *      "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 *      "hit" -> "hot" -> "lot" -> "log" -> "cog"
 *
 * Example 2:
 *  Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 *  Output: []
 *  Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * Constraints:
 *  1 <= beginWord.length <= 5
 *  endWord.length == beginWord.length
 *  1 <= wordList.length <= 1000
 *  wordList[i].length == beginWord.length
 *  beginWord, endWord, and wordList[i] consist of lowercase English letters.
 *  beginWord != endWord
 *  All the words in wordList are unique.
 */
public class LC0126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return res;
        }

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        Map<String, List<String>> map = new HashMap<>();

        biBFS(beginSet, endSet, set, map, false);

        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, map, res, path);
        return res;
    }

    private void biBFS(Set<String> beginSet, Set<String> endSet, Set<String> set, Map<String, List<String>> map, boolean reverse) {
        if (beginSet.size() > endSet.size()) {
            biBFS(endSet, beginSet, set, map, !reverse);
            return;
        }

        if (beginSet.isEmpty()) {
            return;
        }

        boolean found = false;
        set.removeAll(beginSet);
        Set<String> nextWords = new HashSet<>();

        for (String s: beginSet) {
            char[] arr = s.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    arr[i] = j;
                    String newWord = String.valueOf(arr);
                    if (set.contains(newWord)) {
                        if (endSet.contains(newWord))
                            found = true;
                        else {
                            nextWords.add(newWord);
                        }
                        String key = reverse ? newWord : s;
                        String value = reverse ? s : newWord;

                        map.putIfAbsent(key, new ArrayList<>());
                        map.get(key).add(value);
                    }
                }

                arr[i] = c;
            }
        }

        if (!found && !nextWords.isEmpty()) {
            biBFS(nextWords, endSet, set, map, reverse);
        }
    }

    private void dfs(String beginWord, String endWord, Map<String, List<String>> map, List<List<String>> res, List<String> path){
        if(beginWord.equals(endWord)){
            res.add(new ArrayList<>(path));
            return;
        }
        if(!map.containsKey(beginWord)){
            return;
        }

        for(String next: map.get(beginWord)){
            path.add(next);
            dfs(next, endWord, map, res, path);
            path.remove(path.size() - 1);
        }
    }

    public static void run() {
        LC0126 solution = new LC0126();
        System.out.println(solution.findLadders("cet", "ism", Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob")));
    }
}

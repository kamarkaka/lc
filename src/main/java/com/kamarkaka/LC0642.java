package com.kamarkaka;

import java.util.*;

/***
 * 642. Design Search Autocomplete System
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
 * You are given a string array sentences and an integer array times both of length n where sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed. For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.
 * Here are the specific rules:
 *    The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 *    The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).
 *    If less than 3 hot sentences exist, return as many as you can.
 *    When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Implement the AutocompleteSystem class:
 *    AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences and times arrays.
 *    List<String> input(char c) This indicates that the user typed the character c.
 *       Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
 *       Returns the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed. If there are fewer than 3 matches, return them all.
 *
 * Example 1:
 *    Input
 *       ["AutocompleteSystem", "input", "input", "input", "input"]
 *       [[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
 *    Output
 *       [null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]
 *    Explanation
 *       AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
 *       obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 *       obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
 *       obj.input("a"); // return []. There are no sentences that have prefix "i a".
 *       obj.input("#"); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 *
 * Constraints:
 *    n == sentences.length
 *    n == times.length
 *    1 <= n <= 100
 *    1 <= sentences[i].length <= 100
 *    1 <= times[i] <= 50
 *    c is a lowercase English letter, a hash '#', or space ' '.
 *    Each tested sentence will be a sequence of characters c that end with the character '#'.
 *    Each tested sentence will have a length in the range [1, 200].
 *    The words in each input sentence are separated by single spaces.
 *    At most 5000 calls will be made to input.
 */
public class LC0642 {
   public static void run() {
      AutocompleteSystem sol = new AutocompleteSystem(
         new String[] {"uqpewwnxyqxxlhiptuzevjxbwedbaozz","ewftoujyxdgjtazppyztom","pvyqceqrdrxottnukgbdfcr","qtdkgdbcyozhllfycfjhdsdnuhycqcofaojknuqqnozltrjcabyxrdqwrxvqrztkcxpenbbtnnnkfhmebj","jwfbusbwahyugiaiazysqbxkwgcawpniptbtmhqyrlxdwxxwhtumglihrgizrczv","cfptjitfzdcrhw","aitqgitjgrcbacgnaasvbouqsqcwbyskkpsnigtfeecmlkcjbgduban","utsqkmiqqgglufourfdpgdmrkbippffacwvtkpflzrvdlkdxykfpkoqcb","ethtbdopotpamvrwuomlpahtveyw","jiaqkaxovsqtkpdjfbkajpvpyetuoqwnrnpjdhoojbsdvneecsdvgqpyurmsvcy","j","btbnuplyeuccjbernsfbnveillrwdbqledwvpmvdbcugkurrkabtpykhlcogeszclyfuquafouv","hndjzblegevtfkgbjttektox","gtvnlninpvenapyfgmsjdisfnmiktitrutctawosjflvzfkbegnprixzqwzcyhoovsivuwmofsveqkyosowuyamuvy","sawrirvrfrbfagreahrioaombukmdwztbpggnxd","mgdcwptvbvhzyvvumvbjjn","otjvvkegwleyyxtghwgfmlsqlhrlibdvqfinyyebotjpwoaejhtornfgikmifdmwswbqgwhcbzuhrpajxuqicegcptszct","zlondsttyvnnnnxjtoqnlktitwzurissczzbyfsbgpoawodwjpsmavaugnhqtsbeixwl","yehvdehbtmwqkmcjmvpivfzqvevkotwzvjoyfvp","bjximtpayjdcxbrnksbtfnpynzaygygdflowewprqngdadzdhxcpgapjejojrkzrutgcsfpfvpluagniqimfqddldxqiw","bysyrxfykivyauysytgxfhqcrxliulahuizjvozpywrokxujhzpauxwufcxiitukljiiclatfrspqcljjoxpxziumstnhqr","uxtvutlgqapyfltiulwrplesmtowzoyhhjhzihatpuvmutxqgxfawpwypedbz","jzgsdjdawrqfladolduldhpdpagmvllvzamypuqlrpbmhxxadqaqrqavtxeghcyysjynovkiyjtvdluttodtmtocajgttmv","mbijfkmepalhdiubposdksdmmttxblkodcdrxbnxaqebnwliatnxpwaohbwkidia","ljggggbyxwrwanhjonoramexdmgjigrtpz","cqfvkutpipxjepfgsufonvjtotwfxyn","kvseesjazssavispavchdpzvdhibptowhyrrshyntpwkez","nveuzbaosuayteiozmnelxlwkrrrjlwvhejxhupvchfwmvnqukphgoacnazuoimcliubvhv","uwrpwhfdrxfnarxqpkhrylkwiuhzubjfk","bniyggdcloefwy","ihranmhbsahqjxesbtmdkjfsupzdzjvdfovgbtwhqfjdddwhdvrnlyscvqlnqpzegnvvzyymrajvso","lscreasfuxpdxsiinymuzybjexkpfjiplevqcjxlm","uwgnfozopsygnmptdtmmuumahoungpkodwxrcvfymqpeymaqruayvqqgoddgbnhemtsjifhxwiehncswxzrghf","nyfbxgcpfrzyqwfjzgmhuohjhrjizsyjqgmertmooeiaadcmiuyyylpcosnweoyydeauazhzbeaqn","tpylrxbwnkrfxckfdlvrbytaezuzmyscpvruthuvbxjenkeolvqsrjqzizyclzmqtjvnamdansmzyspcfghfprorqprua","nhldlmxpuckxeekipkrzugatjiivtazjbjyxokksyueyjbgmrovbckbxqcqefaiavzsarbbypgmpxe","sylraqsd","xr","xkzpxkhrucyatpatkigvntohihibyisyqtkjdhatdvyvxbjttz","nvnz","blzddwxphkgqfsfzfclwytstpvpzgcdeggdwzukzirscfzcteeuqbmmrfxcnokbbyxkqrxtjfarcefiynwfmy","inuxmuhtdwpuvyludwtokhtalxbuccepsayrjycbcwbtnfholjvkmypodv","awwillrm","xznodxngrstjrwqzmlmigpw","khlxjdtictufdfbkgfusdtaaeuspbbfmtjodflgqofzlqnulkdztflm","nlngmckslyqzjiyiexbropbxnynjcstziluewypboqhqndqsxhtnosrgrameajovsclrgwqjgnztvxrkhwnxkfrf","yroadxhxyacaexrwju","ujxlbpcbxdqrvubifnpzjmmkolyljzjhdegaiowaal","tnfnjgtxbckbpyplucprxcqzhrfjimylmlhdglntfydepltjvklyxesndzuubienhvuaqc","ouedhtkpkg","ygchsrrubucqffewifsxaefwocfaiiupqbomktvrcddggqfgnaycstpccwtbheyaqwhosxajqeqqxzyjsfng","jqqgpjvfkgjh","csowoazaiyejgyixszqmtctpzlkccccqregyhtvxccvrpkupwcyhqatxscevzdfbdqnuyadiyfnhysddfyxpgqtjiogmxsmzbbkr","dlzxdpchkdaztkqtrjmuujgoiae","plcjkwukkyqluxjbhxsyeaqvviinfuujsafwsquidvmutsrukxwrv","yopqbtpoqhpcktjangauzcvvpephhprpaaclbbkgchlqkrwdsaupeizlwxzcpkchoagmrrkwdkthosmrjefgbumnrjsb"},
         new int[] {12,9,4,4,1,5,3,4,7,9,2,4,2,3,11,13,1,3,4,10,7,1,9,5,10,14,5,3,2,11,5,14,4,13,11,5,15,8,1,12,2,11,4,2,11,14,9,12,1,7,13,11,7,2,6,10}
      );

      System.out.println(sol.input('w'));
      System.out.println(sol.input('o'));
      System.out.println(sol.input('f'));
      System.out.println(sol.input('q'));
      System.out.println(sol.input('m'));
      System.out.println(sol.input('p'));
      System.out.println(sol.input('k'));
      System.out.println(sol.input('q'));
      System.out.println(sol.input('g'));
      System.out.println(sol.input('i'));
      System.out.println(sol.input('s'));
      System.out.println(sol.input('a'));
      System.out.println(sol.input('v'));
      System.out.println(sol.input('#'));
   }
}

class AutocompleteSystem {
   TrieNode root;
   StringBuilder sb;
   TrieNode currNode;

   public AutocompleteSystem(String[] sentences, int[] times) {
      this.root = new TrieNode('#');
      this.sb = new StringBuilder();
      this.currNode = this.root;

      for (int i = 0; i < times.length; i++) {
         this.root.build(sentences[i], times[i]);
      }
   }

   public List<String> input(char c) {
      if (c == '#') {
         root.build(sb.toString(), 1);
         sb = new StringBuilder();
         currNode = root;
         return new ArrayList<>();
      }

      sb.append(c);
      List<Result> res = new ArrayList<>();

      if (!currNode.children.containsKey(c)) {
         currNode = new TrieNode('#');
         return new ArrayList<>();
      }

      currNode = currNode.children.get(c);
      Queue<TrieNode> queue = new LinkedList<>();
      queue.add(currNode);
      while (!queue.isEmpty()) {
         TrieNode node = queue.poll();
         if (node.isEnd) {
            res.add(new Result(node.word, node.hotness));
         }

         queue.addAll(node.children.values());
      }

      res.sort((o1, o2) -> {
         if (o1.hotness == o2.hotness) {
            return o1.sentence.compareTo(o2.sentence);
         }
         return o2.hotness - o1.hotness;
      });

      List<String> output = new ArrayList<>();
      for (int i = 0; i < Math.min(3, res.size()); i++) {
         output.add(res.get(i).sentence);
      }

      return output;
   }
}

class TrieNode {
   char val;
   boolean isEnd;
   int hotness;
   String word;
   Map<Character, TrieNode> children;

   public TrieNode(char c) {
      this.val = c;
      this.isEnd = false;
      this.hotness = -1;
      this.children = new HashMap<>();
   }

   public void build(String word, int hotness) {
      TrieNode node = this;

      for (char c : word.toCharArray()) {
         node.children.putIfAbsent(c, new TrieNode(c));
         node = node.children.get(c);
      }

      node.isEnd = true;
      node.hotness += hotness;
      node.word = word;
   }
}

class Result {
   String sentence;
   int hotness;

   public Result(String sentence, int hotness) {
      this.sentence = sentence;
      this.hotness = hotness;
   }
}
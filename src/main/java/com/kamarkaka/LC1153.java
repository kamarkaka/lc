package main.java.com.kamarkaka;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * 1153. String Transforms Into Another String
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 * Return true if and only if you can transform str1 into str2.
 *
 * Example 1:
 *   Input: str1 = "aabcc", str2 = "ccdee"
 *   Output: true
 *   Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 *
 * Example 2:
 *   Input: str1 = "leetcode", str2 = "codeleet"
 *   Output: false
 *   Explanation: There is no way to transform str1 to str2.
 *
 * Constraints:
 *   1 <= str1.length == str2.length <= 10^4
 *   str1 and str2 contain only lowercase English letters.
 */
public class LC1153 {
   public boolean canConvert(String str1, String str2) {
      if (str1.equals(str2)) {
         return true;
      }

      Map<Character, Character> conversionMappings = new HashMap<>();
      Set<Character> uniqueCharactersInStr2 = new HashSet<>();

      // Make sure that no character in str1 is mapped to multiple characters in str2.
      for (int i = 0; i < str1.length(); i++) {
         if (!conversionMappings.containsKey(str1.charAt(i))) {
            conversionMappings.put(str1.charAt(i), str2.charAt(i));
            uniqueCharactersInStr2.add(str2.charAt(i));
         } else if (conversionMappings.get(str1.charAt(i)) != str2.charAt(i)) {
            // this letter maps to 2 different characters, so str1 cannot transform into str2.
            return false;
         }
      }

      // No character in str1 maps to 2 or more different characters in str2 and there
      // is at least one temporary character that can be used to break any loops.
      if (uniqueCharactersInStr2.size() < 26) {
         return true;
      }

      // The conversion mapping forms one or more cycles and there are no temporary
      // characters that we can use to break the loops, so str1 cannot transform into str2.
      return false;
   }
}

package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 535. Encode and Decode TinyURL
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it
 * returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 * There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be
 * encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 * Implement the Solution class:
 *   Solution() Initializes the object of the system.
 *   String encode(String longUrl) Returns a tiny URL for the given longUrl.
 *   String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the
 *   given shortUrl was encoded by the same object.
 * Example 1:
 *   Input: url = "https://leetcode.com/problems/design-tinyurl"
 *   Output: "https://leetcode.com/problems/design-tinyurl"
 *   Explanation:
 *   Solution obj = new Solution();
 *   string tiny = obj.encode(url); // returns the encoded tiny url.
 *   string ans = obj.decode(tiny); // returns the original url after decoding it.
 * Constraints:
 *   1 <= url.length <= 10^4
 *   url is guaranteed to be a valid URL.
 */
public class LC0535 {
    public class Codec {
        private char[] code;
        private Map<String, String> hmap;

        public Codec() {
            this.code = new char[8];
            this.hmap = new HashMap<>();

            for (int i = 0; i < 8; i++) {
                code[i] = 'a';
            }
        }

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String shortUrl = generate();
            while (hmap.containsKey(shortUrl)) {
                shortUrl = generate();
            }

            hmap.put(shortUrl, longUrl);
            return shortUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return hmap.getOrDefault(shortUrl, "");
        }

        private String generate() {
            String result = String.valueOf(code);

            for (int i = 0; i < 8; i++) {
                char c = code[i];
                if (c + 1 <= 'z') {
                    code[i] = (char)(c + 1);
                    break;
                }
            }

            return result;
        }
    }
}

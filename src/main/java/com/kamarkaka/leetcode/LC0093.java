package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 93. Restore IP Addresses
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255
 * (inclusive) and cannot have leading zeros.
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and
 * "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots
 * into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 * Example 1:
 *   Input: s = "25525511135"
 *   Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *   Input: s = "0000"
 *   Output: ["0.0.0.0"]
 * Example 3:
 *   Input: s = "101023"
 *   Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * Constraints:
 *   1 <= s.length <= 20
 *   s consists of digits only.
 */
public class LC0093 {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        dfs(result, s, 0, 0, "");
        return result;
    }

    private void dfs(List<String> result, String s, int from, int index, String ip) {
        if (from >= s.length()) return;
        if (index == 3) {
            String numStr = s.substring(from);
            if (numStr.length() > 1 && numStr.charAt(0) == '0') return;
            if (numStr.length() <= 3 && Integer.parseInt(numStr) <= 255) {
                result.add(ip + "." + numStr);
            }
            return;
        }

        String numStr = "";
        for (int i = from; i < s.length(); i++) {
            numStr += s.charAt(i);
            if (numStr.equals("0")) {
                dfs(result, s, i + 1, index + 1, ip.isEmpty() ? numStr : ip + "." + numStr);
                return;
            } else if (numStr.length() <= 3 && Integer.parseInt(numStr) <= 255) {
                dfs(result, s, i + 1, index + 1, ip.isEmpty() ? numStr : ip + "." + numStr);
            } else {
                return;
            }
        }
    }
}

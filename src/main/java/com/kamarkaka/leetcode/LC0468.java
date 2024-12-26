package com.kamarkaka.leetcode;

/***
 * 468. Validate IP Address
 * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or
 * "Neither" if IP is not a correct IP of any type.
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For
 * example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00", and
 * "192.168@1.1" are invalid IPv4 addresses.
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 *   1 <= xi.length <= 4
 *   xi is a hexadecimal string which may contain digits, lowercase English letter ('a' to 'f') and upper-case English
 *   letters ('A' to 'F').
 *   Leading zeros are allowed in xi.
 *   For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6
 *   addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6
 *   addresses.
 * Example 1:
 *   Input: queryIP = "172.16.254.1"
 *   Output: "IPv4"
 *   Explanation: This is a valid IPv4 address, return "IPv4".
 * Example 2:
 *   Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 *   Output: "IPv6"
 *   Explanation: This is a valid IPv6 address, return "IPv6".
 * Example 3:
 *   Input: queryIP = "256.256.256.256"
 *   Output: "Neither"
 *   Explanation: This is neither a IPv4 address nor a IPv6 address.
 * Constraints:
 *   queryIP consists only of English letters, digits and the characters '.' and ':'.
 */
public class LC0468 {
    public String validIPAddress(String IP) {
        if (IP == null || IP.length() == 0) return "Neither";
        if (IP.charAt(IP.length() - 1) == ':') return "Neither";
        if (IP.charAt(IP.length() - 1) == '.') return "Neither";
        return isIPv4(IP) ? "IPv4" : (isIPv6(IP) ? "IPv6" : "Neither");
    }

    public boolean isIPv4(String IP) {
        String[] parts = IP.split("\\.");
        if (parts.length != 4) return false;

        for (String part : parts) {
            if (part == null || part.length() == 0) return false;

            int num = 0;
            for (int i = 0; i < part.length(); i++) {
                char c = part.charAt(i);

                if (i == 0) {
                    if (c == '0' && part.length() != 1) return false;
                }

                int digit = c - '0';
                if (digit < 0 || digit > 9) return false;

                num = 10 * num + digit;
                if (num > 255) return false;
            }
        }
        return true;
    }

    public boolean isIPv6(String IP) {
        IP = IP.toLowerCase();

        String[] parts = IP.split(":");
        if (parts.length != 8) return false;

        for (String part : parts) {
            if (part == null || part.length() == 0) return false;
            if (part.length() > 4) return false;

            for (int i = 0; i < part.length(); i++) {
                char c = part.charAt(i);
                if (
                        (c < '0' || c > '9') &&
                                (c < 'a' || c > 'f')
                ) return false;
            }

        }
        return true;
    }
}

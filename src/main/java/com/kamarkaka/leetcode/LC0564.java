package com.kamarkaka.leetcode;

/***
 * 564. Find the Closest Palindrome
 * Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome.
 * If there is a tie, return the smaller one.
 * The closest is defined as the absolute difference minimized between two integers.
 * Example 1:
 *   Input: n = "123"
 *   Output: "121"
 * Example 2:
 *   Input: n = "1"
 *   Output: "0"
 *   Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 * Constraints:
 *   1 <= n.length <= 18
 *   n consists of only digits.
 *   n does not have leading zeros.
 *   n is representing an integer in the range [1, 10^18 - 1].
 */
public class LC0564 {
    public String nearestPalindromic(String n) {
        if (n == null) return null;
        // form the first palindrome number based on n
        char[] arr = n.toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            arr[arr.length - 1 - i] = arr[i];
        }
        String start = new String(arr);
        if (n.equals(start)) {
            String smaller = findNextPalindrome(start, false);
            String larger = findNextPalindrome(start, true);
            String diffS = getDiff(n, smaller);
            String diffL = getDiff(larger, n);
            return isSmaller(diffL, diffS) ? larger : smaller;
        } else {
            if (isSmaller(start, n)) { // start < n
                String larger = findNextPalindrome(start, true);
                return isSmaller(getDiff(larger, n), getDiff(n, start)) ? larger : start;
            } else { // start > n
                String smaller = findNextPalindrome(start, false);
                return isSmaller(getDiff(start, n), getDiff(n, smaller)) ? start : smaller;
            }
        }
    }

    private String findNextPalindrome(String start, boolean smallerThan) {
        // since next palindrome number may shorter or longer than tgt, I use StringBuilder to form the number
        StringBuilder sb = new StringBuilder(start);
        int mid = (sb.length() - 1) / 2;
        char cur = sb.charAt(mid);
        if (smallerThan) { // find a larger palindrome num
            // be careful with 9s
            if (cur != '9') {
                sb.setCharAt(mid, (char)(cur + '1' - '0'));
                if (sb.length() % 2 == 0) sb.setCharAt(mid + 1, (char)(cur + '1' - '0'));
            } else {
                // count the number of 9s
                int i = mid;
                while (i >=0 && sb.charAt(i) == '9') {
                    if (i == 0) {
                        sb.setCharAt(i, '1');
                        sb.setCharAt(sb.length() - 1, '0');
                        sb.append('1');
                        return sb.toString();
                    } else {
                        sb.setCharAt(i, '0');
                        sb.setCharAt(sb.length()-1-i, '0');
                    }
                    i--;
                }
                sb.setCharAt(i, (char)(sb.charAt(i) + '1' - '0'));
                sb.setCharAt(sb.length()-1-i, sb.charAt(i));
            }
            return sb.toString();
        } else { // find a smaller palindrome num
            // be carefull with mid is the leftmost position and the digit is 1
            // be careful with 0s
            if (mid == 0 && cur == '1') return sb.length() == 1 ? "0" : "9";
            if (cur != '0') {
                sb.setCharAt(mid, (char)(cur - '1'+ '0'));
                if (sb.length() % 2 == 0) sb.setCharAt(mid + 1, (char)(cur - '1'+ '0'));
            } else {
                // count the number of 0s
                int i = mid;
                while (i >= 0 && sb.charAt(i) == '0') {
                    sb.setCharAt(i, '9');
                    sb.setCharAt(sb.length()-1-i, '9');
                    i--;
                }
                if (i == 0 && sb.charAt(0) == '1') {
                    sb.setCharAt(0, '9');
                    sb.deleteCharAt(sb.length() - 1);
                    return sb.toString();
                }
                sb.setCharAt(i, (char)(sb.charAt(i) - '1' + '0'));
                sb.setCharAt(sb.length()-1-i, sb.charAt(i));
            }
            return sb.toString();
        }
    }

    private String getDiff(String l, String s) {
        char[] res = new char[s.length()];
        int borrow = 0;
        int nonZ = res.length - 1;
        for(int i = res.length - 1; i >= 0; i--) {
            int tmp = borrow;
            if (l.length() != s.length()) {
                tmp += l.charAt(i+1) - s.charAt(i);
            } else {
                tmp += l.charAt(i) - s.charAt(i);
            }
            if (tmp < 0) {
                tmp += 10;
                borrow = -1;
            } else borrow = 0;
            res[i] = (char)(tmp + '0');
            if (tmp != 0) nonZ = i;
        }
        return new String(res, nonZ, res.length - nonZ);
    }

    private boolean isSmaller(String s, String l) {
        if (l.length() > s.length()) return true;
        if (l.length() < s.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < l.charAt(i)) return true;
            if (s.charAt(i) > l.charAt(i)) return false;
        }
        return false;
    }
}

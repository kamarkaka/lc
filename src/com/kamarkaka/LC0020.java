/***
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 *   Open brackets must be closed by the same type of brackets.
 *   Open brackets must be closed in the correct order.
 *
 * Example 1:
 *   Input: s = "()"
 *   Output: true
 *
 * Example 2:
 *   Input: s = "()[]{}"
 *   Output: true
 *
 * Example 3:
 *   Input: s = "(]"
 *   Output: false
 *
 * Example 4:
 *   Input: s = "([)]"
 *   Output: false
 *
 * Example 5:
 *   Input: s = "{[]}"
 *   Output: true
 *
 * Constraints:
 *   1 <= s.length <= 10^4
 *   s consists of parentheses only '()[]{}'.
 */
package com.kamarkaka;

import java.util.Stack;

public class LC0020 {
    public boolean isValid(String s) {
        if (s == null || s.length() < 2) return false;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if ((c == ')' && !stack.empty() && stack.peek() == '(') || (c == ']' && !stack.empty() && stack.peek() == '[') || (c == '}' && !stack.empty() && stack.peek() == '{')) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty() ? true : false;
    }

    public static void run() {
        LC0020 solution = new LC0020();
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("()[]{}"));
        System.out.println(solution.isValid("(]"));
        System.out.println(solution.isValid("([)]"));
        System.out.println(solution.isValid("{[]}"));
    }

}

package com.kamarkaka;

import java.util.*;

public class LC0022 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesisRecur(n, 0, 0, new StringBuilder(""), res);
        return res;
    }

    private void generateParenthesisRecur(int n, int open, int close, StringBuilder sb, List<String> res) {
        if (open == n && close == n) res.add(sb.toString());

        if (open < n) {
            sb.append("(");
            generateParenthesisRecur(n, open + 1, close, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (open > close && close < n) {
            sb.append(")");
            generateParenthesisRecur(n, open, close + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

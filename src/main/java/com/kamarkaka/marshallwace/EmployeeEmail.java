package com.kamarkaka.marshallwace;

import java.util.HashMap;
import java.util.Map;

/***
 * You are given a list of names of new employees in a company. You need to generated a company email address for each
 * of them.
 * The name of each person consists of two or three parts: a first name, an optional middle name, and a last name,
 * separated by spaces. Each part can include only English letters (but the last name may additionally contain hyphens).
 * The name of the company also consists of English letters.
 * Each address must use the format:
 * First.Last@Company.com
 * where:
 * - First is the first name;
 * - Last is the last name, truncated to at most 8 initial letters,
 * Company is the name of the company.
 * The address should be in lowercase and should not contain hyphens. Hyphens in the last name should be removed before
 * truncating the last name.
 * If more than one person would have the same email address, differentiate their addresses by adding subsequent
 * integers (starting from the second address and number 2) before the "@" sign. For example, if there are three people
 * who would have the address "jd@company.com", they should be assigned address:
 * "jd@company.com",
 * "jd2@company.com",
 * "jd3@company.com",
 *
 * Write a function that, given a string S containing a list of name separated by the characters "; " and a string C
 * specifying the name of the company, returns a string containing the list of email addresses separated by the
 * characters "; " in the same order as they were in the input. Each entry in the list should be of the form:
 * - Name <Email>
 *
 * Example:
 * Given: C = "Example", S = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe;
 * Jane Doe; Peter Brian Parker"
 * The function should return:
 * "John Doe <john.doe@example.com>; Peter Benjamin Parker <peter.parker@example.com>; Mary Jane Watson-Parker
 * <mary.watsonpa@example.com>; John Elvis Doe <john.doe2@example.com>; John Evan Doe <john.doe3@example.com>; Jane Doe
 * <jane.doe@example.com>; Peter Brian Parker <peter.parker2@example.com>"
 *
 * Assumptions:
 * - The length of string S is within the range [3..1,000].
 * - The length of string C is within the range [1..100].
 * - String S consists only of letters (a-z and/or A-Z), spaces, hyphens, and semicolons.
 * - String S contains valid names; no name appears more than once.
 * C is made only of letters (a-z and/or A-Z).
 */
public class EmployeeEmail {
    public String generate(String S, String C) {
        StringBuilder sb = new StringBuilder();
        String[] employees = S.split("; ");

        Map<String, Integer> countMap = new HashMap<>();

        for (String employee : employees) {
            String[] parts = employee.split(" ");
            String firstName = parts[0].toLowerCase();
            String lastName = parts[parts.length - 1].replaceAll("-", "");
            lastName = lastName.substring(0, Math.min(8, parts[parts.length - 1].length())).toLowerCase();
            String key = firstName + "." + lastName;
            String countStr = "";

            if (countMap.containsKey(key)) {
                int count = countMap.get(key) + 1;
                countMap.put(key, count);
                countStr = Integer.toString(count);
            } else {
                countMap.put(key, 1);
            }


            sb
                    .append(employee)
                    .append(" <")
                    .append(key)
                    .append(countStr)
                    .append("@")
                    .append(C)
                    .append(".com>; ");

        }

        return sb.substring(0, sb.toString().length() - 2);
    }

    public static void main(String[] args) {
        EmployeeEmail solution = new EmployeeEmail();
        System.out.println(solution.generate("John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker", "Example"));
    }
}

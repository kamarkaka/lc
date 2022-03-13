package com.kamarkaka.twosigma;

public class AddTwoStrings {
   public String add(String s1, String s2) {
      if ((s1 == null || s1.length() == 0) && ((s2 == null || s2.length() == 0))) return "";
      if (s1 == null || s1.length() == 0) return s2;
      if (s2 == null || s2.length() == 0) return s1;

      int sign1 = s1.charAt(0) == '-' ? -1 : 1;
      int sign2 = s2.charAt(0) == '-' ? -1 : 1;

      if (sign1 == -1) s1 = s1.substring(1);
      if (sign2 == -1) s2 = s2.substring(1);

      if (sign1 * sign2 > 0) {
         if (sign1 > 0) return calculate(1, '+', s1, s2); // positive + positive
         return calculate(-1, '+', s1, s2); // negative + negative
      }

      int comparisonResult = compare(s1, s2);
      if (comparisonResult == 0) return "0"; // x + (-x) or (-x) + x

      if (sign1 == -1) {
         if (comparisonResult < 0) return calculate(1, '-', s2, s1); // -1 + 2 -> 2 - 1
         return calculate(-1, '-', s1, s2); // -2 + 1 -> -(2 - 1)
      }

      if (comparisonResult < 0) return calculate(-1, '-', s2, s1); // 1 + (-2) -> -(2-1)
      return calculate(1, '-', s1, s2); // 2 + (-1) -> 2 - 1
   }

   private String calculate(int sign, char op, String s1, String s2) {
      StringBuilder sb = new StringBuilder();
      int p1 = s1.length() - 1, p2 = s2.length() - 1, carry = 0;
      int sign2 = op == '-' ? -1 : 1;
      while (p1 >= 0 || p2 >= 0) {
         int num1 = 0, num2 = 0;
         if (p1 >= 0) num1 = s1.charAt(p1) - '0';
         if (p2 >= 0) num2 = (s2.charAt(p2) - '0') * sign2;

         int sum = num1 + num2 + carry;

         if (sum > 0) {
            carry = sum / 10;
         } else if (sum < 0) {
            sum += 10;
            carry = -1;
         }

         sb.append(sum % 10);
         p1--;
         p2--;
      }

      if (carry != 0) {
         sb.append(carry);
      }

      if (sign < 0) sb.append('-');

      return sb.reverse().toString();
   }

   private int compare(String s1, String s2) {
      int len1 = s1.length();
      int len2 = s2.length();
      if (len1 != len2) return Integer.compare(len1, len2);

      int p1 = 0, p2 = 0;
      while (p1 < s1.length()) {
         int digit1 = s1.charAt(p1++);
         int digit2 = s2.charAt(p2++);
         if (digit1 != digit2) return Integer.compare(digit1, digit2);
      }
      return 0;
   }

   public static void run() {
      AddTwoStrings sol = new AddTwoStrings();
      System.out.println(sol.add("999", "11"));
      System.out.println(sol.add("999", "-11"));
      System.out.println(sol.add("900", "-11"));
      System.out.println(sol.add("-900", "11"));
      System.out.println(sol.add("-900", "-11"));
   }
}

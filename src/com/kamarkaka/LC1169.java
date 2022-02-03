package com.kamarkaka;

import java.util.*;

/***
 * 1169. Invalid Transactions
 * A transaction is possibly invalid if:
 *   the amount exceeds $1000, or;
 *   if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
 * You are given an array of strings transaction where transactions[i] consists of comma-separated values representing the name, time (in minutes), amount, and city of the transaction.
 * Return a list of transactions that are possibly invalid. You may return the answer in any order.
 *
 * Example 1:
 *   Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
 *   Output: ["alice,20,800,mtv","alice,50,100,beijing"]
 *   Explanation: The first transaction is invalid because the second transaction occurs within a difference of 60 minutes, have the same name and is in a different city. Similarly the second one is invalid too.
 *
 * Example 2:
 *   Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
 *   Output: ["alice,50,1200,mtv"]
 *
 * Example 3:
 *   Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
 *   Output: ["bob,50,1200,mtv"]
 *
 * Constraints:
 *   transactions.length <= 1000
 *   Each transactions[i] takes the form "{name},{time},{amount},{city}"
 *   Each {name} and {city} consist of lowercase English letters, and have lengths between 1 and 10.
 *   Each {time} consist of digits, and represent an integer between 0 and 1000.
 *   Each {amount} consist of digits, and represent an integer between 0 and 2000.
 */
public class LC1169 {
   public List<String> invalidTransactions(String[] transactions) {
      Map<String, List<Transaction>> personalHistory = new HashMap<>();
      List<Transaction> allTransactions = new ArrayList<>();

      for (String transaction : transactions) {
         Transaction t = new Transaction(transaction);
         allTransactions.add(t);
         personalHistory.putIfAbsent(t.name, new ArrayList<>());
         personalHistory.get(t.name).add(t);
      }

      List<String> invalid = new ArrayList<>();
      for (Transaction t : allTransactions) {
         if (!isValid(t, personalHistory.get(t.name))) {
            invalid.add(t.original);
         }
      }
      return invalid;
   }

   private boolean isValid(Transaction t, List<Transaction> transactions) {
      if (t.amount > 1000) {
         return false;
      }

      for (Transaction transaction : transactions) {
         if (Math.abs(transaction.time - t.time) <= 60 && !transaction.city.equals(t.city)) {
            return false;
         }
      }
      return true;
   }

   class Transaction {
      final String original;
      final String name;
      final int time;
      final int amount;
      final String city;

      public Transaction(String transaction) {
         this.original = transaction;
         String[] parsed = transaction.split(",");
         this.name = parsed[0];
         this.time = Integer.parseInt(parsed[1]);
         this.amount = Integer.parseInt(parsed[2]);
         this.city = parsed[3];
      }
   }

   public static void run() {
      LC1169 solution = new LC1169();
      System.out.println(solution.invalidTransactions(new String[] {
         "alice,50,1200,mtv",
         "alice,50,1200,mtv"
      }));
   }
}
